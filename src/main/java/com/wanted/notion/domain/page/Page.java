package com.wanted.notion.domain.page;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class Page {
    private int id;
    private String title;
    private String content;
    private int parentId;
}
