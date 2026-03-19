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
@Table(name = "podcast")
public class PodcastEntity {

    @Id
    @Column(name = "uuid", unique = true)
    private Long uuid;

    @Column(name = "title")
    private String title;
    
    @Column(name = "category")
    private String category;

    @Column(name = "author")
    private String author;


    public PodcastEntity (Long uuid) {
        this.uuid = uuid;
    }
    public PodcastEntity() {}

    public Long getUuid() {
        return uuid;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle() {
        return title;
    }
    public String getCategory() {
        return category;
    }
    public void setUuid(Long uuid){
        this.uuid = uuid;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author= author;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
