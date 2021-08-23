package com.koreait.day03.repository;

import com.koreait.day03.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findFirstByNameOrderByIdDesc(String name);

    Optional<Item> findByName(String name);

    Optional<Item> findById(Long Id);
}
