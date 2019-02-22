package com.lambdaschool.todo;

import com.lambdaschool.todo.models.Users;
import com.lambdaschool.todo.models.ToDo;
import com.lambdaschool.todo.repository.ToDoRepository;
import com.lambdaschool.todo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EndpointController
{
    @Autowired
    UsersRepository usersrepos;

    @Autowired
    ToDoRepository todorepos;

    @GetMapping("/users")
    public List<Users> allUsers()
    {
        return usersrepos.findAll();
    }

    @GetMapping("/todos")
    public List<ToDo> allTodos()
    {
        return todorepos.findAll();
    }

    @GetMapping("/users/userid/{userid}")
    public Users getbyuserid(@PathVariable long userid)
    {
        var found = usersrepos.findById(userid);
        if (found.isPresent())
        {
            return found.get();
        }
        else
        {
            return null;
        }
    }

    @GetMapping("/users/username/{username}")
    public Users getbyusername(@PathVariable String username)
    {
        var foundCust = usersrepos.findByName(username);
        if (foundCust != null)
        {
            return foundCust;
        }
        else
        {
            return null;
        }
    }

}
