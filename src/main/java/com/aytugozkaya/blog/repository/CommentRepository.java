package com.aytugozkaya.blog.repository;

import com.aytugozkaya.blog.dtos.CommentDto;
import com.aytugozkaya.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    public List<Comment> findByPostId(Long postId);
}
