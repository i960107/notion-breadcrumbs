package com.wanted.notion;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDto {
    private Long pageId;
    private String title;
    private String content;
    private Long parentId;
    private List<PageInfoDto> subpages;
    private List<PageInfoDto> breadcrumbs;
}
