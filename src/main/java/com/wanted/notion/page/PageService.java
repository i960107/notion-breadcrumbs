package com.wanted.notion.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PageService {

    private final PageRepository repository;

    public PageDto getDto(Long id) {
        isValid(id);

        Page page = get(id);
        List<Page> subPages = repository.findSubPages(page.getId());
        List<Page> broadCrumbs = page.findBroadCrumbs();

        return page.toDto(subPages, broadCrumbs);
    }

    private Page get(Long id) {
        return repository.findById(id);
    }

    private void isValid(Object input) {
        if (input == null) {
            throw new IllegalArgumentException("cannot be null");
        }
    }
}
