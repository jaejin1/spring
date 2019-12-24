package com.spring.springprojecttracker.domain.block;

import com.spring.springprojecttracker.domain.transaction.Transaction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Block {

    @Id
    private Long blockHeight;

    private String channel;

    private String peerId;

    private String Signature;

    private String blockHash;

    private LocalDateTime timestamp;

//    private Integer txsCount;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "block")
//    private List<Transaction> transactions;
}
