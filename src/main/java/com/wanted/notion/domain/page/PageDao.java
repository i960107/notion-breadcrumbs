package com.wanted.notion.domain.page;

import com.wanted.notion.dto.page.PageSummaryDto;
import com.wanted.notion.mapper.page.DetailPageRowMapper;
import com.wanted.notion.mapper.page.PageRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PageDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_BY_ID_SQL = "SELECT id,title,content FROM pages WHERE id = ?";
    private static final String FIND_BREADCRUMBS_SQL =
            "WITH RECURSIVE bread_crumb_path(id, title, parentId) AS ( " +
                    "SELECT id, title, parentId FROM pages WHERE id = ? " +
                    "UNION ALL " +
                    "SELECT p.id, p.title, p.parentId " +
                    "FROM pages p " +
                    "JOIN bread_crumb_path bc ON p.id = bc.parentId " +
                    "WHERE bc.parentId is not null) " +
                    "SELECT id, parentId, title FROM bread_crumb_path;";
    private static final String FIND_SUBPAGES_SQL = "SELECT id, title FROM pages WHERE parentId = ?";

    public Optional<Page> findById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID_SQL, new DetailPageRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    public List<PageSummaryDto> findByBreadCrumbs(int id) {
        return jdbcTemplate.query(FIND_BREADCRUMBS_SQL, new Object[]{id}, new PageRowMapper());
    }

    public List<PageSummaryDto> findBySubPages(int id) {
        return jdbcTemplate.query(FIND_SUBPAGES_SQL, new Object[]{id}, new PageRowMapper());
    }
}

