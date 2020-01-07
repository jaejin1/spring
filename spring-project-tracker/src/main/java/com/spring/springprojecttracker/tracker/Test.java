package com.spring.springprojecttracker.tracker;


import com.spring.springprojecttracker.api.Block.BlockRestController;


public class Test {

    private BlockRestController blockController;
    private String name;

    public Test(String name) {
        blockController = (BlockRestController) BeanUtils.getBean("blockController");
        this.name = name;
    }

    public void test() {
        Request request = new Request("https://bicon.net.solidwallet.io/api/v3");
        System.out.println(name);
        blockController.getBlock(0L);
    }
}
