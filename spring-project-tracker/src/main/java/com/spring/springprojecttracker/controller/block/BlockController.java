package com.spring.springprojecttracker.controller.block;

import com.spring.springprojecttracker.dto.block.BlockDto;
import com.spring.springprojecttracker.service.block.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("block")
@AllArgsConstructor
public class BlockController {

    private BlockService blockService;

    @GetMapping
    public String block(Model model) {
        model.addAttribute("block", blockService.findAllDesc());
        return "/block";
    }

    @GetMapping(value = "/{blockHeight}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BlockDto.Res getBlock(@PathVariable final long blockHeight) {
        return new BlockDto.Res(blockService.findByBlockHeight(blockHeight));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public BlockDto.Res registBlock(@Valid @RequestBody BlockDto.RegistBlockReq dto) {
        return new BlockDto.Res(blockService.create(dto));
    }

    @GetMapping(value = "last")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Long findLastBlockHeight() {
        if (blockService.findLastBlockHeight() == null) {
            return -1L;
        }
        return blockService.findLastBlockHeight().getBlockHeight();
    }
}
