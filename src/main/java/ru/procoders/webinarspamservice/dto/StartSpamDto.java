package ru.procoders.webinarspamservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StartSpamDto {

    @JsonProperty("webinar_id")
    String webinarId;

    String message = "hello from Хуйло and Бобер";

    @JsonProperty("message_count")
    Integer messageCount = 10;

    @JsonProperty("bots_count")
    Integer botsCount = 5;

    @JsonProperty("spam_target")
    String spamTarget = "chat";

    @JsonProperty("use_users_nick_names")
    Boolean useUsersNickNames = false;

    @JsonProperty("use_admin_nick_names")
    Boolean useAdminNickNames = false;

}
