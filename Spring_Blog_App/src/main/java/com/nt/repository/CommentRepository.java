package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.CommentInfo;
import com.nt.model.PostInfo;

public interface CommentRepository extends JpaRepository<CommentInfo, Integer> {
	public List<CommentInfo> findByPost(PostInfo post);
	public List<CommentInfo> findAllByPostIn(List<PostInfo> listOfPosts);
}
