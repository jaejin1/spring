package com.spring.springorm.controller;

import com.spring.springorm.domain.User;
import com.spring.springorm.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @PostMapping("/user")
    public String userAdd(User user) {
        userRepository.insert(user);
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String userlistPage(Model model) {
        model.addAttribute("users", userRepository.listForBeanPropertyRowMapper());
        return "/userList";
    }
}
