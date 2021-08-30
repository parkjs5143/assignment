package com.koreait.day3.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component  // 모든 클래스에 적용시키게 된다. 원해 조각조각나있는 컴포넌트를 어노테이션으로 전체에 적용되게 함
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {    // 들어온 문자열이
        return Optional.of("최고 관리자.");      // 문자가 등록되는 곳에 Oprional이 자동으로 들어감
    }
}
