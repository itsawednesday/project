package com.example.project.web.rest;

import com.example.project.entity.PodcastEntity;
import com.example.project.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/podcast")
public class PodcastController {

    private final PodcastService podcastService;

    @Autowired
    public PodcastController(PodcastService podcastService) {
        this.podcastService = podcastService;
    }

    @GetMapping
    public List<PodcastEntity> getPodcast(@RequestParam(required = false) String uuid,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) String author) {

        if (uuid != null) {
            return podcastService.getPodcastByUuid(uuid);
        } else if (title != null) {
            return podcastService.getPodcastByTitle(title);
        } else if (author != null) {
            return podcastService.getPodcastByAuthor(author);
        }

        return podcastService.getPodcasts();
    }

    @PostMapping
    public ResponseEntity<PodcastEntity> addPodcast(@RequestBody PodcastEntity podcast) {
        PodcastEntity createPodcast = podcastService.addPostcast(podcast);
        return new ResponseEntity<>(createPodcast, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PodcastEntity> update(@RequestBody PodcastEntity podcast) {
        PodcastEntity update = podcastService.updaterPodcast(podcast);
        if (podcast != null) {

            return new ResponseEntity<>(update, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{podcast}")
    public ResponseEntity <String> deletePodcast(@PathVariable String podcast) {
        podcastService.deletePodcast(podcast);
        return new ResponseEntity<>("Podcast deleted.", HttpStatus.OK);
    }
}