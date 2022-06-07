package me.hyeonkyo.todo.domain.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@NoArgsConstructor
@Getter
public class TodoRequestDto {
    private String content;
    private Boolean isCompleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate targetDate;

    @Builder
    public TodoRequestDto(String content, Boolean isCompleted, LocalDate targetDate) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.targetDate = targetDate;
    }

    public TodoList toEntity() {
        return TodoList.builder()
                .content(content)
                .isCompleted(isCompleted)
                .targetDate(targetDate)
                .build();
    }
}
