package com.study.springboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//시큐리티 메소드 호출 전에 필터링(보안) 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtUtil jwtConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //RESTful API 서버이므로 csrf보안 비활성화한다
                .csrf( auth -> auth.disable() );

        //csrf 보안을 쿠키방식으로 지정한다
        //.csrf( auth -> auth.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        //스프링 시큐리티에서 세션 관리 상태가 없음으로 구성한다
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ));

        //jwt 보안필터를 추가한다
        //특정 필터 앞에 추가한다
        http.addFilterBefore(new JwtAuthenticationFilter(jwtConfig),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}