package com.aytugozkaya.blog.dtos;

import com.aytugozkaya.blog.entity.Comment;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String description;
    private Set<Comment> comments;

}
