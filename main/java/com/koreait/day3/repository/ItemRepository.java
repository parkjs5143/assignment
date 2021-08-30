package com.koreait.day3.repository;

import com.koreait.day3.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // select * from item where status = ? order by price desc
    Optional<Item> findFirstByNameOrderByIdDesc(String name);

    Optional<Item> findByName(String name);

    Optional<Item> findById(Long Id);
}
