package me.hyeonkyo.todo.domain.todo.dto;

import lombok.Getter;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;

import java.time.LocalDateTime;

@Getter
public class TodoRequestDto {
    private String content;
    private Boolean isCompleted;
    private LocalDateTime targetDate;

    public TodoList toEntity() {
        return TodoList.builder()
                .content(this.content)
                .isCompleted(this.isCompleted)
                .targetDate(this.targetDate)
                .build();
    }
}
