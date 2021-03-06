package me.hyeonkyo.todo.domain.todo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.hyeonkyo.todo.domain.todo.dto.TodoRequestDto;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;
import me.hyeonkyo.todo.domain.todo.repository.TodoListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TodoListcontrollerTest {

    @Autowired
    TodoListRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String url = "";

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + port + "/api/todo";
    }

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
    }

    @Test
    void GET_findAll테스트() throws Exception {
        //given
        repository.save(TodoList.builder()
                .content("data1")
                .build());
        repository.save(TodoList.builder()
                .content("data2")
                .build());
        //when
        ResultActions resultActions = mockMvc.perform(get(url));
        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.length"))
    }

    @Test
    void POST_save테스트() throws Exception {
        TodoRequestDto dto = TodoRequestDto.builder()
                .content("data1")
                .targetDate(LocalDate.of(2022, 6, 2))
                .build();

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andDo(print());
    }

    @Test
    void PUT_update테스트() throws Exception {
        TodoRequestDto dto = TodoRequestDto.builder()
                .content("old data")
                .targetDate(LocalDate.of(2022, 6, 2))
                .build();
        Long id = repository.save(dto.toEntity()).getId();

        url += "/" + id;
        System.out.println("==== url: " + url +" =====");
        LocalDate updateDate = LocalDate.of(2023, 6, 2);
        TodoRequestDto updateDto = TodoRequestDto.builder()
                .content("new data")
                .isCompleted(true)
                .targetDate(updateDate)
                .build();

        mockMvc.perform(put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(id+""))
                .andDo(print());

        TodoList todoList = repository.findById(id).orElseThrow(() -> new Exception());
        assertThat(todoList.getContent()).isEqualTo("new data");
        assertThat(todoList.getIsCompleted()).isTrue();
        assertThat(todoList.getTargetDate()).isEqualTo(updateDate);
    }

    @Test
    void DELETE_delete테스트() throws Exception {
        TodoRequestDto dto = TodoRequestDto.builder()
                .content("delete data")
                .targetDate(LocalDate.of(2022, 6, 10))
                .build();
        Long id = repository.save(dto.toEntity()).getId();

        url += "/" + id;
        System.out.println("==== url: " + url +" =====");
        mockMvc.perform(delete(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(id+""));

        Optional<TodoList> item = repository.findById(id);
        assertThrows(Exception.class, () -> {item.get();});
    }
}