package com.wanted.notion.service;

import com.wanted.notion.domain.dao.PageDao;
import com.wanted.notion.domain.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageDao pageDao;

    public PageDto findById(Long pageId) {
        return pageDao.findById(pageId);
    }
}
