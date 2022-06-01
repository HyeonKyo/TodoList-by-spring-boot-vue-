package me.hyeonkyo.todo.domain.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;

import java.time.LocalDateTime;

@ToString
@Builder
@Getter
public class TodoRequestDto {
    private String content;
    private Boolean isCompleted;
    private LocalDateTime targetDate;

    public TodoList toEntity() {
        return TodoList.builder()
                .content(content)
                .isCompleted(isCompleted)
                .targetDate(targetDate)
                .build();
    }
}
