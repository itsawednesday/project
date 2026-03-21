package com.example.project.mapper;

import com.example.project.entity.PodcastEntity;

public class PodcastMapper {
	public static PodcastDto mapPodcastDto(PodcastEntity podcastEntity) {
		return new PodcastDto(
				podcastEntity.getUuid(),
				podcastEntity.getAuthor(),
				podcastEntity.getCategory(),
				podcastEntity.getTitle());
	}
	
	public static PodcastEntity mapPodcastEntity(PodcastDto podcastDto) {
		return new PodcastEntity(
				podcastDto.getUuid(),
				podcastDto.getTitle(),
				podcastDto.getCategory(),
				podcastDto.getAuthor());
	}
}
