package me.hyeonkyo.todo.domain.todo.dto;

import lombok.Data;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;

import java.time.LocalDateTime;

@Data
public class TodoListResponseDto {

    private Long id;
    private String content;
    private Boolean isCompleted;
    private LocalDateTime targetDate;

    public TodoListResponseDto(TodoList todoList) {
        this.id = todoList.getId();
        this.content = todoList.getContent();
        this.isCompleted = todoList.getIsCompleted();
        this.targetDate = todoList.getTargetDate();
    }
}
