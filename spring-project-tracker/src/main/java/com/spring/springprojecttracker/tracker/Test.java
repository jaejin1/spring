package com.spring.springprojecttracker.tracker;


import com.spring.springprojecttracker.api.BlockController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public class Test {

    private BlockController blockController;
    private String name;

    public Test(String name) {
        blockController = (BlockController) BeanUtils.getBean("blockController");
        this.name = name;
    }

    public void test() {
        Request request = new Request("https://bicon.net.solidwallet.io/api/v3");
        System.out.println(name);
        blockController.getBlock(0L);
    }
}
