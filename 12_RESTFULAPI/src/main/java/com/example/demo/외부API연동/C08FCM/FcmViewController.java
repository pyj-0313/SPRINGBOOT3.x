package com.example.demo.외부API연동.C08FCM;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * FCM 발송/수신 데모 화면 라우팅
 *  send.html -> /api/fcm/send -> FcmService(Admin SDK) -> Firebase -> receive.html
 */
@Controller
@Slf4j
public class FcmViewController {

    @Value("${fire_apikey}")
    private String apiKey;
    @Value("${fire_domain}")
    private String authDomain;
    @Value("${fire_projectid}")
    private String projectId;
    @Value("${fire_bucket}")
    private String storageBucket;
    @Value("${fire_mesid}")
    private String messagingSenderId;
    @Value("${fire_appid}")
    private String appId;
    @Value("${fire_vapidkey}")
    private String VAPID_KEY;




    // =========================================================================
    // [화면1] 발송 페이지 (제목/내용 입력 → /api/fcm/send 호출)
    //  - 접속: http://localhost:8080/fcm/send
    // =========================================================================
    @GetMapping("/fcm/send")
    public String send() {
        log.info("GET /fcm/send....");
        return "fcm/send";    // templates/fcm/send.html
    }

    // =========================================================================
    // [화면2] 수신 페이지 (기기 토큰 발급/등록 + 즉시 수신 + 종 배지 카운팅)
    //  - 접속: http://localhost:8080/fcm/receive
    // =========================================================================
    @GetMapping("/fcm/receive")
    public String receive(Model model) {
        model.addAttribute("apiKey",apiKey);
        model.addAttribute("authDomain",authDomain);
        model.addAttribute("projectId",projectId);
        model.addAttribute("storageBucket",storageBucket);
        model.addAttribute("messagingSenderId",messagingSenderId);
        model.addAttribute("appId",appId);
        model.addAttribute("VAPID_KEY",VAPID_KEY);

        log.info("GET /fcm/receive....");
        return "fcm/receive"; // templates/fcm/receive.html
    }
}
