package com.tutorial.userManagement.service;

import com.tutorial.userManagement.model.User;
import com.tutorial.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addItem(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword().trim(), BCrypt.gensalt()));
        return this.userRepository.save(user);
    }

    @Transactional
    public void deleteItem(User user) {
        this.userRepository.delete(user);
    }

    @Transactional
    public User updateItem(User user) {
        return this.userRepository.save(user);
    }

    public User findOne(long id) {
        return this.userRepository.getOne(id);
    }

    public User findByEmailAndPassword(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
