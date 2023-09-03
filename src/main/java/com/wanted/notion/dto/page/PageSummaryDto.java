package com.wanted.notion.dto.page;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageSummaryDto {
    private int id;
    private String title;
}
