package ru.procoders.webinarspamservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WebinarUsersNames {
    @JsonProperty("participations")
    public List<Participation> users;
}
