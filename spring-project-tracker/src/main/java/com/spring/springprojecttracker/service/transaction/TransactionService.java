package com.spring.springprojecttracker.service.transaction;

import com.spring.springprojecttracker.domain.transaction.Transaction;
import com.spring.springprojecttracker.domain.transaction.TransactionRepository;
import com.spring.springprojecttracker.dto.transaction.TransactionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction create(TransactionDto.RegistTransactionReq dto) {
        return transactionRepository.save(dto.toEntity());
    }
}
