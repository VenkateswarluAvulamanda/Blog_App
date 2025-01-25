package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.PostInfo;
import com.nt.model.UserInfo;

public interface PostRepository extends JpaRepository<PostInfo, Integer> {
	
	public List<PostInfo> findByUser(UserInfo user);
	
	@Query("FROM PostInfo WHERE title LIKE %?1%")
	public List<PostInfo> searchPost(String query);
} 
