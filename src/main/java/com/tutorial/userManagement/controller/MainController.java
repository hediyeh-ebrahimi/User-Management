package com.tutorial.userManagement.controller;

import com.tutorial.userManagement.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object loadFirstPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public Object loginSubmit(@ModelAttribute User user) {
        System.out.println(user);
        return "login";
    }
}
