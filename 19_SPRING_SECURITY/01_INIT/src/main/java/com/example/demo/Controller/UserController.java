package com.example.demo.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/login")
    public void login(){
        log.info("GET /login...");
    }
    @GetMapping("/join")
    public void join_get(){
        log.info("GET /join");
    }
    @PostMapping("/join")
    public void join_post(){
        log.info("POST /join");
    }

    @GetMapping("/user")
    public void user(){
        log.info("GET /user");
    }
    @GetMapping("/manager")
    public void manager(){
        log.info("GET /manager");
    }
    @GetMapping("/admin")
    public void admin(){
        log.info("GET /admin");
    }

}
