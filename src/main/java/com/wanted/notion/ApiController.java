package com.wanted.notion;

import com.wanted.notion.page.PageDto;
import com.wanted.notion.page.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final PageService service;

    @GetMapping("/post/{id}")
    public PageDto post(@PathVariable("id") Long id) {
        return service.getDto(id);
    }
}
