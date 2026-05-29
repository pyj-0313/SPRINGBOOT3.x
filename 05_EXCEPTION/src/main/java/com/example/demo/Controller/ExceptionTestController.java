package com.example.demo.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@Controller
@Slf4j
@RequestMapping("/except")
public class ExceptionTestController {
//    http://localhost:8090/except/ex1

    @ExceptionHandler(FileNotFoundException.class)
    public String exceptionHandler_1(Exception e){
        log.error("ex1 : " + e);
        return "except/error1";
    }

    @GetMapping("/ex1")
    public void ex1() throws FileNotFoundException {
        log.info("GET /except/ex1...");
        throw new FileNotFoundException("파일을 찾을 수 없습니다..");
    }
}
