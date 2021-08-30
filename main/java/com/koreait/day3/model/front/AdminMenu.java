package com.koreait.day3.model.front;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminMenu {
    private String title;
    private String url;
    private String code;
    private String img;
}
