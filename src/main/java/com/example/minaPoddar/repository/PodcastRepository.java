package com.example.minaPoddar.repository;

import com.example.minaPoddar.entity.PodcastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastRepository extends JpaRepository<PodcastEntity, Long> {

}
