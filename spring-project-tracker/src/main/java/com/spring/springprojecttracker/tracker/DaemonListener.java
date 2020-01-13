package com.spring.springprojecttracker.tracker;

import com.spring.springprojecttracker.controller.block.BlockController;
import com.spring.springprojecttracker.controller.transaction.TransactionController;
import com.spring.springprojecttracker.dto.block.BlockDto;
import com.spring.springprojecttracker.dto.transaction.TransactionDto;
import foundation.icon.icx.data.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class DaemonListener implements ServletContextListener, Runnable
{
    private Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    private static final String API_URL = "https://bicon.net.solidwallet.io/api/v3";

    private final BlockController blockController;
    private final TransactionController transactionController;

    public DaemonListener(BlockController blockController, TransactionController transactionController){
        this.blockController = blockController;
        this.transactionController = transactionController;
    }

    /** 작업을 수행할 thread */
    private Thread thread;
    private boolean isShutdown = false;
    /** context */
    private ServletContext sc;
    /** 작업을 수행한다 */
    public void startDaemon() {
        if (thread == null) {
            thread = new Thread(this, "Daemon thread for background task");
//            thread.setDaemon(true);
        }
        if (!thread.isAlive()) {
            thread.start();
        }
    }
    /** 스레드가 실제로 작업하는 부분 */
    public void run() {
        Thread currentThread = Thread.currentThread();
        Request request = new Request(API_URL);

        while (currentThread == thread && !this.isShutdown) {
            try {
                logger.info("DaemonListener is running");

                Long blockHeightInDB = GetCurrentBlockHeightInDB();
                Long blockHeight = request.getLastBlockHeight();
                Block block = GetBlockInBlockChain(request, blockHeightInDB + 1);

                if (blockHeight == null || block == null) {
                    continue;
                }

                if (blockHeightInDB < blockHeight) {
                    logger.info("you need to crawl in ~~channel");
                    StoreBlock(block, request);
                    StoreTx(block, request);
                } else {
                    logger.info("don't need to crawl in ~~channel");
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("DaemonListener end");
    }
    /** 컨텍스트 초기화 시 데몬 스레드를 작동한다 */
    public void contextInitialized (ServletContextEvent event) {
        logger.info("DaemonListener.contextInitialized has been called");
        sc = event.getServletContext();
        startDaemon();
    }
    /** 컨텍스트 종료 시 thread를 종료시킨다 */
    public void contextDestroyed (ServletContextEvent event) {
        logger.info("DaemonListener.contextDestroyed has been called");
        this.isShutdown = true;
        try
        {
            thread.join();
            thread = null;
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }

    private Long GetCurrentBlockHeightInDB() {
        Long blockHeightInDB = blockController.findLastBlockHeight();
        return blockHeightInDB;
    }

    private Block GetBlockInBlockChain(Request request, Long blockHeight) {
        return request.getBlock(blockHeight);
    }

    private void StoreBlock(Block block, Request request) {
        BlockDto.RegistBlockReq dto = request.getBlockInfo(block);
        blockController.registBlock(dto);
    }

    private void StoreTx(Block block, Request request) {
        List<TransactionDto.RegistTransactionReq> txListDto = request.getTransactionInfoByBlockHeight(block);
        for (TransactionDto.RegistTransactionReq dto : txListDto) {
            transactionController.registTx(dto);
        }
    }
}