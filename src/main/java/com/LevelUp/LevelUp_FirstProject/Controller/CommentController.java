package com.LevelUp.LevelUp_FirstProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LevelUp.LevelUp_FirstProject.Dto.CommentDto;
import com.LevelUp.LevelUp_FirstProject.Dto.ResponseApi;
import com.LevelUp.LevelUp_FirstProject.Service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService; 
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, 
			@PathVariable Integer postId){
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ResponseApi> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComments(commentId);
		return new ResponseEntity<ResponseApi>(new ResponseApi("Comment deleted successfully !!", true), HttpStatus.OK);
	}
}
