package com.wanted.notion.mapper.page;

import com.wanted.notion.dto.page.PageSummaryDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PageRowMapper implements RowMapper<PageSummaryDto> {
    @Override
    public PageSummaryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PageSummaryDto.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .build();
    }
}
