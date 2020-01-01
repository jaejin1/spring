package com.spring.springprojecttracker.api;

import com.spring.springprojecttracker.dto.block.BlockDto;
import com.spring.springprojecttracker.service.block.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
