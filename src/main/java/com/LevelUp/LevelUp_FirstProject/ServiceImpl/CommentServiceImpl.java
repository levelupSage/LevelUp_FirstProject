package com.LevelUp.LevelUp_FirstProject.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LevelUp.LevelUp_FirstProject.Dto.CommentDto;
import com.LevelUp.LevelUp_FirstProject.Entity.Comment;
import com.LevelUp.LevelUp_FirstProject.Entity.Post;
import com.LevelUp.LevelUp_FirstProject.Repositories.CommentRepo;
import com.LevelUp.LevelUp_FirstProject.Repositories.PostRepo;
import com.LevelUp.LevelUp_FirstProject.Service.CommentService;
import com.LevelUp.LevelUp_FirstProject.Validator.ResourceNotFoundException;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto CommentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		Comment comment = this.modelMapper.map(CommentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComments(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepo.delete(com);
	}

}
