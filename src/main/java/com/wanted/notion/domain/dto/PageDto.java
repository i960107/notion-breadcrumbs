package com.wanted.notion.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PageDto {

    private Long pageId;
    private String title;
    private String content;
    private Long parentId;
    private List<SubPageDto> subPages;
    private List<SubPageDto> breadcrumbs;

}
