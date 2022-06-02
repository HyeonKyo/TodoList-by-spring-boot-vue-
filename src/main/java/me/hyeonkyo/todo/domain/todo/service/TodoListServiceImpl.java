package me.hyeonkyo.todo.domain.todo.service;

import lombok.RequiredArgsConstructor;
import me.hyeonkyo.todo.domain.todo.dto.TodoListResponseDto;
import me.hyeonkyo.todo.domain.todo.dto.TodoRequestDto;
import me.hyeonkyo.todo.domain.todo.repository.TodoList;
import me.hyeonkyo.todo.domain.todo.repository.TodoListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;

    @Override
    public List<TodoListResponseDto> findAll() {
        return todoListRepository.findAll().stream()
                .map((todoList) -> new TodoListResponseDto(todoList))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long save(TodoRequestDto dto) {
        return todoListRepository.save(dto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, TodoRequestDto dto) {
        TodoList todo = todoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        todo.update(dto.getContent(), dto.getIsCompleted(), dto.getTargetDate());
        return id;
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        TodoList todo = todoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        todoListRepository.delete(todo);
        return id;
    }
}
