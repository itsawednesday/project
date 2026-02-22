package com.example.project.service;

import com.example.project.db.PodcastRepository;
import com.example.project.entity.PodcastEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PodcastService {
    private final PodcastRepository podcastRepository;

    @Autowired
    public PodcastService(PodcastRepository podcastRepository) {

        this.podcastRepository = podcastRepository;
    }

    public List<PodcastEntity> getPodcasts() {
        return podcastRepository.findAll();
    }


    //få ut podcasts från en specifik uuid
    public List<PodcastEntity> getPodcastByUuid(String podcastUuid) {
        return podcastRepository.findAll()
                .stream()
                .filter(podcast -> podcastUuid.equals(podcast.getUuid()))
                .collect(Collectors.toList());
    }
    //få ut podcasts från en author
    public List<PodcastEntity> getPodcastByAuthor(String author) {
        return podcastRepository.findAll()
                .stream()
                .filter(podcast -> podcast.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }
    //få ut podcasts från en title
    public List<PodcastEntity> getPodcastByTitle(String title) {
        return podcastRepository.findAll()
                .stream()
                .filter(podcast -> podcast.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
    public PodcastEntity addPostcast(PodcastEntity entity) {
        podcastRepository.save(entity);
        return entity;
    }

    public PodcastEntity updaterPodcast(PodcastEntity entity) {
        Optional<PodcastEntity> currentPodcast = podcastRepository.findByUuid(entity.getUuid());
        //om podcast finns i databasen
        if (currentPodcast.isPresent()) {
            PodcastEntity update = currentPodcast.get();
            //updatera gamla podcasten med nya uuid
            update.setUuid(update.getUuid());
            update.setTitle(update.getTitle());
            update.setAuthor(update.getAuthor());

            podcastRepository.save(update);
            return update;
        }
        return null;
    }

    @Transactional
    public void deletePodcast (String podcast) {
        podcastRepository.deleteByUuid(podcast);
    }

}
