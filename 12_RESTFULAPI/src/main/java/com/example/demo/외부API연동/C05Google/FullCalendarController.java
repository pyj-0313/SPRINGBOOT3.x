package com.example.demo.외부API연동.C05Google;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/google/cal")
public class FullCalendarController {
    @Value("${calendar_Apikey}")
    private String Calendar_ApiKey;
    @Value("${calendar_Id}")
    private String Calendar_Id;
    @GetMapping
//    public void get(){
//        log.info("GET /google/cal...");
//
//    }
    public String cal(Model model){
        model.addAttribute("googleCalendarApiKey",Calendar_ApiKey);
        model.addAttribute("googleCalendarId",Calendar_Id);
        log.info("GET /google/cal...");
        return "/google/cal";

    }
}
