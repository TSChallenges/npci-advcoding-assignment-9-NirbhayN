package com.mystore.app.rest;


import com.mystore.app.entity.User;
import com.mystore.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping()
    public User getByUsername(@RequestParam("username") String username){
        return userService.findByUsername(username);
    }


    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestBody User user)
    {

        return userService.createUser(user);
    }


}
