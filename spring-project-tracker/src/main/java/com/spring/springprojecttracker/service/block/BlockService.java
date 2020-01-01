package com.spring.springprojecttracker.service.block;

import com.spring.springprojecttracker.domain.block.Block;
import com.spring.springprojecttracker.domain.block.BlockRepository;
import com.spring.springprojecttracker.dto.block.BlockDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BlockService {
    private final BlockRepository blockRepository;

    public Block create(BlockDto.RegistBlockReq dto) {
        return blockRepository.save(dto.toEntity());
    }
}