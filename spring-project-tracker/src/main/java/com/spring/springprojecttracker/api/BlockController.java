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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

@Component
@RestController
@RequestMapping("block")
@AllArgsConstructor
public class BlockController {

    private BlockService blockService;

    @GetMapping(value = "/{blockHeight}")
    @ResponseStatus(value = HttpStatus.OK)
    public BlockDto.Res getBlock(@PathVariable final long blockHeight) {
        return new BlockDto.Res(blockService.findByBlockHeight(blockHeight));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BlockDto.Res registBlock(@Valid @RequestBody BlockDto.RegistBlockReq dto) {
        return new BlockDto.Res(blockService.create(dto));
    }

    @GetMapping(value = "last")
    @ResponseStatus(value = HttpStatus.OK)
    public Long findLastBlockHeight() {
        if (blockService.findLastBlockHeight() == null){
            return -1L;
        }
        return blockService.findLastBlockHeight().getBlockHeight();
    }
}
