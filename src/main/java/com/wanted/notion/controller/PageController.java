package com.wanted.notion.controller;

import com.wanted.notion.domain.dto.PageDto;
import com.wanted.notion.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping("/{pageId}")
    public ResponseEntity<PageDto> findById(@PathVariable Long pageId) {
        return ResponseEntity.ok(pageService.findById(pageId));
    }
}
