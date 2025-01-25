package com.nt.dto;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class Post {
	
	private String title;
	
	private String description;
	
	@Lob
	private String content;

}
