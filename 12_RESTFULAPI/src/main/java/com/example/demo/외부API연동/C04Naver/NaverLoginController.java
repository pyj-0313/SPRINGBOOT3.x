package com.example.demo.외부API연동.C04Naver;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/Naver")
public class NaverLoginController {

    private String CLIENT_ID = "";
    private String CLIENT_SECRET = "";
    private String CALLBACK_URL ="http://127.0.0.1:8080/Naver/callback";

    @GetMapping("/login")
    public String login(){
        log.info("GET /login....");
        return "redirect:https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+CLIENT_ID+"&state=STATE_STRING&redirect_uri="+CALLBACK_URL;
    }

    @GetMapping("/callback")
    @ResponseBody
    public String callback(String code, String state, String error, String error_description){
        log.info("GET /Naver/callback...{},{},{},{}" ,code,state,error,error_description);


        //요청파라미터 정리
        String url = "https://nid.naver.com/oauth2.0/token";

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",CLIENT_ID);
        params.add("client_secret",CLIENT_SECRET);
        params.add("code",code);
        params.add("state",state);

        //요청 헤더
        HttpHeaders header = new HttpHeaders();

        //요청 엔터티(헤더 + 바디(params))
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,header);

        //응답 = 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,entity,String.class);

        System.out.println(response.getBody());

        return response.getBody();
//        return "redirect:/Naver";

    }

    //Endpoint : /Naver
    @GetMapping
    public String main(Model model){
        log.info("GET /Naver...");

        //요청파라미터 정리
        String url = "";

//        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
//        params.add("","");
//        params.add("","");
//        params.add("","");
//        params.add("","");

        //요청 헤더
        HttpHeaders header = new HttpHeaders();
        header.add("-","");

        //요청 엔터티(헤더 + 바디(params))
        HttpEntity entity = new HttpEntity(header);

        //응답 = 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println(response.getBody());


        return "Naver/main";
    }
}
