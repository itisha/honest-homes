package com.exadel.controller;


import com.exadel.mongodb.model.User;
import com.exadel.mongodb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll() {
        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findById(@PathVariable("id") String id) {
        return new ResponseEntity<User>(userService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/lastName/{lastName}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findByLastName(@PathVariable("lastName") String lastName) {
        return new ResponseEntity<List<User>>(userService.findByLastName(lastName), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity create(@RequestBody User user) {
        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity update(@RequestBody User user) {
        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable("id") String id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (userService.exists(id)) {
            userService.delete(id);
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity(status);
    }
}
