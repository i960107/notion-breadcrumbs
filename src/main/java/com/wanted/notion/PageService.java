package com.wanted.notion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageService {
    private final PageDao pageDao;

    public PageDto getPage(Long pageId) {
        // 페이지 정보 가져오기
        PageDto page = pageDao.getPage(pageId);

        return page;
    }
}
