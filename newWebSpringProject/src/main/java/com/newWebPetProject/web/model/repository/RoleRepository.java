package com.newWebPetProject.web.model.repository;

import com.newWebPetProject.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
