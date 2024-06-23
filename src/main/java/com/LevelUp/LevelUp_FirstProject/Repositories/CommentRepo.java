package com.LevelUp.LevelUp_FirstProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LevelUp.LevelUp_FirstProject.Entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
