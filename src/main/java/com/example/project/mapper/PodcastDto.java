package com.example.project.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PodcastDto {
	private Long uuid;
	private String title;
	private String category;
	private String author;
	
	
	
}
