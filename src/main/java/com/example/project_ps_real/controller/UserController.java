package com.example.project_ps_real.controller;

import com.example.project_ps_real.entity.User;
import com.example.project_ps_real.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

   /* @Autowired
    private BCryptPasswordEncoder passwordEncoder;*/


    @GetMapping("/getAll")
    @ResponseBody
    public List<User> getAllUsers(){
        return this.userService.retrieveUsers();
    }

    @GetMapping("/getById")
    @ResponseBody
    public User getUserById(@RequestParam("id") Long id) {
        return this.userService.retrieveUserById(id);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public User insertUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/deleteUserById")
    @ResponseBody
    public String deleteUserById(@RequestParam Long id){
        return this.userService.deleteUserById(id);
    }

    @GetMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        User user = userService.getUserByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }


}
