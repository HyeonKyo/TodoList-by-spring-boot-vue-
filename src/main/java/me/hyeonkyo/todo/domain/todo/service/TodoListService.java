package me.hyeonkyo.todo.domain.todo.service;

import me.hyeonkyo.todo.domain.todo.dto.TodoListResponseDto;
import me.hyeonkyo.todo.domain.todo.dto.TodoRequestDto;

import java.util.List;

public interface TodoListService {

    List<TodoListResponseDto> findAll();

    Long save(TodoRequestDto dto);

    Long update(Long id, TodoRequestDto dto);

    Long delete(Long id);
}
