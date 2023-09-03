package com.wanted.notion.page;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PageDto {
    private final Long pageId;
    private final String title;
    private final String content;
    private final List<SubPageDto> subPages;
    private final List<SubPageDto> broadCrumbs;
}
