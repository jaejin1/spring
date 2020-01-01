package com.spring.springprojecttracker.domain.transaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String txHash;

    private String status;

    private String channel;

    private String BlockHeight;

    private String fromAddr;

    @Column(columnDefinition = "TEXT")
    private String data;

    private LocalDateTime timestamp;
}
