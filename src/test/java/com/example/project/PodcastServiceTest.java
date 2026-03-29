package com.example.project;

import com.example.project.entity.PodcastEntity;
import com.example.project.kafka.KafkaProducer;
import com.example.project.repository.PodcastRepository;
import com.example.project.service.PodcastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PodcastServiceTest {

    @Mock
    private PodcastRepository repository;
    @InjectMocks
    private PodcastService service;
    private PodcastEntity podcast;

    @BeforeEach
    void setUp() {
        var producer = mock(KafkaProducer.class);
        service = new PodcastService(repository, producer);
        podcast = new PodcastEntity();
        podcast.setTitle("Test");
        podcast.setUuid(4L);

    }

    @Test
    void testaGetPodcasts() {
        when(repository.findAll()).thenReturn(List.of(new PodcastEntity()));

        List<PodcastEntity> entityList = service.getPodcasts();

        assertNotNull(entityList);
        
        verify(repository, times(1)).findAll();
    }

    @Test
    void testAddPostcast() {
        when(repository.save(any())).thenReturn(podcast);
        
        var skapaPod = service.addPostcast(podcast);
        
        assertEquals("Test", skapaPod.getTitle());
    }
    
    @Test
    void testaUpdateraPodcast() {
      
        when(repository.findById(4L)).thenReturn(Optional.of(podcast));
        when(repository.save(any())).thenReturn(podcast);
        
        var uppdatera = service.updaterPodcast(podcast);
        
        assertEquals(4L, uppdatera.getUuid());
    }
    
    @Test
    void testaDeletePodcast() {
        Long uuid = 4L;
        
        service.deletePodcast(uuid);
        
        verify(repository).deleteById(uuid);
    
    }
}
