package com.spring.springsecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/private")
    public String privatePage() {
        return "private";
    }

    @GetMapping(value = "/public")
    public String publicPage() {
        return "public";
    }
}
