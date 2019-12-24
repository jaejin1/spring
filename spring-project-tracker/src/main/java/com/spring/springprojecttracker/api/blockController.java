package com.spring.springprojecttracker.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("block")
@AllArgsConstructor
public class blockController {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String test() {
        return "Hello";
    }
}
