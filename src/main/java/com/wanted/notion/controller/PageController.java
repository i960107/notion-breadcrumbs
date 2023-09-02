package com.wanted.notion;

import com.wanted.notion.domain.dao.PageDao;
import com.wanted.notion.domain.dto.PageDto;
import com.wanted.notion.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/page")
@RequiredArgsConstructor
public class TestController {

    private final PageService pageService;

    @GetMapping("/{pageId}")
    public ResponseEntity<PageDto> findById(@PathVariable Long pageId) throws SQLException {
        return ResponseEntity.ok(pageService.findById(pageId));
    }
}
