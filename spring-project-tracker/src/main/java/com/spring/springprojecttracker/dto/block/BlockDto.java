package com.spring.springprojecttracker.dto.block;

import com.spring.springprojecttracker.domain.block.Block;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BlockDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RegistBlockReq {
        private Long blockHeight;
        private String channel;
        private String peerId;
        private String signature;
        private String blockHash;
        private LocalDateTime timestamp;

        @Builder
        public RegistBlockReq(Long blockHeight, String channel, String peerId, String signature, String blockHash, LocalDateTime timestamp) {
            this.blockHeight = blockHeight;
            this.channel = channel;
            this.peerId = peerId;
            this.signature = signature;
            this.blockHash = blockHash;
            this.timestamp = timestamp;
        }

        public Block toEntity() {
            return Block.builder()
                    .blockHeight(blockHeight)
                    .channel(channel)
                    .peerId(peerId)
                    .signature(signature)
                    .blockHash(blockHash)
                    .timestamp(timestamp)
                    .build();
        }
    }

    @Getter
    public static class Res {
        private Long blockHeight;
        private String channel;
        private String peerId;
        private String signature;
        private String blockHash;
        private LocalDateTime timestamp;

        public Res(Block block) {
            this.blockHeight = block.getBlockHeight();
            this.channel = block.getChannel();
            this.peerId = block.getPeerId();
            this.signature = block.getSignature();
            this.blockHash = block.getBlockHash();
            this.timestamp = block.getTimestamp();
        }
    }


}
