package com.wanted.notion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class InsertSqlFormatting {

    @Test
    public void generateSQLStatements() {
        Random random = new Random();

        for (int i = 1; i <= 1000; i++) {
            String title = generateTitle(i);
            String content = generateContent(i);
            int parentId;
            if (i <= 50) {
                parentId = 0;
            } else {
                parentId = random.nextInt(10) + 1;
            }

            System.out.printf("INSERT INTO pages (id, title, content, parentId) VALUES (%d, '%s', '%s', %d);%n", i, title, content, parentId);
        }
    }

    private String generateTitle(int num) {
        return "Title" + num;
    }

    private String generateContent(int num) {
        return "Content" + num + ".";
    }
}


