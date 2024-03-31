package com.newWebPetProject.web.model.repository;

import com.newWebPetProject.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
