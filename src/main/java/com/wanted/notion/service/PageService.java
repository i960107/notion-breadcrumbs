package com.wanted.notion.service;

import com.wanted.notion.domain.page.PageDao;
import com.wanted.notion.dto.BreadcrumbDto;
import com.wanted.notion.dto.PageDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageService {
    private final PageDao pageDao;

    public PageDto getPage(Long pageId) {
        PageDto pageDto = pageDao.getPage(pageId);

        if (pageDto != null) {
            List<BreadcrumbDto> breadcrumbs = pageDao
                    .getBreadcrumbs(pageDto.getPageId());
            pageDto.addBreadcrumbs(breadcrumbs);

            return pageDto;
        }
        return pageDto;
    }
}
