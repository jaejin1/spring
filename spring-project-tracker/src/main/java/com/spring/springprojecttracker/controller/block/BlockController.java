package com.spring.springprojecttracker.controller.block;

import com.spring.springprojecttracker.service.block.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
