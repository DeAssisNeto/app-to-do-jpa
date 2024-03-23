package com.app.todojpa.repositories;

import com.app.todojpa.models.TaskModel;
import com.app.todojpa.models.UserModel;
import com.app.todojpa.roles.TaskRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    public List<TaskModel> findAllByUserAndCompleted(UserModel user, boolean completed);
    public List<TaskModel> findAllByUserAndCompletedAndAndPriority(UserModel user,boolean completed, TaskRole priority);
}
