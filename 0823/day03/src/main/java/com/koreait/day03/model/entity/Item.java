package com.koreait.day03.model.entity;

import com.koreait.day03.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="seq_item" )
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
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
}
