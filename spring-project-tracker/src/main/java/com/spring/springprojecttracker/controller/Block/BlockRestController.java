package com.spring.springprojecttracker.controller.Block;

import com.spring.springprojecttracker.dto.block.BlockDto;
import com.spring.springprojecttracker.service.block.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Component
@RestController
@RequestMapping("block")
@AllArgsConstructor
public class BlockRestController {

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
