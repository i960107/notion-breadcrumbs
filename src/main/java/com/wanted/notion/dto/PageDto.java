package com.wanted.notion.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDto {
    private Long pageId;
    private String title;
    private String content;
    private List<BreadcrumbDto> subpages;
    private List<BreadcrumbDto> breadcrumbs;

    @Builder
    public PageDto(Long pageId, String title, String content) {
        this.pageId = pageId;
        this.title = title;
        this.content = content;
        this.subpages = new ArrayList<>();
        this.breadcrumbs = new ArrayList<>();
    }

    public void addSubpage(BreadcrumbDto pageCrumb) {
        this.subpages.add(pageCrumb);
    }

    public void addBreadcrumbs(List<BreadcrumbDto> breadCrumbs) {
        this.breadcrumbs = breadCrumbs;
    }
}
