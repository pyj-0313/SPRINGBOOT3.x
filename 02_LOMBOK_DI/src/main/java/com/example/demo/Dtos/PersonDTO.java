package com.example.demo.Dtos;

// LOMBOK 확인 CTRL + F12
// JUNIT TEST CASE : CTRL + SHIFT + T

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
//@ToString
@Data   //Getter, Setter, ToString 3개 한번에 받는 어노테이션
@AllArgsConstructor // 모든 인자 받는 생성자
@NoArgsConstructor //기본 생성자
@Builder
@Component
public class PersonDTO {
    private String name;
    private int age;
    private String addr;

}

