package me.hyeonkyo.todo.domain.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;

import java.time.LocalDate;

@Data
public class TodoListResponseDto {

    private Long id;
    private String content;
    private Boolean isCompleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate targetDate;

    public TodoListResponseDto(TodoList todoList) {
        this.id = todoList.getId();
        this.content = todoList.getContent();
        this.isCompleted = todoList.getIsCompleted();
        this.targetDate = todoList.getTargetDate();
    }
}
