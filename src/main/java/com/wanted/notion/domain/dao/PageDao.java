package com.wanted.notion.domain.dao;

import com.wanted.notion.domain.dto.SubPageDto;
import com.wanted.notion.domain.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PageDao {

    private final JdbcTemplate jdbcTemplate;

    public PageDto findById(Long pageId) {
        String query = "SELECT * FROM page WHERE pageId = ?";
        return jdbcTemplate.queryForObject(query, pageDtoMapper, pageId);
    }

    private RowMapper<PageDto> pageDtoMapper = ((rs, rowNum) ->
            PageDto.builder()
                    .pageId(rs.getLong("pageId"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .parentId(rs.getLong("parentId") == 0 ? null : rs.getLong("parentId"))
                    .subPages(getSubPages(rs.getLong("pageId")))
                    .breadcrumbs(getBreadcrumbs(rs.getLong("parentId")))
                    .build()
    );

    /**
     * 입력된 pageId를 부모로 갖는 subPage List return
     */
    private List<SubPageDto> getSubPages(Long pageId) {
        String query = "SELECT pageId, title FROM page WHERE parentId = ?";

        return jdbcTemplate.query(query, ((rs, rowNum) ->
                SubPageDto.builder()
                        .pageId(rs.getLong("pageId"))
                        .title(rs.getString("title"))
                        .build()
        ), pageId);
    }

    /**
     * 입력된 Page의 parentId를 pageId로 갖는 Page,
     * 다시 그 Page의 parentId를 page로 갖는 Page, ... 를 List로 만들어 return
     */
    private List<SubPageDto> getBreadcrumbs(Long parentId) {
        List<SubPageDto> breadcrumbs = new ArrayList<>();

        while (parentId != 0) {
            String query = "SELECT pageId, title, parentId FROM page WHERE pageId = ?";

            parentId = jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                     breadcrumbs.add(
                             SubPageDto.builder()
                            .pageId(rs.getLong("pageId"))
                            .title(rs.getString("title"))
                            .build()
                     );
                    return rs.getLong("parentId");
            }, parentId);
        }

        return breadcrumbs;
    }

}
