package com.spring.springprojecttracker.domain.transaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String txHash;

    private String status;

    private String channel;

    private Long BlockHeight;

    private String fromAddr;

    @Column(columnDefinition = "TEXT")
    private String data;

    private LocalDateTime timestamp;
}
