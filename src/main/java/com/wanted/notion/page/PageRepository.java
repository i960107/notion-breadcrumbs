package com.wanted.notion.page;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PageRepository {

    private final static int MAX_BROADCRUMBS_DEPTH = 4;
    private final JdbcTemplate jdbcTemplate;

    public Page findById(Long id) {
        return findById(id, 0);
    }

    public Page findById(Long id, int count) {
        if (count >= MAX_BROADCRUMBS_DEPTH) {
            return null;
        }

        String query = "select title, content, parent_id from Page where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[] { id }, (rs, rowNum) -> {
            String title = rs.getString("title");
            String content = rs.getString("content");
            String parentId = rs.getString("parent_id");
            return Page.toEntity(
                    id,
                    title,
                    content,
                    parentId != null ? findById(Long.parseLong(parentId), count+1) : null
            );
        });
    }

    public List<Page> findSubPages(Long parentId) {
        String query = "select id, title, content from Page where parent_id = ?";
        return jdbcTemplate.query(query, new Object[] { parentId }, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            return Page.toEntity(
                    id,
                    title
            );
        });
    }
}
