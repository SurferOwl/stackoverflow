package com.example.project_ps_real.repository;

import com.example.project_ps_real.entity.Post;
import com.example.project_ps_real.entity.PostTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagRepository extends CrudRepository<PostTag, Long> {
    void deleteByPost(Post post);
}
