package com.nt.dto;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class Comment {
	
	private String name;
	
	private String email;
	
	@Lob
	private String content;
	
}
