package com.vincennlin.aimahjongbackend.repository.user;

import com.vincennlin.aimahjongbackend.entity.user.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
