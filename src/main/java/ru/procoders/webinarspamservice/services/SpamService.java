package ru.procoders.webinarspamservice.services;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.procoders.webinarspamservice.dto.StartSpamDto;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpamService {

    public ResponseEntity<?> startSpam(StartSpamDto startSpamDto){
        Thread thread = new Thread(new SpamLogic(startSpamDto));
        thread.start();
        return new ResponseEntity<>(Map.of("status", "success"), HttpStatus.OK);
    }

}


