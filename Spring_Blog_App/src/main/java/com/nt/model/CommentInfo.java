package com.nt.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COMMENTS_INFO")
@Setter
@Getter
public class CommentInfo {
	
	@Id
	@SequenceGenerator(name = "gen", sequenceName = "COMMENTS_INFO_SEQ",
	 					initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen", strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String email;
	
	@Lob
	private String content;
	
	@CreationTimestamp
	private LocalDate postedOn;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private PostInfo post;

}
