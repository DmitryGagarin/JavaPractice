package com.newWebPetProject.web.model;

import java.util.List;

public interface UserRegService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
