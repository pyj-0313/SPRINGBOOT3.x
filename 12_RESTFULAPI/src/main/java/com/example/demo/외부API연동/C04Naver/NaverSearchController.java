package com.example.demo.외부API연동.C04Naver;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/Naver/search")
public class NaverSearchController {
    @GetMapping("/book/{keyword}")
    public void search(@PathVariable String keyword)
    {
        log.info("GET /Naver.search...{}",keyword);
    }
}
