package com.spring.springprojecttracker.dto.transaction;

import com.spring.springprojecttracker.domain.transaction.Transaction;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TransactionDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RegistTransactionReq {
        private String txHash;
        private String status;
        private String channel;
        private Long blockHeight;
        private String fromAddr;
        private String data;
        private LocalDateTime timestamp;

        @Builder
        public RegistTransactionReq(String txHash, String status, String channel, Long blockHeight, String fromAddr, String data, LocalDateTime timestamp) {
            this.blockHeight = blockHeight;
            this.txHash = txHash;
            this.status = status;
            this.channel = channel;
            this.fromAddr = fromAddr;
            this.data = data;
            this.timestamp = timestamp;
        }

        public Transaction toEntity() {
            return Transaction.builder()
                    .txHash(txHash)
                    .status(status)
                    .channel(channel)
                    .blockHeight(blockHeight)
                    .fromAddr(fromAddr)
                    .data(data)
                    .timestamp(timestamp)
                    .build();
        }
    }

    @Getter
    public static class Res {
        private Long id;
        private String txHash;
        private String status;
        private String channel;
        private Long blockHeight;
        private String fromAddr;
        private String data;
        private LocalDateTime timestamp;

        public Res(Transaction transaction) {
            this.id = transaction.getId();
            this.txHash = transaction.getTxHash();
            this.status = transaction.getStatus();
            this.channel = transaction.getChannel();
            this.blockHeight = transaction.getBlockHeight();
            this.fromAddr = transaction.getFromAddr();
            this.data = transaction.getData();
            this.timestamp = transaction.getTimestamp();
        }
    }
}
