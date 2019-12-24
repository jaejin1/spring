package com.spring.springaop.controller;

import com.spring.springaop.service.BookService;
import com.spring.springaop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private BookService bookService;
    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String index() {
        bookService.findBookByTitle("spring");
        return "indexPage";
    }

    @GetMapping("/test")
    public String test() {
        testService.test();
        return "test";
    }
}
