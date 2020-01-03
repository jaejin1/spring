package com.spring.springprojecttracker.api;

import com.spring.springprojecttracker.dto.transaction.TransactionDto;
import com.spring.springprojecttracker.service.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("tx")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TransactionDto.Res registTx(@Valid @RequestBody TransactionDto.RegistTransactionReq dto) {
        return new TransactionDto.Res(transactionService.create(dto));
    }
}
