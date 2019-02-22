package com.lambdaschool.todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="todo")
public class ToDo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoid;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name ="userid", nullable=false)
    @JsonIgnore
    private Users userid;

    public ToDo(){}

    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Users getUserid()
    {
        return userid;
    }

    public void setUserid(Users userid)
    {
        this.userid = userid;
    }
}
