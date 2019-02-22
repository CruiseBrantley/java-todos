package com.lambdaschool.todo.repository;

import com.lambdaschool.todo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Long>
{
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery=true)
    public Users findByName(String username);
}
