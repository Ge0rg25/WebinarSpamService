package ru.procoders.webinarspamservice.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatedBotResponse {

    Boolean isAccepted;
    Long participationId;
    Map<String, String> user;
}
