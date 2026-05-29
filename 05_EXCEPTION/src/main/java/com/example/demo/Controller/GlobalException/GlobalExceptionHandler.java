package com.example.demo.Controller.GlobalException;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(FileNotFoundException.class)
    public String exceptionHandler_1(Exception e, Model model){
        log.error("[Global] ExceptionTestController ex1 : " + e);
        model.addAttribute("e",e);
        return "global/error1";
    }
    @ExceptionHandler(ArithmeticException.class)
    public String exceptionHandler_2(Exception e, Model model){
        log.error("[Global] ExceptionTestController ex2 : " + e);
        model.addAttribute("e",e);
        return "global/error2";
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler_All(Exception e, Model model){
        log.error("[Global] etc Exceptions : " + e);
        model.addAttribute("e",e);
        return "global/error3";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String exceptionHandler(Exception e, Model model){
        log.error("IllegalStateException ex05 : " + e);
        model.addAttribute("e",e);
        return "global/error";
    }

}
