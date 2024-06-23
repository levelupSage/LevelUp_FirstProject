package com.LevelUp.LevelUp_FirstProject.Service;

import com.LevelUp.LevelUp_FirstProject.Dto.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto CommentDto, Integer postId);
	
	void deleteComments(Integer commentId);
}
