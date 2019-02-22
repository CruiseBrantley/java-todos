package com.lambdaschool.todo;

import com.lambdaschool.todo.models.Users;
import com.lambdaschool.todo.models.ToDo;
import com.lambdaschool.todo.repository.ToDoRepository;
import com.lambdaschool.todo.repository.UsersRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value = "ToDo Sprint", description = "ToDo Sprint Challenge API")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EndpointController
{
    @Autowired
    UsersRepository usersrepos;

    @Autowired
    ToDoRepository todorepos;

    @ApiOperation(value = "list All Customers", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully recetrieve list"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
            })

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

    @GetMapping("/todos/todoid/{todoid}")
    public ToDo getbytodoid(@PathVariable long todoid)
    {
        var found = todorepos.findById(todoid);
        if (found.isPresent())
        {
            return found.get();
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/users")
    public Users postuser(@RequestBody Users user) throws URISyntaxException
    {
        return usersrepos.save(user);
    }

    @PostMapping("/todos")
    public ToDo posttodo(@RequestBody ToDo todo) throws URISyntaxException
    {
        return todorepos.save(todo);
    }

    @PutMapping("/users/userid/{userid}")
    public Users changeUser(@RequestBody Users newUser, @PathVariable long userid) throws URISyntaxException
    {
        Optional<Users> updateUser = usersrepos.findById(userid);
        if (updateUser.isPresent())
        {
            newUser.setUserid(userid);
            usersrepos.save(newUser);

            return newUser;
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/users/todoid/{todoid}")
    public ToDo changeToDo(@RequestBody ToDo newToDo, @PathVariable long todoid) throws URISyntaxException
    {
        Optional<ToDo> updateToDo = todorepos.findById(todoid);
        if (updateToDo.isPresent())
        {
            newToDo.setTodoid(todoid);
            todorepos.save(newToDo);

            return newToDo;
        }
        else
        {
            return null;
        }
    }

    @DeleteMapping("/users/userid/{userid}")
    public Users deleteUser(@PathVariable long userid)
    {
        var found = usersrepos.findById(userid);
        if (found.isPresent())
        {
            usersrepos.deleteById(userid);
            return found.get();
        }
        else
        {
            return null;
        }
    }

    @DeleteMapping("/todos/todoid/{todoid}")
    public ToDo deleteToDo(@PathVariable long todoid)
    {
        var found = todorepos.findById(todoid);
        if (found.isPresent())
        {
            todorepos.deleteById(todoid);
            return found.get();
        }
        else
        {
            return null;
        }
    }
}
