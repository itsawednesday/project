package com.example.project;

import com.example.project.entity.PodcastEntity;
import com.example.project.kafka.KafkaProducer;
import com.example.project.repository.PodcastRepository;
import com.example.project.service.PodcastService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PodcastServiceTest {

    @Test
    void testaGetPodcasts() {
        var repository = mock(PodcastRepository.class);
        var producer = mock(KafkaProducer.class);
        var podcastService = new PodcastService(repository, producer);

        PodcastEntity podcast = new PodcastEntity();
        podcast.setTitle("Title");
        when(repository.save(ArgumentMatchers.any(PodcastEntity.class))).thenReturn(podcast);

        var service = podcastService.addPostcast(podcast);
    //    assertThat(service.getTitle()).isSameAs(podcast.getTitle());
        verify(repository.save(podcast));
    }

}
