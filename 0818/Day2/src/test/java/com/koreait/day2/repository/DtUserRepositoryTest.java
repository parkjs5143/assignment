package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.DtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

class DtUserRepositoryTest extends Day2ApplicationTests {
    @Autowired  // 아까 만든 repository 를 가져다 쓸수 있게 함
    private DtUserRepository dtUserRepository;

    @Test // 단위테스트로 사용
    public void create(){
        DtUser user = DtUser.builder().userid("apple").userpw("12345").hp("010-1111-1111").email("apple@apple.com").updateDate(LocalDateTime.now()).build();
        // builder()를 이용해 메소드 체이닝 방식으로 setter를 사용해줄 수 있음.
        DtUser newDtUser = dtUserRepository.save(user);
    }
}
