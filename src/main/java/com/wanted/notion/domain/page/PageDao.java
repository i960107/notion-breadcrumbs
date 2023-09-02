package com.wanted.notion.domain.page;

import com.wanted.notion.dto.BreadcrumbDto;
import com.wanted.notion.dto.PageDto;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageDao {
    private final JdbcTemplate jdbcTemplate;

    public PageDto getPage(Long pageId) {
        String query = "SELECT "
                + "p1.page_id as page_id, "
                + "p1.title as title, "
                + "p1.content as content, "
                + "p2.page_id as subpage_id, "
                + "p2.title as subpage_title "
                + "FROM PAGE p1 INNER JOIN Page p2 "
                + "ON p1.page_id = p2.parent_id "
                + "WHERE p1.page_id = " + pageId.toString();

        return jdbcTemplate.query(query, rs -> {
            PageDto pageDto = null;
            while (rs.next()) {
                if (pageDto == null) {
                    pageDto = PageDto.builder()
                            .pageId(rs.getLong("page_id"))
                            .title(rs.getString("title"))
                            .content(rs.getString("content"))
                            .build();
                }
                pageDto.addSubpage(BreadcrumbDto.builder()
                        .id(rs.getLong("subpage_id"))
                        .title(rs.getString("subpage_title"))
                        .build()
                );
            }
            return pageDto;
        });
    }

    public List<BreadcrumbDto> getBreadcrumbs(Long pageId) {
        String query = "SELECT page_id, title, parent_id FROM PAGE WHERE page_id = ";
        List<BreadcrumbDto> breadcrumbs = new LinkedList<>();
        do {
            pageId = jdbcTemplate.queryForObject(query + pageId.toString(), (rs, rows) -> {
                        breadcrumbs.add(0, BreadcrumbDto.builder()
                                .id(rs.getLong("page_id"))
                                .title(rs.getString("title"))
                                .build());
                        return rs.getLong("parent_id");
                    }
            );
        } while (pageId != 0);
        return breadcrumbs;
    }
}
