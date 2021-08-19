package com.koreait.day2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_user",
        sequenceName = "seq_user",
        initialValue = 1,
        allocationSize = 1
)
@Builder
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    private Long id;        // 일렬번호
    private String userid;  // 아이디
    private String userpw;  // 비밀번호
    private String name;    // 이름
    private String status;  // 상태
    private LocalDateTime lastLoginAt;  // 마지막 접속시간
    private LocalDateTime regDate;      // 가입 날짜
}
