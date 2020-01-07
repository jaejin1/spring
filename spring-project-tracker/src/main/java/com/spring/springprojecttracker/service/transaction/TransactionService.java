package com.spring.springprojecttracker.service.transaction;

import com.spring.springprojecttracker.domain.transaction.Transaction;
import com.spring.springprojecttracker.domain.transaction.TransactionRepository;
import com.spring.springprojecttracker.dto.transaction.TransactionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction create(TransactionDto.RegistTransactionReq dto) {
        return transactionRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public List<TransactionDto.Res> findAllDesc() {
        return transactionRepository.findAllByOrderByIdDesc()
                .map(TransactionDto.Res::new)
                .collect(Collectors.toList());
    }
}
