package com.newWebPetProject.web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Setter
@Getter
@ToString
public class Users {
    private Long id;
    private String firstName;
    private String secondName;
    private String gender;
    private String status;
    private String username;

    public Users(Long id,
                 String firstName,
                 String secondName,
                 String gender,
                 String status,
                 String username) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.status = status;
        this.username = username;
    }
}

