package com.wanted.notion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.wanted.notion.dto.BreadcrumbDto;
import com.wanted.notion.dto.PageDto;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PageServiceTest {
    @Autowired
    PageService pageService;

    @Test
    void test_get_page_with_breadcrumbs() {
        Long pageId = 3L;
        PageDto pageDto = pageService.getPage(pageId);

        assertEquals(pageId, pageDto.getPageId());

        assertEquals(2, pageDto.getSubpages().size());

        assertEquals(3, pageDto.getBreadcrumbs().size());

        assertNotNull(pageDto.getBreadcrumbs().get(0).getId());
        assertNotNull(pageDto.getBreadcrumbs().get(0).getTitle());

        //breadcrumbs는 현재 경로 포함
        LinkedList<BreadcrumbDto> linkedList = (LinkedList<BreadcrumbDto>) pageDto.getBreadcrumbs();
        assertEquals(pageId, linkedList.getLast().getId());

        assertNotNull(pageDto.getSubpages().get(0).getTitle());
        assertNotNull(pageDto.getSubpages().get(0).getId());
    }

    @Test
    void test_get_non_exist_page() {
        Long pageId = 10L;
        assertNull(pageService.getPage(pageId));
    }

}