package com.aytugozkaya.blog.repository;

import com.aytugozkaya.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post,Long>{
}
