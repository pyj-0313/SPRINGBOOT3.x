package com.example.demo.외부API연동.C06Portone;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/Portone")
public class PortoneController {

    @GetMapping("/index")
    public void index(){
        log.info("GET /Portone/index...");
    }


}
