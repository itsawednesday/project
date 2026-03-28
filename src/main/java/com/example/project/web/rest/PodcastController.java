package com.example.project.web.rest;

import com.example.project.entity.PodcastEntity;
import com.example.project.kafka.KafkaProducer;
import com.example.project.service.PodcastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/podcast")
public class PodcastController {

    private final PodcastService podcastService;

    private final KafkaProducer kafkaProducer;

    //Porva att skriva ett meddelande på postman
    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestParam("message") String message) {
        this.kafkaProducer.writeMessage((message));

    }

    public PodcastController(PodcastService podcastService, KafkaProducer kafkaProducer) {
        this.podcastService = podcastService;
        this.kafkaProducer = kafkaProducer;
    }
    @GetMapping("/{uuid}")
    public List<PodcastEntity> getPodcastById(@PathVariable Long uuid) {
        return podcastService.getPodcastByUuid(uuid);
    }
//
//    @GetMapping("/{uuid}")
//    public List<PodcastEntity> getPodcast(@RequestParam(required = false) String uuid,
//                                          @RequestParam(required = false) String title,
//                                          @RequestParam(required = false) String author) {
//
//        if (uuid != null) {
//            return podcastService.getPodcastByUuid(Long.valueOf(uuid));
//        } else if (title != null) {
//            return podcastService.getPodcastByTitle(title);
//        } else if (author != null) {
//            return podcastService.getPodcastByAuthor(author);
//        }
//
//        return podcastService.getPodcasts();
//    }

    @PostMapping
    public ResponseEntity<PodcastEntity> addPodcast(@RequestBody PodcastEntity podcast) {
        PodcastEntity createPodcast = podcastService.addPostcast(podcast);
        return new ResponseEntity<>(createPodcast, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PodcastEntity> update(@RequestBody PodcastEntity podcast) {
        PodcastEntity update = podcastService.updaterPodcast(podcast);

        return new ResponseEntity<>(update, HttpStatus.OK);
	}

    @DeleteMapping("/{podcast}")
    public ResponseEntity <String> deletePodcast(@PathVariable Long uuid) {
        podcastService.deletePodcast(uuid);
        return new ResponseEntity<>("Podcast deleted.", HttpStatus.OK);
    }
}