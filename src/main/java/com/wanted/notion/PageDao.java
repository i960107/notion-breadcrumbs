package com.wanted.notion;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PageDao {
    private final JdbcTemplate jdbcTemplate;

    @Cacheable("pages")
    public PageDto getPage(Long pageId) {
        String mainSql = "SELECT p.PAGE_ID, p.TITLE, p.CONTENT, p.PARENT_ID " +
                "FROM PAGE p " +
                "WHERE p.PAGE_ID = ?";
        String subpagesSql = "SELECT PAGE_ID, TITLE " +
                "FROM PAGE " +
                "WHERE PARENT_ID = ?";
        PageDto page = jdbcTemplate.queryForObject(mainSql, new Object[]{pageId}, (rs, rowNum) -> {
            PageDto p = new PageDto();
            p.setPageId(rs.getLong("PAGE_ID"));
            p.setTitle(rs.getString("TITLE"));
            p.setContent(rs.getString("CONTENT"));
            p.setParentId(rs.getLong("PARENT_ID"));
            p.setSubpages(jdbcTemplate.query(subpagesSql, new Object[]{pageId}, (subRs, subRowNum) -> {
                PageInfoDto subpage = new PageInfoDto();
                subpage.setPageId(subRs.getLong("PAGE_ID"));
                subpage.setTitle(subRs.getString("TITLE"));
                return subpage;
            }));
            p.setBreadcrumbs(getBreadcrumbs(pageId));
            return p;
        });
        return page;
    }

    public List<PageInfoDto> getBreadcrumbs(Long pageId) {
        List<PageInfoDto> breadcrumbs = new ArrayList<>();
        final Long[] currentId = {pageId};
        int limit = 4;
        while (currentId[0] != null && breadcrumbs.size() < limit) {
            String sql = "SELECT PAGE_ID, TITLE, PARENT_ID FROM PAGE WHERE PAGE_ID = ?";
            List<PageInfoDto> results = jdbcTemplate.query(sql, new Object[]{currentId[0]}, (rs, rowNum) -> {
                PageInfoDto info = new PageInfoDto();
                info.setPageId(rs.getLong("PAGE_ID"));
                info.setTitle(rs.getString("TITLE"));
                currentId[0] = rs.getLong("PARENT_ID");
                return info;
            });
            if (!results.isEmpty()) {
                breadcrumbs.addAll(results);
            } else {
                break;
            }
        }
        Collections.reverse(breadcrumbs);
        return breadcrumbs;
    }
}