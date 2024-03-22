package com.app.todojpa.models;

import com.app.todojpa.dtos.TaskRecordDto;
import com.app.todojpa.roles.TaskRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "TB_TASK")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TaskModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private TaskRole priority;
    @ManyToOne
    @JoinColumn(
        name = "user_id"
    )
    private UserModel user;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean completed;

    public TaskModel(TaskRecordDto dto, UserDetails user){
        this.description = dto.description();
        this.priority = dto.priority();
        this.user = (UserModel) user;
    }

}
