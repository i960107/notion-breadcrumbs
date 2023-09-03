package com.wanted.notion.mapper.page;

import com.wanted.notion.domain.page.Page;
import org.springframework.jdbc.core.RowMapper;

public class DetailPageRowMapper implements RowMapper<Page>{
    @Override
    public Page mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        return Page.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .build();
    }
}
