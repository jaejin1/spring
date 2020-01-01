package com.spring.springprojecttracker.tracker;

import com.spring.springprojecttracker.dto.block.BlockDto;
import foundation.icon.icx.IconService;
import foundation.icon.icx.data.Block;
import foundation.icon.icx.transport.http.HttpProvider;
import okhttp3.OkHttpClient;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Request {

    private String URL;
    private OkHttpClient okHttpClient;

    public Request(String url) {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(200, TimeUnit.MILLISECONDS)
                .writeTimeout(600, TimeUnit.MILLISECONDS)
                .build();
        URL = url;
    }

    public BlockDto.RegistBlockReq getBlock(String height) {
        IconService iconService = new IconService(new HttpProvider(okHttpClient, URL));
        Block block = null;

        foundation.icon.icx.Request<Block> request = iconService.getBlock(new BigInteger(height));

        try {
            block = request.execute();
            System.out.println(block.toString());

        } catch (Exception e) {
            System.out.println("Error !!");
        }

        return BlockDto.RegistBlockReq.builder()
                .blockHeight(block.getHeight().toString())
                .channel("testchannel")
                .peerId(block.getPeerId())
                .signature(block.getSignature())
                .blockHash(block.getBlockHash().toString())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
