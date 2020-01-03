package com.spring.springprojecttracker.domain.block;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, String> {

    Block findFirstByOrderByBlockHeightDesc();

    Block findByBlockHeight(long blockHeight);
}
