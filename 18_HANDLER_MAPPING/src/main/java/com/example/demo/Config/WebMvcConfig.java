package com.example.demo.Config;


import com.example.demo.Handler.CustomHandler;
import com.example.demo.Handler.RequestCustomHandler;
import com.example.demo.Interceptor.MemoInterceptor;
import com.example.demo.Listener.C01CustomContextRefreshedListener;
import com.example.demo.Listener.C02RequestHandledEventListener;
import com.example.demo.Listener.MemoAddEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

//    //MULTIPATR CONFIG
//    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(DataSize.ofGigabytes(1));        // 파일 1개 최대(1G)
//        factory.setMaxRequestSize(DataSize.ofGigabytes(1));     // 요청 전체 최대(1G)
//        factory.setFileSizeThreshold(DataSize.ofGigabytes(1));  // 메모리 임계치(유사: maxInMemorySize)
//        return factory.createMultipartConfig();
//    }
//
//    //정적자원경로 지정
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resourcese/css/**").addResourceLocations("classpath:/css/");
//        registry.addResourceHandler("/resourcese/js/**").addResourceLocations("classpath:/js/");
//    }




    @Autowired
    MemoInterceptor memoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memoInterceptor)
                .addPathPatterns("/memo/**")
                .excludePathPatterns("/resourcese/css/**","/resourcese/js/**");    //정적경로 path 등록
    }

    //-----------------------------------------------
    // LISTENER
    //-----------------------------------------------
    @Bean
    public C01CustomContextRefreshedListener c01CustomContextRefreshedListener(){
        return new C01CustomContextRefreshedListener();
    }
    @Bean
    public C02RequestHandledEventListener c02RequestHandledEventListener(){
        return new C02RequestHandledEventListener();
    }

    @Bean
    public MemoAddEventListener memoAddEventListener(){
        return new MemoAddEventListener();
    }

    //-----------------------------------------------
    // HANDLER
    //-----------------------------------------------
    //1) BEAN MAPPING(BeanNameUrlHandlerMapping)
    @Bean
    BeanNameUrlHandlerMapping beanNameUrlHandlerMapping(){
        System.out.println("[HANDLER_MAPPER] beanNameUrlHandlerMapping init..");
        return new BeanNameUrlHandlerMapping();
    }
    @Bean(name="/custom_01")
    public CustomHandler customHandler(){
        return new CustomHandler();
    }

    //  요청 URL을 동일한 이름을 가진 Bean 에 매핑
    //2)SimpleUrlHandlerMapping: 정적자원에 대한 매핑정보 설정(기본값)
    //+개발자가 매핑정보를 추가설정가능
    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(){
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();

        Map<String,Object> map = new HashMap();
        map.put("/custom_02",new CustomHandler());

        handlerMapping.setUrlMap(map);
        handlerMapping.setOrder(0);

        return handlerMapping;
    }

    //03 RequestMappingHandlerMapping
    //Controller와 매핑되는 URL을 찾아내고 해당 URL에 대한 요청 처리
    @Bean
    RequestMappingHandlerMapping requestMappingHandlerMapping2() throws NoSuchMethodException {
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        //실행할 함수 선택(Reflectino)
        Method method = RequestCustomHandler.class.getMethod("helloWorld",null);
        //요청 조건 객체 생성
        RequestMappingInfo info = RequestMappingInfo
                .paths("/custom_03")
                .methods(RequestMethod.GET)
                .build();
        //핸들러에 등록
        handlerMapping.registerMapping(info,new RequestCustomHandler(),method);

        return handlerMapping;
    }
    @Bean
    RequestMappingHandlerMapping requestMappingHandlerMapping3() throws NoSuchMethodException {
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        //실행할 함수 선택(Reflectino)
        Method method = RequestCustomHandler.class.getMethod("helloWorld2",null);

        //요청 조건 객체 생성
        RequestMappingInfo info = RequestMappingInfo
                .paths("/custom_04")
                .methods(RequestMethod.GET)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .build();
        //핸들러에 등록
        handlerMapping.registerMapping(info,new RequestCustomHandler(),method);

        return handlerMapping;
    }





}
