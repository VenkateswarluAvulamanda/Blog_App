package com.nt.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS_INFO")
@Setter
@Getter
public class UserInfo {

	@Id
	@SequenceGenerator(name = "gen", sequenceName = "USERS_INFO_SEQ",
	 					initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen", strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String pazzword;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<PostInfo> posts;
}
