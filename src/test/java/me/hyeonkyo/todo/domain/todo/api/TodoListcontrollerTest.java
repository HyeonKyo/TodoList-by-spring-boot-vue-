package me.hyeonkyo.todo.domain.todo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.hyeonkyo.todo.domain.todo.dto.TodoRequestDto;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;
import me.hyeonkyo.todo.domain.todo.repository.TodoListRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;
import java.util.List;
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
                .targetDate(LocalDateTime.of(2022, 6, 2, 14, 0))
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
                .targetDate(LocalDateTime.of(2022, 6, 2, 14, 0))
                .build();
        Long id = repository.save(dto.toEntity()).getId();

        url += "/" + id;
        System.out.println("==== url: " + url +" =====");
        LocalDateTime updateDate = LocalDateTime.of(2023, 6, 2, 14, 0);
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
                .targetDate(LocalDateTime.of(2022, 6, 2, 14, 0))
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