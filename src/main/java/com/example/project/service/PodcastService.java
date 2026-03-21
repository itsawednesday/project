package com.example.project.service;

import com.example.project.kafka.KafkaProducer;
import com.example.project.repository.PodcastRepository;
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
    private final KafkaProducer kafkaProducer;

    public PodcastService(PodcastRepository podcastRepository, KafkaProducer kafkaProducer) {
        this.podcastRepository = podcastRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PodcastEntity> getPodcasts() {
        return podcastRepository.findAll();
    }


    //få ut podcasts från en specifik uuid
    public List<PodcastEntity> getPodcastByUuid(Long podcastUuid) {
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
        var saved = podcastRepository.save(entity);
        kafkaProducer.writeMessage("CREATED PODCAST: " + saved.getUuid());
        return entity;
    }

    public PodcastEntity updaterPodcast(PodcastEntity entity) {
        Optional<PodcastEntity> currentPodcast = podcastRepository.findById(entity.getUuid());
        //om podcast finns i databasen
        if (currentPodcast.isPresent()) {
            PodcastEntity update = currentPodcast.get();
            //updatera gamla podcasten med nya uuid
            update.setUuid(update.getUuid());
            update.setTitle(update.getTitle());
            update.setAuthor(update.getAuthor());

           var updated = podcastRepository.save(update);
           kafkaProducer.writeMessage("UPDATED PODCAST: " + updated.getUuid());
            return update;
        }
        return null;
    }

    @Transactional
    public void deletePodcast (Long uuid) {
        podcastRepository.deleteById(uuid);
        kafkaProducer.writeMessage("DELETED PODCAST: " + uuid);
    }

}
