package com.wanted.notion.page;

import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@ToString
public class Page {
    private Long id;
    private String title;
    private String content;
    private Page parent;

    private Page(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Page(Long id, String title, String content, Page parent) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.parent = parent;
    }

    public static Page toEntity(Long id, String title) {
        return new Page(id, title);
    }

    public static Page toEntity(Long id, String title, String content, Page parent) {
        return new Page(id, title, content, parent);
    }

    public PageDto toDto(List<Page> subPages, List<Page> broadCrumbs) {
        List<SubPageDto> subPageDtos = subPages.stream().map(page -> page.toSubDto()).collect(Collectors.toList());
        List<SubPageDto> broadCrumbDtos = broadCrumbs.stream().map(page -> page.toSubDto()).collect(Collectors.toList());

        return new PageDto(getId(), getTitle(), getContent(), subPageDtos, broadCrumbDtos);
    }

    public SubPageDto toSubDto() {
        return new SubPageDto(getId(), getTitle());
    }

    public List<Page> findBroadCrumbs() {
        List<Page> list = Stream.iterate(this, Objects::nonNull, p -> p.getParent()).collect(Collectors.toList());
        Collections.reverse(list);

        return list;
    }
}
