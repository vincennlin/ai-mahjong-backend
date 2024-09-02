package com.vincennlin.mahjongtrackerbackend.repository;

import com.vincennlin.mahjongtrackerbackend.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
