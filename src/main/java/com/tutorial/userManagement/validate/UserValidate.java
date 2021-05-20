package com.tutorial.userManagement.validate;

import com.tutorial.userManagement.model.User;
import com.tutorial.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class UserValidate {

    private UserService userService;

    @Autowired
    public UserValidate(UserService userService) {
        this.userService = userService;
    }

    public List<String> validateAdd(User user) {
        List<String> error = new ArrayList<>();
        if (user.getPhoneNumber().trim().length() != 11) {
            error.add("Phone Number must be 11 digit.");
        }
        if (!Pattern.matches("09[0-9]{9}", user.getPhoneNumber().trim())) {
            error.add("Phone Number is not valid.");
        }

        Pattern patternEmail = Pattern.compile("[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})");
        if (!patternEmail.matcher(user.getEmail().trim()).matches()) {
            error.add("Email is not valid.");
        } else {
            User userExist = this.userService.findByEmail(user.getEmail().trim());
            if (userExist != null && userExist.getUserId() != 0) {
                error.add("Email is not valid.");
            }
        }
        return error;

    }

    public List<String> validateLogin(User user) {
        List<String> error = new ArrayList<>();

        Pattern patternEmail = Pattern.compile("[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})");
        if (!patternEmail.matcher(user.getEmail().trim()).matches()) {
            error.add("Email is not valid.");
        } else {
            String password = BCrypt.hashpw(user.getPassword().trim(), BCrypt.gensalt());
            User userExist = this.userService.findByEmail(user.getEmail().trim());
            boolean checkpw = BCrypt.checkpw(user.getPassword().trim(), userExist.getPassword());
            if (!checkpw) {
                error.add("Email or Password is wrong.");
            }
        }
        return error;
    }
}
