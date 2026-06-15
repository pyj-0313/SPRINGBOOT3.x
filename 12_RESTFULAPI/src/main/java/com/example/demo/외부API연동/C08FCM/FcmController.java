package com.example.demo.외부API연동.C08FCM;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 * FCM(Firebase Cloud Messaging) 앱 푸시 발송 실습 - HTTP v1 API
 * - 개인 구글계정으로 Firebase 프로젝트 생성 → 완전 무료 (사업자 불필요)
 *
 * [공식 문서]
 * - 콘솔            : https://console.firebase.google.com/
 * - 메시지 전송 가이드 : https://firebase.google.com/docs/cloud-messaging/send-message
 * - REST v1 스펙    : https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages/send
 * - 액세스 토큰 발급 : https://firebase.google.com/docs/cloud-messaging/auth-server
 *
 * [확인 방법]
 *   PROJECT_ID / ACCESS_TOKEN(서비스계정 OAuth2 토큰, 1시간 유효) 채운 뒤
 *   http://localhost:8099/fcm/send?token=대상기기토큰&title=제목&body=내용
 *   ※ ACCESS_TOKEN 은 서비스계정 키(JSON)로 발급 (위 '액세스 토큰 발급' 문서 참고)
 */
@RestController
@Slf4j
@RequestMapping("/fcm")
public class FcmController {

    private String PROJECT_ID = "";     // Firebase 프로젝트 ID
    private String ACCESS_TOKEN = "";   // 서비스계정 OAuth2 액세스 토큰 (Bearer)

    // 단말기(device token) 1대로 푸시 전송
    // 문서: https://firebase.google.com/docs/cloud-messaging/send-message#rest
    @GetMapping("/send")
    public ResponseEntity<String> send(
            @RequestParam("token") String token,
            @RequestParam("title") String title,
            @RequestParam("body") String body
    ) {
        log.info("GET /fcm/send...token : " + token);

        String url = "https://fcm.googleapis.com/v1/projects/" + PROJECT_ID + "/messages:send";

        RestTemplate restTemplate = new RestTemplate();

        // 요청 헤더 (OAuth2 Bearer 토큰)
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + ACCESS_TOKEN);
        header.add("Content-Type", "application/json");

        // 요청 바디 { "message": { token, notification:{ title, body } } }
        JSONObject notification = new JSONObject();
        notification.put("title", title);
        notification.put("body", body);

        JSONObject message = new JSONObject();
        message.put("token", token);
        message.put("notification", notification);

        JSONObject params = new JSONObject();
        params.put("message", message);

        HttpEntity<String> entity = new HttpEntity<>(params.toJSONString(), header);

        // 요청 후 응답 확인
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());

        return response;
    }
}
