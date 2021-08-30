package com.koreait.day3.model.entity;

import com.koreait.day3.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name = "seq_item",
        sequenceName = "seq_item",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;  // Enum 클래스 타입으로 변경해줌
    //    private String status;
    private String title;
    private String content;
    private BigDecimal price;
    @CreatedDate
    private LocalDateTime regDate;
    @CreatedBy
    private String createBy;
    @LastModifiedDate
    private LocalDateTime updateDate;
    @LastModifiedBy
    private String updateBy;

    //    private Long partnerId;
    @ManyToOne
    private Partner partner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}

