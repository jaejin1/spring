package com.spring.springprojecttracker.domain.block;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface BlockRepository extends JpaRepository<Block, String> {

    Stream<Block> findAllByOrderByBlockHeightDesc();

    Block findFirstByOrderByBlockHeightDesc();

    Block findByBlockHeight(long blockHeight);
}
