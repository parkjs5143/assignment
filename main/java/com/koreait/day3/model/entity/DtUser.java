package com.koreait.day3.model.entity;

import com.koreait.day3.model.enumclass.UserStatus;
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

// DTO만들기 -> 테이블 만들기 -> 시퀀스 만들기 -> repository에서 JpaRepository상속받는 인터페이스 repository만들기
// -> test에서 test클래스 만들기 repository 상속받음 create메소드 생성
// -> beans 사용할 수 있도록 @Autowired 로 uesersRepositoey 생성
// create바로 위에 @Test 사용할 수 있도록
// 어노테이션들이 서로 떨어지지 않게 해야함 바로아래에 클래스에 적용될 수 있도록
@Data   // 내부적으로 get과set 만들어짐(코딩할 피료음서짐)
@AllArgsConstructor // 얘네들을 다 파라미터로 쓸 수 있는 생성자가 만들어짐
@NoArgsConstructor  // 파라미터가 아무것도 없는 생성자가 만들어짐
@Entity         // JPA를 이용해 DB와 연동시켜 줌 (클래스명과 테이블명 / 변수명과 컬럼명이 동일한 것)
@SequenceGenerator( // 만들어준 시퀀스를 연결시켜주는 어노테이션
        name = "seq_user",           // 자바 내애서 지어주는 시퀀스명
        sequenceName = "seq_user", // 오라클에 존재하는 시퀀스명
        initialValue = 1,
        allocationSize = 1
)
@Builder        // 메소드체이닝 이용할 수 있게 추가해준 어노테이션
@EntityListeners(AuditingEntityListener.class)
public class DtUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    private Long id;
    private String userid;
    private String userpw;
    private String hp;
    private String email;
    @CreatedDate
    private LocalDateTime regDate;
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private UserStatus status;  // enum 타입 REGISTERED, UNRESISTERED 로 등록가능

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dtUser")
    private List<OrderGroup> orderGroupsList;
}
