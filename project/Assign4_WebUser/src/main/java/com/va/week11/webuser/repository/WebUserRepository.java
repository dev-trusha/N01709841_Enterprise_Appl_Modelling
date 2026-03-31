package com.va.week11.webuser.repository;

import com.va.week11.webuser.entity.WebUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebUserRepository extends JpaRepository<WebUserEntity, Long> {
    Optional<WebUserEntity> findByUsername(String username);
}
