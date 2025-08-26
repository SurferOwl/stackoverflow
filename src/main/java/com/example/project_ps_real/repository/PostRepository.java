package com.example.project_ps_real.repository;

import com.example.project_ps_real.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Object findAll(Sort date);
}