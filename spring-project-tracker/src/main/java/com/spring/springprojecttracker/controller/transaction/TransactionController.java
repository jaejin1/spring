package com.spring.springprojecttracker.controller.transaction;

import com.spring.springprojecttracker.dto.transaction.TransactionDto;
import com.spring.springprojecttracker.service.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping(value = "/{txId}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public TransactionDto.Res getTx(@PathVariable final long txId) {
        return new TransactionDto.Res(transactionService.findByTxId(txId));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public TransactionDto.Res registTx(@Valid @RequestBody TransactionDto.RegistTransactionReq dto) {
        return new TransactionDto.Res(transactionService.create(dto));
    }
}
