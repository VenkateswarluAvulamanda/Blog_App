package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.dto.Comment;
import com.nt.dto.Post;
import com.nt.model.CommentInfo;
import com.nt.model.PostInfo;

public interface PostService {
	
	public String savePost(Post post);
	
	public List<PostInfo> findAllPostsByUser();
	
	public List<PostInfo> findAll();
	
	public Optional<PostInfo> findPostById(Integer id);
	
	public List<PostInfo> searchPost(String query);
	
	public String saveComment(Comment comment);
	
	public List<CommentInfo> findAllCommentsByPost(PostInfo post);
	
	public List<CommentInfo> findAllCommentsByPosts(List<PostInfo> listOfPosts);
	
	public String deleteCommentById(Integer id);
	
	public String updatePost(PostInfo post);
}
