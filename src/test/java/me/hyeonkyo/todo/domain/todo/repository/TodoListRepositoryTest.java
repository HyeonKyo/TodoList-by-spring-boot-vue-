package me.hyeonkyo.todo.domain.todo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest //SpringBootTest와는 다르게 단위테스트 가능
class TodoListRepositoryTest {

    @Autowired
    private TodoListRepository todoListRepository;

    //JPA의 CRUD테스트, save, findById, findAll 등등..

    @AfterEach
    void tearDown() {
        todoListRepository.deleteAll();
    }

    @Test
    void save테스트() throws Exception {
        todoListRepository.save(TodoList.builder()
                .content("Hello")
                .build());

        List<TodoList> list = todoListRepository.findAll();
        TodoList todo = list.get(0);
        assertNotNull(todo);
        assertThat(todo.getContent()).isEqualTo("Hello");
        assertThat(todo.getIsCompleted()).isFalse();
        assertThat(todo.getCompletedDate()).isNull();
    }

    @Test
    void findAll테스트() throws Exception {
        LocalDate targetDate = LocalDate.now();

        todoListRepository.save(TodoList.builder()
                .content("data1")
                .targetDate(targetDate)
                .build());
        todoListRepository.save(TodoList.builder()
                .content("data2")
                .targetDate(targetDate)
                .build());

        TodoList todoList1 = todoListRepository.findById(1L).orElseThrow(() -> new Exception());
        TodoList todoList2 = todoListRepository.findById(2L).orElseThrow(() -> new Exception());
        assertThat(todoList1.getContent()).isEqualTo("data1");
        assertThat(todoList1.getTargetDate()).isEqualTo(targetDate);
        assertThat(todoList2.getContent()).isEqualTo("data2");
        assertThat(todoList2.getTargetDate()).isEqualTo(targetDate);
    }

    @Test
    void Auditing_생성_테스트() {
        LocalDateTime current = LocalDateTime.of(2021,06,04,00,00,00);

        todoListRepository.save(TodoList.builder()
                .content("data")
                .build());

        List<TodoList> list = todoListRepository.findAll();
        TodoList todo = list.get(0);

        assertThat(todo.getCreatedDate()).isAfter(current);
        assertThat(todo.getModifiedDate()).isAfter(current);
    }
}