package com.example.demoregister.service;


import com.example.demoregister.entity.User;
import com.example.demoregister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User saveUser(User user){return userRepository.save(user);}

    public User identifyUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User identifyUserById(Long userId) {
        return userRepository.findUserById(userId);
    }
}
