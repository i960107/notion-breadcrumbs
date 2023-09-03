package com.wanted.notion.service;


import com.wanted.notion.domain.page.Page;
import com.wanted.notion.domain.page.PageDao;
import com.wanted.notion.dto.page.PageSummaryDto;
import com.wanted.notion.dto.page.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PageService {
    private final PageDao pageDao;


    @Transactional(readOnly = true)
    public Optional<PageResponseDto> findByPage(int id) {

        Optional<Page> pageOptional = pageDao.findById(id);

        if (pageOptional.isPresent()) {
            Page page = pageOptional.get();
            List<PageSummaryDto> breadcrumbs = pageDao.findByBreadCrumbs(id);
            List<PageSummaryDto> subpages = pageDao.findBySubPages(id);

            PageResponseDto response = PageResponseDto.builder()
                    .pageId(page.getId())
                    .title(page.getTitle())
                    .content(page.getContent())
                    .subPages(subpages)
                    .breadcrumbs(breadcrumbs)
                    .build();

            return Optional.ofNullable(response);
        }
      return Optional.empty();
    }
}
