package com.wanted.notion.service;


import com.wanted.notion.domain.page.Page;
import com.wanted.notion.domain.page.PageDao;
import com.wanted.notion.dto.page.PageSummaryDto;
import com.wanted.notion.dto.page.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PageService {
    private final PageDao pageDao;

    @Cacheable(value = "page", key = "#id")
    @Transactional(readOnly = true)
    public PageResponseDto findByPage(int id) {

        Page page = pageDao.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 페이지가 없습니다. id=" + id));

        List<PageSummaryDto> breadcrumbs = pageDao.findByBreadCrumbs(id);
        List<PageSummaryDto> subpages = pageDao.findBySubPages(id);

        return PageResponseDto.builder()
                .pageId(page.getId())
                .title(page.getTitle())
                .content(page.getContent())
                .subPages(subpages)
                .breadcrumbs(breadcrumbs)
                .build();

    }
}
