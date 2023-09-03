package com.wanted.notion;

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

    @GetMapping("/{page-id}")
    public ResponseEntity<PageDto> getPage(@PathVariable("page-id") Long pageId) {
        return ResponseEntity.ok(pageService.getPage(pageId));
    }
}
