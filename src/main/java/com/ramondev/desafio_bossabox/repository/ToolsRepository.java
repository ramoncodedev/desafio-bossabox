package com.ramondev.desafio_bossabox.repository;

import com.ramondev.desafio_bossabox.entity.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolsRepository extends JpaRepository<Tools, Long> {
}
