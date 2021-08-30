package com.koreait.day3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration  // 기능은 없고 Enavle을 사용해주기 위해 쓴것
@EnableJpaAuditing  // config 관련한 세팅을 해줄 수 있음 필요하는 어노테이션을 세팅할 수 있다.
public class JpaConfig {
}
