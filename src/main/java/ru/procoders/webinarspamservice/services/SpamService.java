package ru.procoders.webinarspamservice.services;


import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.procoders.webinarspamservice.dto.StartSpamDto;
import ru.procoders.webinarspamservice.utils.WebinarSpamUtils;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SpamService {


    RestTemplate restTemplate;
    Faker faker;
    WebinarSpamUtils webinarSpamUtils;

    public ResponseEntity<?> startMessageSpam(StartSpamDto startSpamDto) {
        SpamLogic spamLogic = new SpamLogic(startSpamDto, restTemplate, faker, webinarSpamUtils);
        Thread thread = new Thread(spamLogic);
        thread.start();
        log.info("Новый сеанс спама запущен");
        return new ResponseEntity<>(Map.of("status", "success"), HttpStatus.OK);
    }
}


