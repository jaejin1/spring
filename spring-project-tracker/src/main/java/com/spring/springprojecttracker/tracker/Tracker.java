//package com.spring.springprojecttracker.tracker;
//
//
//import com.spring.springprojecttracker.api.BlockController;
//import com.spring.springprojecttracker.api.TransactionController;
//import com.spring.springprojecttracker.dto.block.BlockDto;
//import com.spring.springprojecttracker.dto.transaction.TransactionDto;
//import foundation.icon.icx.data.Block;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextRefreshedEvent;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.util.List;
//
//@WebListener
//@Configuration
//@ConfigurationProperties(prefix = "blockchain")
//@Getter
//@Setter
//public class Tracker implements ServletContextListener, Runnable {
//    private String apiUrl;
//
//    @Autowired
//    private BlockController blockController;
//    @Autowired
//    private TransactionController transactionController;
//
//    private Thread thread;
//    private boolean isShutdown = false;
//
//    private ServletContext sc;
//
//    public void startDaemon() {
//        if (thread == null) {
//            thread = new Thread(this, "Daemon thread for background task");
//        }
//
//        if (!thread.isAlive()) {
//            thread.start();
//        }
//    }
//
//    public void run() {
//
//        Thread currentThread = Thread.currentThread();
//        Request request = new Request(apiUrl);
//
//        while (currentThread == thread && !this.isShutdown) {
//            try {
//                System.out.println("== DaemonListener is running. ==");
//
//                Long blockHeightInDB = GetCurrentBlockHeightInDB();
//                Long blockHeight = request.getLastBlockHeight();
//                Block block = GetBlockInBlockChain(request, blockHeightInDB + 1);
//
//                if (blockHeight == null) {
//                    continue;
//                }
//
//                if (blockHeightInDB < blockHeight) {
//                    System.out.println("you need to crawl in testchannel");
//                    StoreBlock(block, request);
//                    StoreTx(block, request);
//                } else {
//                    System.out.println("Don't need to crawl in testchannel");
//                }
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("== DaemonListener end. ==");
//    }
//
//    public void contextInitialized (ServletContextEvent event) {
//        System.out.println("== DaemonListener.contextInitialized has been called. ==");
//        Request request = new Request(apiUrl);
//        Block block = GetBlockInBlockChain(request, 0L);
//        StoreBlock(block, request);
//
//        System.out.println("test !!! !! ! ! ! ! ! !");
//        System.out.println("test !!! !! ! ! ! ! ! !");
//        System.out.println("test !!! !! ! ! ! ! ! !");
//
//        sc = event.getServletContext();
//
//        startDaemon();
//    }
//
//    public void contextDestroyed (ServletContextEvent event) {
//        System.out.println("== DaemonListener.contextDestroyed has been called. ==");
//        this.isShutdown = true;
//        try {
//            thread.join();
//            thread = null;
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }
//    }
//
//    public Long GetCurrentBlockHeightInDB() {
//        Long blockHeightInDB = blockController.findLastBlockHeight();
//        System.out.println(blockHeightInDB);
//
//        if (blockHeightInDB == null) {
//            return 0L;
//        }
//        return blockHeightInDB;
//    }
//
//    public Block GetBlockInBlockChain(Request request, Long blockHeight) {
//        return request.getBlock(blockHeight);
//    }
//
//    public void StoreBlock(Block block, Request request) {
//        BlockDto.RegistBlockReq dto = request.getBlockInfo(block);
//        blockController.registBlock(dto);
//    }
//
//    public void StoreTx(Block block, Request request) {
//        List<TransactionDto.RegistTransactionReq> txListDto = request.getTransactionInfoByBlockHeight(block);
//        for (int txCount = 0; txCount < txListDto.size(); txCount++) {
//            transactionController.registTx(txListDto.get(txCount));
//        }
//    }
//}
