package com.example.project_ps_real.service;

import com.example.project_ps_real.entity.PostTag;
import com.example.project_ps_real.repository.PostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostTagService {

    @Autowired
    private PostTagRepository postTagRepository;

    public PostTagService(PostTagRepository postTagRepository) {
        this.postTagRepository = postTagRepository;
    }

    public List<PostTag> retrieveAll() {
        return (List<PostTag>) this.postTagRepository.findAll();
    }

    public PostTag findByIdPostTag(Long id) {
        Optional<PostTag> postTag = this.postTagRepository.findById(id);
        if(postTag.isPresent()){
            return postTag.get();
        }else{
            return null;
        }
    }

    public PostTag addPostTag(PostTag postTag) {
        System.out.println("PostTag: " + postTag.toString());
        return this.postTagRepository.save(postTag);
    }

    public PostTag updatePostTag(PostTag postTag) {
        Optional<PostTag> isPostTag=postTagRepository.findById(postTag.getPostTagId());
        if(isPostTag.isPresent()){
            System.out.println("PostTag with id:"+postTag.getPostTagId());
            return this.postTagRepository.save(postTag);
        }else{
            System.out.println("PostTag is not found");
            return null;
        }
    }
    public String deletePostTagById(Long id) {
        Optional<PostTag> postTag = this.postTagRepository.findById(id);
        if(postTag.isPresent()) {
            this.postTagRepository.deleteById(id);
            return "Entry successfully deleted!";
        }
        else {
            return "Failed to delete user with id " + id;
        }
    }

}
