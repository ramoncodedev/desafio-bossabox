package com.ramondev.desafio_bossabox.repository;

import com.ramondev.desafio_bossabox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     boolean existsByEmail(String email);

     Optional<UserDetails> findUserByEmail(String email);

}


