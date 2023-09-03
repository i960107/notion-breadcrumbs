package com.wanted.notion.dto.page;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PageResponseDto {
    private int pageId;
    private String title;
    private String content;
    private List<PageSummaryDto> subPages;
    private List<PageSummaryDto> breadcrumbs;


    @Override
    public String toString() {
        return "ResponseDto{" +
                "pageId=" + pageId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", subPages=" + (subPages == null ? "null" : subPages.stream().map(page -> "{" + "id=" + page.getId() + ", title='" + page.getTitle() + "'}").collect(Collectors.joining(", "))) +
                ", breadcrumbs=" + (breadcrumbs == null ? "null" : breadcrumbs.stream().map(page -> "{" + "id=" + page.getId() + ", title='" + page.getTitle() + "'}").collect(Collectors.joining(", "))) +
                '}';
    }


}
