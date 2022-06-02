package me.hyeonkyo.todo.domain.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@Getter
public class TodoRequestDto {
    private String content;
    private Boolean isCompleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate targetDate;

    public TodoList toEntity() {
        return TodoList.builder()
                .content(content)
                .isCompleted(isCompleted)
                .targetDate(targetDate)
                .build();
    }
}
