package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends Day2ApplicationTests {

    @Autowired  // 아까 만든 repository 를 가져다 쓸수 있게 함
    private AdminUserRepository adminUserRepository;

    @Test // 단위테스트로 사용
    public void create(){
        AdminUser adminUser = AdminUser.builder().userid("admin").userpw("1234").name("관리자").status("use").regDate(LocalDateTime.now()).build();
        // builder()를 이용해 메소드 체이닝 방식으로 setter를 사용해줄 수 있음.
        AdminUser newAdminUser = adminUserRepository.save(adminUser);
    }
}
