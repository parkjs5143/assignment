package com.koreait.day03.model.network.response;

import com.koreait.day03.model.enumclass.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderGroupApiResponse {
    private Long id;
    private OrderType orderType;
    private String status;
    private String revAddress;
    private String revName;
    private String paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    private LocalDateTime regDate;
}
