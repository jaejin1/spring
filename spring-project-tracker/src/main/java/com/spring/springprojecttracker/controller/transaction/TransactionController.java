package com.spring.springprojecttracker.controller.transaction;

import com.spring.springprojecttracker.service.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tx")
@AllArgsConstructor
public class TransactionController {
    private TransactionService transactionService;

    @GetMapping
    public String tx(Model model) {
        model.addAttribute("tx", transactionService.findAllDesc());
        return "/tx";
    }
}
