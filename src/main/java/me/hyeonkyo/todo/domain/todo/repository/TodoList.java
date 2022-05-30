package me.hyeonkyo.todo.domain.todo.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hyeonkyo.todo.domain.model.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class TodoList extends BaseTimeEntity {

    //id, content, (생성일, 수정일 ⇒ audiance) , 목표일, 달성일, 등록 아이디(fk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false)
    private String content;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isCompleted;

    private LocalDateTime targetDate;

    private LocalDateTime completedDate;

//    @Column()
//    private String userName;

    @Builder
    public TodoList(String content, Boolean isCompleted, LocalDateTime targetDate) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.targetDate = targetDate;
    }

    public void update(String content, Boolean isCompleted, LocalDateTime targetDate) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.targetDate = targetDate;
    }
}
