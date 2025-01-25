package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.Comment;
import com.nt.dto.Post;
import com.nt.model.CommentInfo;
import com.nt.model.PostInfo;
import com.nt.model.UserInfo;
import com.nt.repository.CommentRepository;
import com.nt.repository.PostRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public String savePost(Post post) {
		PostInfo info = new PostInfo();
		BeanUtils.copyProperties(post, info);
		info.setUser((UserInfo) session.getAttribute("user"));
		postRepository.save(info);
		return "post saved";
	}
	@Override
	public List<PostInfo> findAllPostsByUser() {
		return postRepository.findByUser((UserInfo) session.getAttribute("user"));
	}
	
	@Override
	public List<PostInfo> findAll() {
		return postRepository.findAll();
	}
	
	@Override
	public Optional<PostInfo> findPostById(Integer id) {
		return postRepository.findById(id);
	}
	
	@Override
	public String saveComment(Comment comment) {
		CommentInfo info = new CommentInfo();
		BeanUtils.copyProperties(comment, info);
		info.setPost((PostInfo) session.getAttribute("post"));
		commentRepository.save(info);
		return "comment posted";
	}
	
	@Override
	public List<CommentInfo> findAllCommentsByPost(PostInfo post) {
		return commentRepository.findByPost(post);
	}
	
	@Override
	public List<CommentInfo> findAllCommentsByPosts(List<PostInfo> listOfPosts) {
		return commentRepository.findAllByPostIn(listOfPosts);
	}
	
	@Override
	public String deleteCommentById(Integer id) {
		Optional<CommentInfo> comment = commentRepository.findById(id);
		if(comment.isPresent()) {
			commentRepository.deleteById(id);
			return "sucess";
		}
		return "comment not exits";
	}
	@Override
	public String updatePost(PostInfo post) {
		
		if(post.getId()!=null) {
			Optional<PostInfo> opt = postRepository.findById(post.getId());
			if(opt.isPresent()) {
				post.setCreatedOn(opt.get().getCreatedOn());
				post.setUser(opt.get().getUser());
				postRepository.save(post);
			}
			return "post updated sucessfully";
		}
		return "post not exits";
	}
	
	@Override
	public List<PostInfo> searchPost(String query) {
		return postRepository.searchPost(query);
	}
}
