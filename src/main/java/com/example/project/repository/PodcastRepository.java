package com.example.project.repository;

import com.example.project.entity.PodcastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PodcastRepository extends JpaRepository<PodcastEntity, Long> {

//    void deleteByUuid(String uuid);
//     Optional<PodcastEntity> findByUuid(String uuid);
}
