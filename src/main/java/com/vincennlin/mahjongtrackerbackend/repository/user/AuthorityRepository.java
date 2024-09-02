package com.vincennlin.mahjongtrackerbackend.repository.user;

import com.vincennlin.mahjongtrackerbackend.entity.user.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
