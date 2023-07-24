package ru.procoders.webinarspamservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.procoders.webinarspamservice.dto.StartSpamDto;
import ru.procoders.webinarspamservice.services.SpamService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SpamController {
    SpamService spamService;

    @PostMapping("/start")
    public ResponseEntity<?> startSpam(@RequestBody StartSpamDto startSpamDto){
        System.out.println(startSpamDto.toString());
        return spamService.startSpam(startSpamDto);
    }

}
