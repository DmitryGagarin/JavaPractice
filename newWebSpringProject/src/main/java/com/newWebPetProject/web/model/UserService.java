package com.newWebPetProject.web.model;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public Users firstUser() {
        return new Users(1L, "Artem", "Morozov", "male", "user", "Moroz");
    }

    public Users secondUser() {
        return new Users(2L, "Kirill", "Sidorov", "male", "admin", "xx_sid_xx");
    }

    public Users thirdUser() {
        return new Users(3L, "Alena", "Victorova", "female", "user", "super_girl_power");
    }
}
