package com.koreait.day3.model.network.response;

import com.koreait.day3.model.entity.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiResponse {
    private Long id;
    private String type;
    private String title;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String updateBy;
    private List<Partner> partnerList;
}
