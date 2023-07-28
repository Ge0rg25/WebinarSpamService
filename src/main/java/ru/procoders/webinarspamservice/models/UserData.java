package ru.procoders.webinarspamservice.models;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserData {
    int id;
    String nickname;
    String type;
    String name;
    String secondName;
    String avatarUri;
    String discr;
}
