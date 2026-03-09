package com.ramondev.desafio_bossabox.repository;

import com.ramondev.desafio_bossabox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     boolean existsByEmail(String email);



}


