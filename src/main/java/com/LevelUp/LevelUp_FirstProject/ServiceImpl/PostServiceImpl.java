package com.LevelUp.LevelUp_FirstProject.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.LevelUp.LevelUp_FirstProject.Dto.PostDto;
import com.LevelUp.LevelUp_FirstProject.Dto.PostResponse;
import com.LevelUp.LevelUp_FirstProject.Entity.Category;
import com.LevelUp.LevelUp_FirstProject.Entity.Post;
import com.LevelUp.LevelUp_FirstProject.Entity.Users;
import com.LevelUp.LevelUp_FirstProject.Repositories.CategoryRepo;
import com.LevelUp.LevelUp_FirstProject.Repositories.PostRepo;
import com.LevelUp.LevelUp_FirstProject.Repositories.UserRepo;
import com.LevelUp.LevelUp_FirstProject.Service.PostService;
import com.LevelUp.LevelUp_FirstProject.Validator.ResourceNotFoundException;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		Users user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "user Id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUsers(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatePost = this.postRepo.save(post);
		return modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	//public List<PostDto> getAllPost(Integer pageSize, Integer pageNubmer) {
	public PostResponse getAllPost(Integer pageSize, Integer pageNubmer, String sortById, String sortDir) {	
		//int pageSize = 5;
		//int pageNubmer = 1;
		//Pageable pageable = PageRequest.of(pageNubmer, pageSize);
//		Sort sort = null;
//		if(sortDir.equalsIgnoreCase("asc")){
//			sort = Sort.by(sortById).ascending();
//		}else {
//			sort = Sort.by(sortById).descending();
//		}
		Sort sort = (sortDir.equalsIgnoreCase("asc")?Sort.by(sortById).ascending() : Sort.by(sortById).descending()); 
		Pageable pageable = PageRequest.of(pageNubmer, pageSize, sort);
		Page<Post>	pagePost= postRepo.findAll(pageable);
		List<Post> allPost = pagePost.getContent(); //this.postRepo.findAll(pageable);
		List<PostDto> collect = allPost.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalPage(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}
	
	@Override
	public List<PostDto> getPostsByUser(Integer userId){
		Users user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = this.postRepo.findByUsers(user);
		List<PostDto> postDto= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}
	
	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.searchById("%"+keyword+"%");
		//List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

}
