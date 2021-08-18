package com.koreait.day2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data   // 내부적으로 get과set 만들어짐(코딩할 피료음서짐)
@AllArgsConstructor // 얘네들을 다 파라미터로 쓸 수 있는 생성자가 만들어짐
@NoArgsConstructor  // 파라미터가 아무것도 없는 생성자가 만들어짐
@Entity         // JPA를 이용해 DB와 연동시켜 줌 (클래스명과 테이블명 / 변수명과 컬럼명이 동일한 것)
// DB와의 연결은 applicationPoroperties 에서 연결시켜줬음
// build.gradle : 오라클의 메뉴얼에 나와있는 dependency 추가
@SequenceGenerator( // 만들어준 시퀀스를 연결시켜주는 어노테이션
        name="user_seq_user",           // 자바 내애서 지어주는 시퀀스명
        sequenceName = "user_seq_user", // 오라클에 존재하는 시퀀스명
        initialValue = 1,
        allocationSize = 1
)
@Builder        // 메소드체이닝 이용할 수 있게 추가해준 어노테이션
public class AdminUser {
    @Id
    // 작용하고자 하는 얘위에다가 만들어야함 generator에 들어가야 하는 이름은
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_user")
    private Long id;    // 일렬번호
    private String userid;  // 아이디
    private String userpw;  // 비밀버노
    private String name;    // 이름
    private String status;  // 상태
    private LocalDateTime lastLoginAt;  // 마지막 접속시간
    private LocalDateTime regDate;  // 가입날짜
}
