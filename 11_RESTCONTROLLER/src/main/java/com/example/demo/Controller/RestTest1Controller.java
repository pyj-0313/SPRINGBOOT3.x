package com.example.demo.Controller;


import com.example.demo.Domain.Common.Dtos.MemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/rest")
public class RestTest1Controller {
    @GetMapping(value = "/getText",produces = MediaType.TEXT_PLAIN_VALUE)
    public String t1(){
        log.info("GET /rest/getText");
        return "HELLOWORLD";
    }
    @GetMapping(value = "/getJson",produces = MediaType.APPLICATION_JSON_VALUE)
    public MemoDTO t2(){
        log.info("GET /rest/getJson");
        return new MemoDTO(1L,"title1","text1","a@acom", LocalDateTime.now());
    }
    @GetMapping(value = "/getXml",produces = MediaType.APPLICATION_XML_VALUE)
    public MemoDTO t3(){
        log.info("GET /rest/getXml");
        return new MemoDTO(1L,"title1","text1","a@acom", LocalDateTime.now());
    }
    @GetMapping(value = "/getList",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MemoDTO> t4() {
        log.info("GET /rest/getList");
        List<MemoDTO> list = new ArrayList<>();
        for (long i = 0; i < 10; i++){
            list.add(new MemoDTO(i,"t" + i, "t" + i, "w" + i, LocalDateTime.now()));
        }
        return list;
    }
}
