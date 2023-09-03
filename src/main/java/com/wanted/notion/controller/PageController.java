package com.wanted.notion.controller;


import com.wanted.notion.dto.page.PageResponseDto;
import com.wanted.notion.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/pages")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping("/{id}")
    public PageResponseDto findByPage(@PathVariable int id) {
        PageResponseDto response = pageService.findByPage(id);
        return response;
    }
}


