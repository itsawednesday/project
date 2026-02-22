package com.example.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "podcast_media")
public class PodcastEntity {

    @Id
    @Column(name = "uuid", unique = true)
    private String uuid;

    private String title;

    private String image;

    private String description;

    private String languages;

    private String categories;

    private String website;

    private String author;

    private String itunes_id;

    public PodcastEntity (String uuid) {
        this.uuid = uuid;
    }
    public PodcastEntity() {}

    public String getUuid() {
        return uuid;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle() {
        return title;
    }
    public void setUuid(String uuid){
        this.uuid = uuid;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author= author;
    }

}
