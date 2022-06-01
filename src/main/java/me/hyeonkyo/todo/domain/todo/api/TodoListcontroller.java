package me.hyeonkyo.todo.domain.todo.api;

import lombok.RequiredArgsConstructor;
import me.hyeonkyo.todo.domain.todo.dto.TodoListResponseDto;
import me.hyeonkyo.todo.domain.todo.dto.TodoRequestDto;
import me.hyeonkyo.todo.domain.todo.service.TodoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/todo")
@RestController
public class TodoListcontroller {

    private final TodoListService todoListService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<TodoListResponseDto> list = todoListService.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TodoRequestDto dto) {
        Long saveId = todoListService.save(dto);
        return ResponseEntity.ok(saveId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody TodoRequestDto dto) {
        Long modifiedId = todoListService.update(id, dto);
        return ResponseEntity.ok(modifiedId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Long deleteId = todoListService.delete(id);
        return ResponseEntity.ok(deleteId);
    }
}
