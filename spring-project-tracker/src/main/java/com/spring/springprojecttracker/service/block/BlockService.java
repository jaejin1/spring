package com.spring.springprojecttracker.service.block;

import com.spring.springprojecttracker.domain.block.Block;
import com.spring.springprojecttracker.domain.block.BlockRepository;
import com.spring.springprojecttracker.dto.block.BlockDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BlockService {

    private final BlockRepository blockRepository;

    public Block create(BlockDto.RegistBlockReq dto) {
        return blockRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Block findByBlockHeight(long blockHeight) {
        return blockRepository.findByBlockHeight(blockHeight);
    }

    public Block findLastBlockHeight() {
        return blockRepository.findFirstByOrderByBlockHeightDesc();
    }

    @Transactional(readOnly = true)
    public List<BlockDto.Res> findAllDesc() {
        return blockRepository.findAllByOrderByBlockHeightDesc()
                .map(BlockDto.Res::new)
                .collect(Collectors.toList());
    }
}
