package me.hyeonkyo.todo.domain.todo.repository;

import me.hyeonkyo.todo.domain.model.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//@DynamicInsert
@ToString
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

    @Column(nullable = false)
    private Boolean isCompleted;

    private LocalDate targetDate;

    private LocalDateTime completedDate;

//    @Column()
//    private String userName;

    @Builder
    public TodoList(String content, Boolean isCompleted, LocalDate targetDate) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.targetDate = targetDate;
    }

    @PrePersist
    public void prePersist() {
        this.isCompleted = this.isCompleted == null ? false : this.isCompleted;
    }

    public void update(String content, Boolean isCompleted, LocalDate targetDate) {
        this.content = (content == null || content.isEmpty()) ? this.content : content;
        this.isCompleted = isCompleted == null ? this.isCompleted : isCompleted;
        this.targetDate = targetDate;
    }
}
