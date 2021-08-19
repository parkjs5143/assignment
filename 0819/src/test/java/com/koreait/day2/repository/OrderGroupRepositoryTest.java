package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.OrderGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup = OrderGroup.builder()
                .orderType("ALL")
                .status("결제완료")
                .revAddress("서울시 서초구 양재동")
                .revName("김사과")
                .paymentType("카드")
                .totalPrice(BigDecimal.valueOf(4500000))
                .totalQuantity(2)
                .regDate(LocalDateTime.now())
                .orderAt(LocalDateTime.now())
                .userid(3L)
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
    }
}
