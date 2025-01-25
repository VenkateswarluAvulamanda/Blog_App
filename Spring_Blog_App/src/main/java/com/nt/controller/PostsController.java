package com.nt.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.dto.Comment;
import com.nt.dto.Post;
import com.nt.model.CommentInfo;
import com.nt.model.PostInfo;
import com.nt.model.UserInfo;
import com.nt.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostsController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/post")
	public String createPost(Model model) {
		model.addAttribute("post", new Post());
		return "post";
	}
	@PostMapping("/post")
	public String post(@ModelAttribute Post post, 
			Map<String, Object> map) {
		postService.savePost(post);
		List<PostInfo> listOfPosts = postService.findAllPostsByUser();
		map.put("listOfPosts", listOfPosts);
		return "dashboard";
	}

	@GetMapping("/postInfo")
	public String showPostInfo(@RequestParam Integer id, Model model) {
		Optional<PostInfo> post = postService.findPostById(id);
		if(post.isPresent()) {
			PostInfo postInfo = post.get();
			session.setAttribute("post", postInfo);
			model.addAttribute("post", postInfo);
			List<CommentInfo> listOfComments = postService.findAllCommentsByPost(postInfo);
			model.addAttribute("listOfComments", listOfComments);
		}
		model.addAttribute("comment", new Comment());
		return "postInfo";
	}
	@GetMapping("/comments") 
	public String comments(Model model) {
		List<PostInfo> listOfPosts = postService.findAllPostsByUser();
		List<CommentInfo> listOfComments = postService.findAllCommentsByPosts(listOfPosts);
		model.addAttribute("listOfComments", listOfComments);
		return "comments";
	}
	@PostMapping("/postComment")
	public String saveComment(@ModelAttribute Comment comment, @RequestParam Integer id) {
		postService.saveComment(comment);
		return "redirect:/postInfo?id="+id;
	}
	
	@GetMapping("/deleteComment")
	public String deleteComment(@RequestParam Integer id) {
		postService.deleteCommentById(id);
		return "redirect:/comments";
	}
	
	@GetMapping("/editPost")
	public String editPost(@RequestParam Integer id, Model model) {
		Optional<PostInfo> opt = postService.findPostById(id);
		if(opt.isPresent()) {
			PostInfo post = opt.get();
			model.addAttribute("post", post);
		}
		return "editPost";
	}
	
	@PostMapping("/editPost")
	public String edit(@ModelAttribute PostInfo info,Model model) {
		info.setUser((UserInfo) session.getAttribute("user"));
		postService.updatePost(info);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public void dashboard(Model model) {
		List<PostInfo> listOfPosts = postService.findAllPostsByUser();
		model.addAttribute("listOfPosts", listOfPosts);
	}
	
	@PostMapping("/search")
	public String searchPost(@RequestParam String query, Model model) {
		List<PostInfo> listOfPosts = postService.searchPost(query);
		model.addAttribute("listOfPosts", listOfPosts);
		return "index";
	}
}






