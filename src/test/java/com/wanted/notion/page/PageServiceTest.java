package com.wanted.notion.page;

import com.wanted.notion.dto.page.PageResponseDto;
import com.wanted.notion.service.PageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PageServiceTest {

    @Autowired
    private PageService pageService;

    @Test
    public void findByIdTest(){
      Optional<PageResponseDto> reponseDto =  pageService.findByPage(5);
        System.out.println(reponseDto.toString());
    }


}
