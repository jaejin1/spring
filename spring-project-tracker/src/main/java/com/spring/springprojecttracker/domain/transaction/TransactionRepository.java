package com.spring.springprojecttracker.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Stream<Transaction> findAllByOrderByIdDesc();

    Transaction findById(long txId);
}
