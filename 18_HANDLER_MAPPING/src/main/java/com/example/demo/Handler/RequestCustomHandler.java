package com.example.demo.Handler;


import org.springframework.web.bind.annotation.ResponseBody;

public class RequestCustomHandler {

    public String helloWorld(){
        System.out.println("[HANDLER] RequestCustomHandler's helloWorld invoke..");
        return "memo/add";
    }

    @ResponseBody
    public String helloWorld2(){
        System.out.println("[HANDLER] RequestCustomHandler's helloWorld2 invoke..");
        return "HELLOWORLD!!!!!";
    }
}
