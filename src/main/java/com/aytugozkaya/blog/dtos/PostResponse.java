package com.aytugozkaya.blog.dtos;

import com.aytugozkaya.blog.dtos.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private boolean last;
    private long totalElements;
}
