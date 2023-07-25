package ru.procoders.webinarspamservice.services;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.procoders.webinarspamservice.dto.StartSpamDto;
import ru.procoders.webinarspamservice.models.CreatedBotResponse;

import java.util.UUID;

public class SpamLogic implements Runnable {

    StartSpamDto startSpamDto;
    RestTemplate restTemplate = new RestTemplate();


    public SpamLogic(StartSpamDto startSpamDto){
        this.startSpamDto = startSpamDto;
    }

    @Override
    public void run() {
        String messageForDumb = "text=" + startSpamDto.getMessage();
        System.out.println("Я в новом потоке спама");
        for (int i = 0; i < startSpamDto.getBotsCount(); i++) {
            String botName = UUID.randomUUID().toString();
            String sessionId;
            String webinarUrl = "https://events.webinar.ru/api/eventsessions/"+ startSpamDto.getWebinarId();


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Accept", "application/json, text/javascript, */*; q=0.01");
            headers.set("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"");
            headers.set("sec-ch-ua-mobile", "?0");
            headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
            headers.set("X-Webinar-Referrer", "https://events.webinar.ru/65638895/1128606851/session/1868443042");
            headers.set("Referer", "https://events.webinar.ru/65638895/1128606851/session/1868443042");
            headers.set("atatus-apm-traceparent", "00-103b813339bf4ffce69deed2fa7fb8c8-adc32ed6adb2e3a5-01");
            headers.set("X-Requested-With", "XMLHttpRequest");
            headers.set("sec-ch-ua-platform", "\"macOS\"");
            HttpEntity httpEntity = new HttpEntity("nickname=" + botName + "&name=&secondName=&phone=", headers);
            try {
                ResponseEntity<CreatedBotResponse> response = restTemplate.postForEntity(webinarUrl + "/guestlogin", httpEntity, CreatedBotResponse.class);
                sessionId = response.getBody().getUser().get("sessionId");
                Thread botThread = new Thread(new BotRunnable(startSpamDto.getMessageCount(), sessionId, messageForDumb, restTemplate, webinarUrl));
                botThread.start();
                System.out.println("Новый бот с ником "+ botName +" создан");
            }catch (Exception e){
                System.out.print("Ебучий тайм аут. Ребята, у нас одному боту пиздец");
            }
        }
        Thread.currentThread().interrupt();
    }
}
