package ru.procoders.webinarspamservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StartSpamDto {

    @JsonProperty("webinar_id")
    String webinarId;

    String message;

    @JsonProperty("message_count")
    Integer messageCount;

    @JsonProperty("bots_count")
    Integer botsCount;

}
