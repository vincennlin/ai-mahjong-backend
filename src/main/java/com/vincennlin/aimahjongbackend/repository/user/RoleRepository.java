package com.vincennlin.aimahjongbackend.repository.user;

import com.vincennlin.aimahjongbackend.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
