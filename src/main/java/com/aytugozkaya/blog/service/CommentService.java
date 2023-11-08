package com.aytugozkaya.blog.service;

import com.aytugozkaya.blog.dtos.CommentDto;
import com.aytugozkaya.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId,CommentDto commentDto);
    List<CommentDto> getAllCommentsByPostId(long postId);
    CommentDto getCommentById(long commentId, long postId);
    CommentDto updateComment(long commentId, long postId, CommentDto commentDto);
    void deleteComment(long commentId, long postId);
}
