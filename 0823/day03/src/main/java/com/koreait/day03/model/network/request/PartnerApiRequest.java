package com.koreait.day03.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerApiRequest {
    private Long id;
    private String name;
    private String status;
    private String address;
    private String callCenter;
    private String businessNumber;
    private String ceoName;
    private LocalDateTime regDate;
}
