package com.spring.springprojecttracker.tracker;

import com.spring.springprojecttracker.domain.transaction.Transaction;
import com.spring.springprojecttracker.dto.block.BlockDto;
import com.spring.springprojecttracker.dto.transaction.TransactionDto;
import com.spring.springprojecttracker.service.transaction.TransactionService;
import foundation.icon.icx.IconService;
import foundation.icon.icx.data.Block;
import foundation.icon.icx.data.ConfirmedTransaction;
import foundation.icon.icx.transport.http.HttpProvider;
import okhttp3.OkHttpClient;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Request {

    private Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    private String URL;
    private OkHttpClient okHttpClient;

    public Request(String url) {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(200, TimeUnit.MILLISECONDS)
                .writeTimeout(600, TimeUnit.MILLISECONDS)
                .build();
        URL = url;
    }

    public Long getLastBlockHeight() {
        IconService iconService = new IconService(new HttpProvider(okHttpClient, URL));
        foundation.icon.icx.Request<Block> request = iconService.getLastBlock();
        Long block = null;
        try {
            block = request.execute().getHeight().longValue();
        } catch (Exception e) {
            logger.error("request execute error");
        }

        return block;
    }

    public Block getBlock(Long height) {
        IconService iconService = new IconService(new HttpProvider(okHttpClient, URL));
        Block block = null;

        foundation.icon.icx.Request<Block> request = iconService.getBlock(new BigInteger(Long.toString(height)));
        try {
            block = request.execute();
        } catch (Exception e) {
            logger.error("request execute error");
        }

        return block;
    }

    public BlockDto.RegistBlockReq getBlockInfo(Block block) {
        return BlockDto.RegistBlockReq.builder()
                .blockHeight(block.getHeight().longValue())
                .channel("testchannel")
                .peerId(block.getPeerId())
                .signature(block.getSignature())
                .blockHash(block.getBlockHash().toString())
                .timestamp(LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(TimeUnit.MICROSECONDS.toMillis(block.getTimestamp().longValue())),
                        TimeZone.getDefault().toZoneId()))
                .build();
    }

    public List<TransactionDto.RegistTransactionReq> getTransactionInfoByBlockHeight(Block block) {
        List<ConfirmedTransaction> txList = block.getTransactions();
        List<TransactionDto.RegistTransactionReq> txDtoList = new ArrayList<TransactionDto.RegistTransactionReq>();

        for (int txCount = 0; txCount < block.getTransactions().size(); txCount++){
            String data = "";
            if (txList.get(txCount).getData() != null) {
                data = txList.get(txCount).getData().toString();
            }
            txDtoList.add(TransactionDto.RegistTransactionReq.builder()
                        .txHash(txList.get(txCount).getTxHash().toString())
                        .status("Comfirmed")
                        .channel("testchannel")
                        .blockHeight(block.getHeight().longValue())
                        .fromAddr(txList.get(txCount).getFrom().toString())
                        .data(data)
                        .timestamp(LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(TimeUnit.MICROSECONDS.toMillis(txList.get(txCount).getTimestamp().longValue())),
                                TimeZone.getDefault().toZoneId()))
                        .build());
        }
        return txDtoList;
    }

}
