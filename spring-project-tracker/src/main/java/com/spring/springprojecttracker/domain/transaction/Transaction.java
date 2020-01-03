package com.spring.springprojecttracker.domain.transaction;

import lombok.*;

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

    private Long blockHeight;

    private String fromAddr;

    @Column(columnDefinition = "TEXT")
    private String data;

    private LocalDateTime timestamp;

    @Builder
    public Transaction(String txHash, String status, String channel, Long blockHeight, String fromAddr, String data, LocalDateTime timestamp) {
        this.blockHeight = blockHeight;
        this.txHash = txHash;
        this.status = status;
        this.channel = channel;
        this.fromAddr = fromAddr;
        this.data = data;
        this.timestamp = timestamp;
    }
}
