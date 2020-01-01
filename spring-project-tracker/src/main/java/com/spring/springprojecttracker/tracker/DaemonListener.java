package com.spring.springprojecttracker.tracker;


import com.spring.springprojecttracker.dto.block.BlockDto;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DaemonListener implements ServletContextListener, Runnable {
    private Thread thread;
    private boolean isShutdown = false;

    private ServletContext sc;

    public void startDaemon() {
        if (thread == null) {
            thread = new Thread(this, "Daemon thread for background task");
        }

        if (!thread.isAlive()) {
            thread.start();
        }
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        Request request = new Request("https://bicon.net.solidwallet.io/api/v3");
        BlockDto.RegistBlockReq dto;

        while (currentThread == thread && !this.isShutdown) {
            try {
                System.out.println("== DaemonListener is running. ==");

                dto = request.getBlock("1");

                System.out.println(dto.getBlockHeight());
                System.out.println(dto.getBlockHash());
                System.out.println(dto.getChannel());
                System.out.println(dto.getPeerId());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("== DaemonListener end. ==");
    }

    public void contextInitialized (ServletContextEvent event) {
        System.out.println("== DaemonListener.contextInitialized has been called. ==");
        sc = event.getServletContext();
        startDaemon();
    }

    public void contextDestroyed (ServletContextEvent event) {
        System.out.println("== DaemonListener.contextDestroyed has been called. ==");
        this.isShutdown = true;
        try {
            thread.join();
            thread = null;
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
