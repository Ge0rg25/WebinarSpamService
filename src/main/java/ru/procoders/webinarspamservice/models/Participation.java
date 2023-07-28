package ru.procoders.webinarspamservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Participation{
    int id;
    boolean isAccepted;
    String role;
    boolean isOnline;
    String name;
    String secondName;
    String agreementStatus;
    String registerStatus;
    String paymentStatus;
    String lastSessionConnectionStatus;
    boolean isTranslator;
    boolean isChatMuted;

    @JsonProperty("user")
    UserData userData;
}
