package com.spring.springprojecttracker.api;

import com.spring.springprojecttracker.dto.block.BlockDto;
import com.spring.springprojecttracker.service.block.BlockService;
import com.spring.springprojecttracker.tracker.Request;
import foundation.icon.icx.IconService;
import foundation.icon.icx.data.Block;
import foundation.icon.icx.transport.http.HttpProvider;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("block")
@AllArgsConstructor
public class blockController {

    private BlockService blockService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String test() {
        return "Hello";
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BlockDto.Res registBlock(@Valid @RequestBody BlockDto.RegistBlockReq dto) {
        return new BlockDto.Res(blockService.create(dto));
    }

    @GetMapping(value = "test")
    @ResponseStatus(value = HttpStatus.OK)
    public String ang() {

        Request request = new Request("https://bicon.net.solidwallet.io/api/v3");
        BlockDto.RegistBlockReq dto;
        dto = request.getBlock("1");

        System.out.println(dto.getBlockHeight());
        System.out.println(dto.getBlockHash());
        System.out.println(dto.getChannel());
        System.out.println(dto.getPeerId());
        return "test";
    }
}
