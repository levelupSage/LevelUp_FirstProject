package com.LevelUp.LevelUp_FirstProject.Service;

import java.util.List;

import com.LevelUp.LevelUp_FirstProject.Dto.PostDto;
import com.LevelUp.LevelUp_FirstProject.Dto.PostResponse;

public interface PostService {

	//Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	//List<PostDto> getAllPost();
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//search Post
	List<PostDto> searchPosts(String keyword);

	List<PostDto> getPostsByUser(Integer userId);

	//List<PostDto> getAllPost(Integer pageSize, Integer pageNubmer);
	PostResponse getAllPost(Integer pageSize, Integer pageNubmer, String sortById, String sortDir);
	
}
