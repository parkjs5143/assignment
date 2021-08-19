package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class CategoryRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    // 자동차2, 컴퓨터2, 가전2
    @Test
    public void create(){
        Category category = Category.builder()
                .type("가전")
                .title("엘지 냉장고")
                .regDate(LocalDateTime.now())
                .build();

        Category newCategory = categoryRepository.save(category);
    }
}
