package com.example.project.db;

import com.example.project.entity.PodcastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PodcastRepository extends JpaRepository<PodcastEntity, String> {

    void deleteByUuid(String uuid);
     Optional<PodcastEntity> findByUuid(String uuid);
}
