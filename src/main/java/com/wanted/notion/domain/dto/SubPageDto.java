package com.wanted.notion.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubPageDto {

    private Long pageId;
    private String title;
}
