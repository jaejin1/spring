package com.spring.springprojecttracker.domain.block;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Block {

    @Id
    private Long blockHeight;

    private String channel;

    private String peerId;

    private String signature;

    private String blockHash;

    private LocalDateTime timestamp;

    @Builder
    public Block(Long blockHeight, String channel, String peerId, String signature, String blockHash, LocalDateTime timestamp) {
        this.blockHeight = blockHeight;
        this.channel = channel;
        this.peerId = peerId;
        this.signature = signature;
        this.blockHash = blockHash;
        this.timestamp = timestamp;
    }

//    private Integer txsCount;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "block")
//    private List<Transaction> transactions;
}
