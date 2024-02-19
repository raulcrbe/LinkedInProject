package com.linkedin.sports.user.crud.infrastructure.persistence.jpa.repositories;

import com.linkedin.sports.user.crud.infrastructure.persistence.jpa.entities.UserJpa;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserJpa, UUID> {}
