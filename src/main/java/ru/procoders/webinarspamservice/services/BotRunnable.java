package ru.procoders.webinarspamservice.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class BotRunnable implements Runnable {

    RestTemplate restTemplate;
    Integer messageCount;
    String sessionId;
    String messageForDumb;

    String webinarUrl;


    public BotRunnable(Integer messageCount, String sessionId, String messageForDumb, RestTemplate restTemplate, String webinarUrl) {
        this.messageCount = messageCount;
        this.sessionId = sessionId;
        this.messageForDumb = messageForDumb;
        this.restTemplate = restTemplate;
        this.webinarUrl = webinarUrl;
    }

    @Override
    public void run() {
        for (int j = 0; j < messageCount; j++) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Accept", "*/*");
            headers.setConnection("keep-alive");
            headers.set("Cookie", "atatus-aid=id|3b22e9e10c3544b0a0f0e7276b306d3a&timestamp|2023-07-24T15:42:57.276Z; tmr_lvid=8ba7d71b4950c873db0981603842533a; tmr_lvidTS=1690213377581; _ga=GA1.2.1782998344.1690213378; _gid=GA1.2.1585371709.1690213378; _ym_uid=1690213378273047836; _ym_d=1690213378; _ym_isad=2; sessionId=" + sessionId + "; RUM_EPISODES=s=1690213508015&r=https%3A//events.webinar.ru/65638895/1128606851/session/1868443042; atatus-sid=id|62e31929b87843cc99871fdc42652aee&timestamp|2023-07-24T15:45:08.719Z; mp_0ff52acccc4445cd1afa75416a39d7de_mixpanel=%7B%22distinct_id%22%3A%20%22%24device%3A189889368794c9-000a4f8048da99-1b525634-1d73c0-189889368794c9%22%2C%22%24device_id%22%3A%20%22189889368794c9-000a4f8048da99-1b525634-1d73c0-189889368794c9%22%7D; webrtcTestResult_v2=ok; tmr_detect=0%7C1690213510987; eventreferer=%7B%22event_referer_creator_id%22%3A65638895%2C%22event_referer_visited_at%22%3A%222023-07-24T18%3A45%2B0300%22%7D; jwt-token=eyJhbGciOiJSUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjc5OTExMjYzLCJpYXQiOjE2OTAyMTQxNjksImV4cCI6MTY5MDIxNDc2OX0.wb2f7OqZpMPoSI0ZYpG6T0Es7blOLxaj9BHixQJDKyDyYCg_bn1IWv5OuSkGRMdEObXWIvoMSogDp7EnNrIdIvmqYyr7smdEqjl_xprTzPRZ7op0dhzURABxxl5_zyLFV7zABRebyST4Xq7HYzrA288kohPFMYznMwvZS3-2KCgnV7urFXNmfUOtC5G7EedPyxm6KbdX83xkgh-dsi_SrV4zhgvbFOSaoLAI0kW1I4gDfks4FsoDloBrR8LEdleTVegLHJ8uD-Mw0Lje4-OXt8JJR-d2lU1jcHjG7kao0ZGUmSRB5BZY5_dhFh7Ob6yrzZp8b4Nox5YbZvgpppoCiJFfnXNOKbn0sJmNRualw6prwmXjiGsmHHeJKPk01q2tbvzj45LZlzFs81hYBuO3hWR-75BShbA-uJMEePIrO6DPHUaJN_VyE0ai5OBMrj-1uLv4TjD_l4T8CQ4OdvRmMSL1UODsiWJ0rd66XaneCcFvjE70OlxlcuQDmtIGvKetiyCWFrEsGlkvKa3Y7oVr5TtsvoEBdl8w3e-egkizSzn1ch6XBpVmwPLHeGzagZkjpNzxnkjDurpwxhRsrYsl382aQhF5f4b0bctAZ5x_Bv_HU6RRyFb79FYUr4uNpRsiPbFUF7vjPMTjORfEi_pDbj8R5fw-DwdLf2z5WlgLgpE; jwt-refresh-token=4a88758167e6e2cfeb663f556e6eb073b17c003fdb3d2e2e64ec7091e61bb2b7c3ae84cde01e32fc8dcb83fb9aa0af5099912eee56efdb259bd9ded2f87a033c; sessionId=" + sessionId);
            headers.set("Origin", "https://events.webinar.ru");
            headers.set("Referer", "https://events.webinar.ru/65638895/1128606851/stream-new/1868443042");
            headers.set("Sec-Fetch-Dest", "empty");
            headers.set("Sec-Fetch-Mode", "cors");
            headers.set("Sec-Fetch-Site", "same-origin");
            headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
            headers.set("atatus-apm-traceparent", "00-70a38a5f23d09ddbbb9d78458d47455f-09686bad7aee2a64-01");
            headers.set("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"");
            headers.set("sec-ch-ua-mobile", "?0");
            headers.set("sec-ch-ua-platform", "\"macOS\"");
            HttpEntity httpEntity = new HttpEntity(messageForDumb, headers);
            try {
                restTemplate.postForObject(webinarUrl + "/chat", httpEntity, String.class);
            } catch (Exception e){
                System.out.printf("Ебучий смс не прошел из за ебучей блокировки");
            }
        }
    }
}
