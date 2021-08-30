package com.koreait.day3.repository;

import com.koreait.day3.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);

    Optional<Category> findFirstByTypeOrderByIdDesc(String type);
}
