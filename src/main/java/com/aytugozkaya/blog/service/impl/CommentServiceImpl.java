package com.aytugozkaya.blog.service.impl;


import com.aytugozkaya.blog.dtos.CommentDto;
import com.aytugozkaya.blog.entity.Comment;
import com.aytugozkaya.blog.entity.Post;
import com.aytugozkaya.blog.exception.BlogAPIException;
import com.aytugozkaya.blog.exception.ResourceNotFound;
import com.aytugozkaya.blog.repository.CommentRepository;
import com.aytugozkaya.blog.repository.PostRepository;
import com.aytugozkaya.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.BadLocationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    ModelMapper mapper;
    CommentServiceImpl (CommentRepository commentRepository, PostRepository postRepository,ModelMapper mapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post","id",postId));
        comment.setPost(post);

        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long commentId, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFound("Post","id",commentId));
        if (comment.getPost().getId() == (post.getId())){
            return mapToDto(comment);
        }
        else throw new BlogAPIException(HttpStatus.NOT_FOUND,"Comment not found");

    }

    @Override
    public CommentDto updateComment(long commentId, long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFound("Post","id",commentId));

        if(comment.getPost().getId() == (post.getId())){
            comment.setBody(commentDto.getBody());
            comment.setName(commentDto.getName());
            comment.setEmail(commentDto.getEmail());
            return mapToDto(commentRepository.save(comment));
        }
        else throw new BlogAPIException(HttpStatus.NOT_FOUND,"Comment not found");
    }

    @Override
    public void deleteComment(long commentId, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFound("Post","id",commentId));
        if(comment.getPost().getId()== (post.getId())){
            commentRepository.delete(comment);

        }
        else throw new BlogAPIException(HttpStatus.NOT_FOUND,"Comment not found");

    }

    private CommentDto  mapToDto (Comment comment) {
          CommentDto commentDto = mapper.map(comment,CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }

    private Comment mapToEntity (CommentDto commentDto){
          Comment comment = mapper.map(commentDto,Comment.class);
//        comment.setId(commentDto.getId());
//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
