package com.example.demo.Config.auth.provider;

import java.util.Map;

public interface OAuth2UserInfo {
    String getName();       //이름 반환
    String getEamil();      //접속이메일게정 반환
    String getProvider();   //PROVIDER이름 반환
    String getProviderId(); //PROVIDERID 반환
    Map<String,Object> getAttributes(); //!계정 정보 반환
}
