package com.koreait.day03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name = "seq_partner",
        sequenceName = "seq_partner",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="seq_partner" )
    private Long id;
    private String name;
    private String status;
    private String address;
    private String callCenter;
    private String businessNumber;
    private String ceoName;
    @CreatedDate
    private LocalDateTime regDate;
    @LastModifiedDate
    private LocalDateTime updateDate;
//    private Long categoryId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    private List<Item> itemList;



}
