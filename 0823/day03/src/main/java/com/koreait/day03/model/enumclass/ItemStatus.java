package com.koreait.day03.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {
    REGISTERED(0, "등록", "상품 등록 상태"),
    UNREGISTERED(1, "중지", "상품 판매 중지상태"),
    WAITING(2, "대기", "상품 검수 상태");
    private Integer id;
    private String title;
    private String description;
}
