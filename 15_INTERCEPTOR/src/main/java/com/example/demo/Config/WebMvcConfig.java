package com.example.demo.Config;


import com.example.demo.Interceptor.MemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

}
