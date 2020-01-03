package com.spring.springprojecttracker.tracker;

import com.spring.springprojecttracker.api.BlockController;
import com.spring.springprojecttracker.api.TransactionController;
import com.spring.springprojecttracker.dto.block.BlockDto;
import com.spring.springprojecttracker.dto.transaction.TransactionDto;
import foundation.icon.icx.data.Block;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class DaemonListener implements ServletContextListener, Runnable
{

    private BlockController blockController;
    private TransactionController transactionController;

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
        Request request = new Request("https://bicon.net.solidwallet.io/api/v3");
        blockController = (BlockController) BeanUtils.getBean("blockController");
        transactionController = (TransactionController) BeanUtils.getBean("transactionController");

        while (currentThread == thread && !this.isShutdown) {
            try {
                System.out.println ("== DaemonListener is running. ==");

                Long blockHeightInDB = GetCurrentBlockHeightInDB();
                Long blockHeight = request.getLastBlockHeight();
                System.out.println(blockHeightInDB);
                System.out.println(blockHeight);
                Block block = GetBlockInBlockChain(request, blockHeightInDB + 1);

                if (blockHeight == null || block == null) {
                    continue;
                }

                if (blockHeightInDB < blockHeight) {
                    System.out.println("you need to crawl in testchannel");
                    StoreBlock(block, request);
                    StoreTx(block, request);
                } else {
                    System.out.println("Don't need to crawl in testchannel");
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println ("== DaemonListener end. ==");
    }
    /** 컨텍스트 초기화 시 데몬 스레드를 작동한다 */
    public void contextInitialized (ServletContextEvent event) {
        System.out.println ("== DaemonListener.contextInitialized has been called. ==");
        sc = event.getServletContext();
        startDaemon();
    }
    /** 컨텍스트 종료 시 thread를 종료시킨다 */
    public void contextDestroyed (ServletContextEvent event) {
        System.out.println ("== DaemonListener.contextDestroyed has been called. ==");
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

    public Long GetCurrentBlockHeightInDB() {
        Long blockHeightInDB = blockController.findLastBlockHeight();
        System.out.println(blockHeightInDB);
        return blockHeightInDB;
    }

    public Block GetBlockInBlockChain(Request request, Long blockHeight) {
        return request.getBlock(blockHeight);
    }

    public void StoreBlock(Block block, Request request) {
        BlockDto.RegistBlockReq dto = request.getBlockInfo(block);
        blockController.registBlock(dto);
    }

    public void StoreTx(Block block, Request request) {
        List<TransactionDto.RegistTransactionReq> txListDto = request.getTransactionInfoByBlockHeight(block);
        for (int txCount = 0; txCount < txListDto.size(); txCount++) {
            transactionController.registTx(txListDto.get(txCount));
        }
    }
}