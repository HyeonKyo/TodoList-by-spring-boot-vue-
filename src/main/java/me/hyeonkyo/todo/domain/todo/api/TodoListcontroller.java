package me.hyeonkyo.todo.domain.todo.api;

import lombok.RequiredArgsConstructor;
import me.hyeonkyo.todo.domain.todo.dto.TodoRequestDto;
import me.hyeonkyo.todo.domain.todo.service.TodoListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/todo")
@RestController
public class TodoListcontroller {

    private final TodoListService todoListService;

    @PostMapping
    public Long save(@RequestBody TodoRequestDto dto) {
        return todoListService.save(dto);
    }
}
