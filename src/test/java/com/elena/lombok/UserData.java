package com.elena.lombok;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String updatedAt;
    private String email;
}

