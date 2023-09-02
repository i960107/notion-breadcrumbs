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
    private static final int MAX_RETRIES = 3;
    private static String GET_CRUMB_QUERY = "SELECT "
            + "page_id, title, parent_id "
            + "FROM PAGE WHERE page_id = ";
    private static String GET_PAGE_QUERY = "SELECT "
            + "p1.page_id as page_id, "
            + "p1.title as title, "
            + "p1.content as content, "
            + "p2.page_id as subpage_id, "
            + "p2.title as subpage_title "
            + "FROM PAGE p1 INNER JOIN Page p2 "
            + "ON p1.page_id = p2.parent_id "
            + "WHERE p1.page_id = ";


    public PageDto getPage(Long pageId) {

        return jdbcTemplate.query(GET_PAGE_QUERY + pageId, rs -> {
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
        List<BreadcrumbDto> breadcrumbs = new LinkedList<>();
        do {
            pageId = retryAddCrumbUntilSucceed(pageId, breadcrumbs);
        } while (pageId != 0);
        return breadcrumbs;
    }

    private Long retryAddCrumbUntilSucceed(Long pageId, List<BreadcrumbDto> breadcrumbs) {
        for (int i = 0; i <= MAX_RETRIES; i++) {
            try {
                return addCrumb(pageId, breadcrumbs);
            } catch (Exception e) {
                if (i == MAX_RETRIES) {
                    throw e;
                }
            }
        }
        return null;
    }

    private Long addCrumb(Long pageId, List<BreadcrumbDto> breadcrumbs) {
        BreadcrumbDto crumb = jdbcTemplate.queryForObject(GET_CRUMB_QUERY + pageId.toString(),
                (rs, rows) -> BreadcrumbDto.builder()
                        .id(rs.getLong("page_id"))
                        .title(rs.getString("title"))
                        .parentId(rs.getLong("parent_id"))
                        .build()
        );
        breadcrumbs.add(0, crumb);
        return crumb.getParentId();
    }

}
