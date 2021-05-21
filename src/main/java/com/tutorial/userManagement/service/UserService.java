package com.tutorial.userManagement.service;

import com.tutorial.userManagement.email.EmailServiceImpl;
import com.tutorial.userManagement.model.User;
import com.tutorial.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Random;

@Service
public class UserService {
    private UserRepository userRepository;
    private EmailServiceImpl emailService;

    @Autowired
    public UserService(UserRepository userRepository,EmailServiceImpl emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
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

    @Transactional
    public User resetPassword(User user) throws MessagingException {
        Random random = new Random();
        String password = random.nextInt(9999)+"";

        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setEmail(user.getEmail());
        user.setPassword(BCrypt.hashpw(password.trim(), BCrypt.gensalt()));
        user = this.updateItem(user);

        this.emailService.sendSimpleMessage(user.getEmail(),"Reset Password","Password Changed Successfully. \n" +
                "Your current Password :  "+password.trim());

        return user;
    }
}
