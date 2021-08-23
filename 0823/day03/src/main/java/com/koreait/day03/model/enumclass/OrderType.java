package com.koreait.day03.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
    ALL(0, "묶음", "모든 상품을 묶어 배송"),
    EACH(1, "개별", "모든 상품을 각각 준비되는대로 발송");

    private Integer id;
    private String title;
    private String description;
}
