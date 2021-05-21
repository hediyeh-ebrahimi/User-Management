package com.tutorial.userManagement.controller;

import com.tutorial.userManagement.email.EmailServiceImpl;
import com.tutorial.userManagement.model.User;
import com.tutorial.userManagement.service.UserService;
import com.tutorial.userManagement.validate.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.Validate;

import javax.mail.MessagingException;
import java.util.List;

@Controller
public class MainController {

    private UserService userService;
    private EmailServiceImpl emailService;
    private UserValidate userValidate;

    @Autowired
    public MainController(UserService userService, EmailServiceImpl emailService, UserValidate userValidate) {
        this.userService = userService;
        this.emailService = emailService;
        this.userValidate = userValidate;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object loadFirstPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public Object loginSubmit(@ModelAttribute User user, Model model) {
        List<String> userValidate = this.userValidate.validateLogin(user);
        if (userValidate.size() > 0) {
            model.addAttribute("error", userValidate);
            return "login";
        } else {
            System.out.println(this.userService.findByEmail(user.getEmail()));
            System.out.println(user.getEmail());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            model.addAttribute("user",this.userService.findByEmail(user.getEmail()));
            return "profile";
        }
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public Object showSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @RequestMapping(value = "/signUpSubmit", method = RequestMethod.POST)
    public Object signUpSubmit(@ModelAttribute User user, Model model) {
        List<String> userValidate = this.userValidate.validateAdd(user);
        if (userValidate.size() > 0) {
            model.addAttribute("error", userValidate);
            return "signUp";
        } else {
            this.userService.addItem(user);
            model.addAttribute("success", "User added successfully.");
            return "login";
//            this.emailService.sendSimpleMessage("sarait405@gmail.com", "testtttttttttt", "tesyyyyyyyyyyyyyyyy");
        }

    }

    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    public Object resetPassword(@ModelAttribute User user,Model model){
        try {
            user = this.userService.findByEmail(user.getEmail());
            user = this.userService.resetPassword(user);
            model.addAttribute("user",user);
            model.addAttribute("success", "Password reset Successfully");
        } catch (MessagingException e) {
            model.addAttribute("error", "Password reset error");
            e.printStackTrace();
        }
        return "profile";
    }
}
