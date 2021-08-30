package com.koreait.day3.repository;

import com.koreait.day3.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findFirstByNameOrderByIdDesc(String name);

    Optional<Partner> findByName(String name);

    Optional<Partner> findByCeoName(String ceoname);
}
