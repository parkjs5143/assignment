package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PartnerRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){
        Partner partner = Partner.builder()
                .name("베스트샵")
                .status("사용중")
                .address("서울시 금천구")
                .callCenter("070-4444-4444")
                .businessNumber("444-44-44444")
                .ceoName("오지환")
                .regDate(LocalDateTime.now())
                .categoryId(6L)
                .build();
        Partner newPartner = partnerRepository.save(partner);
    }
}
