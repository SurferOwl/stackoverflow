package com.example.project_ps_real.service;

import com.example.project_ps_real.entity.Post;
import com.example.project_ps_real.entity.PostTag;
import com.example.project_ps_real.entity.Tag;
import com.example.project_ps_real.repository.PostRepository;
import com.example.project_ps_real.repository.PostTagRepository;
import com.example.project_ps_real.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    @Autowired
    private TagRepository tagRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> retrieveAll() {
        return (List<Post>) postRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Post findByIdPost(Long id) {
        Optional<Post> post = this.postRepository.findById(id);
        if(post.isPresent()){
            return post.get();
        }else{
            return null;
        }
    }

    public Post addPost(Post post) {
        System.out.println("Post: " + post.toString());
        return this.postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Post post) {
        if (post.getPostId() == null) {
            throw new IllegalArgumentException("Post ID must not be null when updating.");
        }

        Post existingPost = postRepository.findById(post.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + post.getPostId()));

        List<PostTag> currentPostTags = existingPost.getPostTagList();
        List<PostTag> incomingPostTags = post.getPostTagList() != null ? post.getPostTagList() : new ArrayList<>();

        // Step 1: Convert current tags to a set of tagIds
        Set<Long> existingTagIds = currentPostTags.stream()
                .map(pt -> pt.getTag().getTagId())
                .collect(Collectors.toSet());

        // Step 2: Add only new ones
        for (PostTag pt : incomingPostTags) {
            Long tagId = pt.getTag().getTagId();
            if (!existingTagIds.contains(tagId)) {
                pt.setPost(existingPost);
                currentPostTags.add(pt);
            }
        }

        // Optional Step 3: Remove unused tags
        Set<Long> incomingTagIds = incomingPostTags.stream()
                .map(pt -> pt.getTag().getTagId())
                .collect(Collectors.toSet());

        currentPostTags.removeIf(pt -> !incomingTagIds.contains(pt.getTag().getTagId()));

        // Step 4: Update the rest
        existingPost.setTitle(post.getTitle());
        existingPost.setText(post.getText());
        existingPost.setImage(post.getImage());
        existingPost.setDate(post.getDate());
        existingPost.setStatus(post.getStatus());
        existingPost.setUser(post.getUser());
        existingPost.setParentPost(post.getParentPost());

        return postRepository.save(existingPost);
    }

    @Transactional
    public String deletePostById(Long id) {
        Optional<Post> post = this.postRepository.findById(id);

        if (post.isPresent()) {
            // First delete associated PostTags
            postTagRepository.deleteByPost(post.get());

            // Then delete the Post itself
            postRepository.deleteById(id);

            return "Entry successfully deleted!";
        } else {
            return "Failed to delete post with id " + id;
        }
    }

}
