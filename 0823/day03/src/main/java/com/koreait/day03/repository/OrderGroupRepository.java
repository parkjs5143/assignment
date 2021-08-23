package com.koreait.day03.repository;

import com.koreait.day03.model.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
    Optional<OrderGroup> findFirstByRevNameOrderByIdDesc(String name);

    Optional<OrderGroup> findByRevAddress(String revaddress);

    Optional<OrderGroup> findByTotalPrice(BigDecimal totalprice);

    Optional<OrderGroup> findById(Long Id);
}
