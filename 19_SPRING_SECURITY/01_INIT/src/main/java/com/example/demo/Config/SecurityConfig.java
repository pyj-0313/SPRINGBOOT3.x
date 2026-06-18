package com.example.demo.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception
    {
        //CSRF 비활성화(비활성화하지 않으면 logout 요청은 기본적으로 POST방식을 따른다)
        http.csrf((config)->{config.disable();});

        //권한처리
        http.authorizeHttpRequests((auth)->{
            auth.requestMatchers("/","/join","/login").permitAll();
        });


        //로그인


        //로그아웃


        return http.build();
    }
}
