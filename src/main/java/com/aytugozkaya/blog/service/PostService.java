package com.aytugozkaya.blog.service;

import com.aytugozkaya.blog.dtos.PostDto;
import com.aytugozkaya.blog.dtos.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
    String deletePost(long id);
}
