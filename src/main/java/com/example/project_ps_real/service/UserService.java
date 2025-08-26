package com.example.project_ps_real.service;

import com.example.project_ps_real.entity.User;
import com.example.project_ps_real.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> retrieveUsers(){
        return (List<User>) this.userRepository.findAll();
    }

    public User retrieveUserById(Long id){
        Optional <User> users=this.userRepository.findById(id);
        if(users.isPresent()){
            System.out.println("User found with ID: " + id);
            return users.get();
        }
        else
        {
            System.out.println("User not found!");
            return null;
        }

    }

    public User addUser(User user){
            return this.userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> isUser = this.userRepository.findById(user.getUserId());
        if(isUser.isPresent()){
            return this.userRepository.save(user);
        }else{
            return null;
        }
    }

    public String deleteUserById(Long id){
        Optional<User> user=this.userRepository.findById(id);
        if(user.isPresent()) {
            this.userRepository.deleteById(id);
            return "Deletion Successfully";

        }
        else {
            return "Failed to delete user with id " + id;
        }
    }


    public User getUserByEmail(String email){
        Optional <User> users=this.userRepository.findByEmail((email));
        if(users.isPresent()){
            System.out.println("User found with username: " + email);
            return users.get();
        }
        else
        {
            System.out.println("User not found!");
            return null;
        }
    }

}
