package com.example.demo.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class UserController {
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
