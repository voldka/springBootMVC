package com.example.springmvccrud.repository;

import com.example.springmvccrud.dto.PostDTO;
import com.example.springmvccrud.entity.PostEntity;
import org.springframework.data.repository.Repository;

public interface PostRepository extends Repository<PostEntity,Long> {
    public PostEntity save(PostEntity postEntity);
}
