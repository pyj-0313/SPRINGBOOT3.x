package com.example.demo.Controller;

import com.example.demo.Dtos.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@Slf4j
@RequestMapping(value = "/param")
public class ParamController {

    //-----------------------------------
    // 사용자 - 파라미터 -> 서버 (잘받는지 확인)
    //-----------------------------------
    @RequestMapping(value="/p01",method = RequestMethod.GET)
    public String paramHandler_1(@RequestParam(required = false) String name){
        log.info("GET /param/p01.."+name);
        return "param/page01";
        //WEB-INF/views/param/page01.jsp
    }

    @RequestMapping(value="/p02",method = RequestMethod.POST)
    public String paramHandler_2(@RequestParam(name="username") String name){
        log.info("GET /param/p02.."+name);
        return "param/page02";
        //WEB-INF/views/param/page02.jsp
    }

    @RequestMapping(value="/p03",method = RequestMethod.GET)
    public String paramHandler_3(String name){
        log.info("GET /param/p03.."+name);
        return "param/page03";
        //WEB-INF/views/param/page03.jsp
    }

    @RequestMapping(value="/p04",method = RequestMethod.GET)
    public String paramHandler_4(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String addr
    ){
        log.info("GET /param/p04.."+name+" "+age + " " + addr);
        return "param/page04";
        //WEB-INF/views/param/page04.jsp
    }

    @RequestMapping(value="/p05",method = RequestMethod.GET)
    public String paramHandler_5(@ModelAttribute PersonDTO dto){
        log.info("GET /param/p05.."+dto);
        return "param/page05";
        //WEB-INF/views/param/page05.jsp
    }

    @RequestMapping(value="/p06",method = RequestMethod.GET)
    public String paramHandler_6(PersonDTO dto){
        log.info("GET /param/p06.."+dto);
        return "param/page06";
        //WEB-INF/views/param/page06.jsp
    }

    @RequestMapping(value="/p07/{name}/{age}/{addr}",method = RequestMethod.GET)
    public String paramHandler_7(
            @PathVariable String name,
            @PathVariable int age,
            @PathVariable String addr
    ){
        log.info("GET /param/p07.."+name+" "+age + " " + addr);
        return "param/page07";
        //WEB-INF/views/param/page07.jsp
    }

    @RequestMapping(value="/p08/{name}/{age}/{addr}",method = RequestMethod.GET)
    public String paramHandler_8(PersonDTO dto){
        log.info("GET /param/p08.."+dto);
        return "param/page08";
        //WEB-INF/views/param/page08.jsp
    }
    //@RequestParam(Default) : 동기요청 파라미터 처리 / html form 기반 전달되는 파라미터 처리(JS의 form-data도 받기가능, JSON Type 받기불가)
    //@RequestBody : 비동기요청 파라미터 처리 / json,filedata등 전달되는 파라미터 처리(html form 처리가능)
    @RequestMapping(value="/p09",method = RequestMethod.POST)
    public String paramHandler_rp(@ModelAttribute PersonDTO dto){
        log.info("GET /param/p09.."+dto);
        return "param/page09";
        //WEB-INF/views/param/page09.jsp
    }
    //POSTMAN 설정
    //Content-Type : application/json
    //Body : row 선택 -> JSON TYPE FORMATTING
    @RequestMapping(value="/p10",method = RequestMethod.POST)
    public String paramHandler_rb(@RequestBody PersonDTO dto){
        log.info("GET /param/p10.."+dto);
        return "param/page10";
        //WEB-INF/views/param/page10.jsp
    }

    //-----------------------------------
    //  사용자(잘받는지 확인) <- 전달하는 값 - 서버
    //-----------------------------------
    @RequestMapping(value="/p11",method = RequestMethod.GET)
    public String paramHandler_11(PersonDTO dto, Model model){
        log.info("GET /param/p11.."+dto);
        // 1 파라미터 받기(o)

        // 2 유효성 검증(x)

        // 3 서비스(x)

        // 4 뷰로이동(+값 전달)
        model.addAttribute("name",dto.getName());
        model.addAttribute("age",dto.getAge());
        model.addAttribute("addr",dto.getAddr());
        model.addAttribute("now", LocalDateTime.now());
        return "param/page11";
    }

    @RequestMapping(value="/p12",method = RequestMethod.GET)
    public String paramHandler_12(PersonDTO dto, Model model){
        log.info("GET /param/p12.."+dto);
        // 1 파라미터 받기(o)

        // 2 유효성 검증(x)

        // 3 서비스(x)

        // 4 뷰로이동(+값 전달)
        model.addAttribute("dto",dto);
        model.addAttribute("now", LocalDateTime.now());
        return "param/page12";
    }

    @RequestMapping(value="/p13",method = RequestMethod.GET)
    public String paramHandler_13(PersonDTO dto, Model model){
        log.info("GET /param/p13.."+dto);
        // 1 파라미터 받기(o)

        // 2 유효성 검증(x)

        // 3 서비스(x)

        // 4 뷰로이동(+값 전달)
        model.addAttribute("dto",dto);
        model.addAttribute("now", LocalDateTime.now());
        return "param/page13";
    }
}
