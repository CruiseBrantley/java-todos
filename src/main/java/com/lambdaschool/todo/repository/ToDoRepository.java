package com.lambdaschool.todo.repository;

import com.lambdaschool.todo.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long>
{
}
