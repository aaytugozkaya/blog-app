package com.aytugozkaya.blog.controller;

import com.aytugozkaya.blog.dtos.CommentDto;
import com.aytugozkaya.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") Long postId,
                                                    @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.createComment(postId,commentDto));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable(name = "postId") Long postId){
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") Long postId,
                                                     @PathVariable(name = "id") Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(commentId,postId));
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment (@PathVariable(name = "postId") Long postId,
                                                     @PathVariable(name = "id") Long commentId,
                                                     @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(commentId,postId,commentDto));
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public void deleteComment(@PathVariable(name="commentId") Long commentId,
                                @PathVariable(name="postId") Long postId){
        commentService.deleteComment(commentId,postId);
    }



}
