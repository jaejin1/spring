package com.spring.springprojecttracker.dto.block;

import com.spring.springprojecttracker.domain.block.Block;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BlockDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RegistBlockReq {
        private Long blockHeight;
        private String channel;
        private String peerId;
        private String signature;
        private String blockHash;

        @Builder
        public RegistBlockReq(Long blockHeight, String channel, String peerId, String signature, String blockHash) {
            this.blockHeight = blockHeight;
            this.channel = channel;
            this.peerId = peerId;
            this.signature = signature;
            this.blockHash = blockHash;
        }

        public Block toEntity() {
            return Block.builder()
                    .blockHeight(blockHeight)
                    .channel(channel)
                    .peerId(peerId)
                    .signature(signature)
                    .blockHash(blockHash)
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

        public Res(Block block) {
            this.blockHeight = block.getBlockHeight();
            this.channel = block.getChannel();
            this.peerId = block.getPeerId();
            this.signature = block.getSignature();
            this.blockHash = block.getBlockHash();
        }
    }


}
