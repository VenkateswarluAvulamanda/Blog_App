package com.nt.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "POSTS_INFO")
@Setter
@Getter
public class PostInfo {

	@Id
	@SequenceGenerator(name = "gen", sequenceName = "POSTS_INFO_SEQ",
	 					initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "gen", strategy = GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	private String description;
	
	@Lob
	private String content;
	
	@CreationTimestamp
	private LocalDate createdOn;
	
	@UpdateTimestamp
	private LocalDate modifiedOn;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInfo user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<CommentInfo> comments;
	
}
