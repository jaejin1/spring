package com.spring.springprojecttracker.tracker;

import com.spring.springprojecttracker.dto.block.BlockDto.RegistBlockReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


//@Configuration
//@ConfigurationProperties(prefix = "blockchain")
@Getter
@Setter
public class Tracker {

    private String apiUrl;
    private RegistBlockReq dto;

    public Tracker(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void crawler() {

        Request request = new Request(apiUrl);

        dto = request.getBlock("1");

        System.out.println(dto.getBlockHeight());
        System.out.println(dto.getBlockHash());
        System.out.println(dto.getChannel());
        System.out.println(dto.getPeerId());
    }
}
