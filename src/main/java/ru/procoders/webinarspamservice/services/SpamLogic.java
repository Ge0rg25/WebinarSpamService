package ru.procoders.webinarspamservice.services;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.procoders.webinarspamservice.dto.StartSpamDto;
import ru.procoders.webinarspamservice.models.CreatedBotResponse;
import ru.procoders.webinarspamservice.models.Participation;
import ru.procoders.webinarspamservice.utils.WebinarSpamUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Slf4j
public class SpamLogic implements Runnable {

    StartSpamDto startSpamDto;

    RestTemplate restTemplate;
    Faker faker;
    WebinarSpamUtils webinarSpamUtils;

    Random random = new Random();

    public SpamLogic(StartSpamDto startSpamDto, RestTemplate restTemplate, Faker faker, WebinarSpamUtils webinarSpamUtils) {
        this.startSpamDto = startSpamDto;
        this.restTemplate = restTemplate;
        this.faker = faker;
        this.webinarSpamUtils = webinarSpamUtils;
    }

    @Override
    public void run() {
        String messageForDumb = "text=" + startSpamDto.getMessage();
        log.info("Я в новом потоке спама");
        String webinarUrl = "https://events.webinar.ru/api/eventsessions/" + startSpamDto.getWebinarId();
        List<Participation> users = webinarSpamUtils.getWebinarUsers(restTemplate, webinarUrl);
        List<String> nicknames = new ArrayList<>();

        if (startSpamDto.getUseUsersNickNames()) {
            for (Participation participation : users) {
                if (!participation.getRole().equals("ADMIN")) {
                    nicknames.add(participation.getUserData().getNickname());
                }
            }
        }


        if (startSpamDto.getUseAdminNickNames()) {
            List<Participation> admins = new ArrayList<>();
            for (Participation participation : users) {
                if (participation.getRole().equals("ADMIN")) {
                    nicknames.add(participation.getUserData().getNickname());
                }
            }
        }

        for (int i = 0; i < startSpamDto.getBotsCount(); i++) {
            String botName = faker.name().fullName();
            if(!nicknames.isEmpty()){
                botName = nicknames.get(random.nextInt(nicknames.size()));
            }
            String sessionId;





            HttpHeaders headers = WebinarSpamUtils.getRegisterHttpHeaders();
            HttpEntity<?> httpEntity = new HttpEntity<>("nickname=" + botName + "&name=&secondName=&phone=", headers);
            try {
                ResponseEntity<CreatedBotResponse> response = restTemplate.postForEntity(webinarUrl + "/guestlogin", httpEntity, CreatedBotResponse.class);
                sessionId = response.getBody().getUser().get("sessionId");
                BotRunnable botRunnable = new BotRunnable(startSpamDto.getMessageCount(), sessionId, messageForDumb, restTemplate, webinarUrl, startSpamDto.getSpamTarget(), botName);
                Thread botThread = new Thread(botRunnable);
                botThread.start();
                log.info("Новый бот с ником " + botName + " создан");
            } catch (Exception e) {
                log.warn("Ебучий тайм аут. Бота не будет");
            }
        }
        Thread.currentThread().interrupt();
    }


}
