package com.example.project_ps_real.controller;

import com.example.project_ps_real.entity.PostTag;
import com.example.project_ps_real.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/postTags")
public class PostTagController {

    @Autowired
    private PostTagService postTagService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<PostTag> getAllPostTag(){
        return this.postTagService.retrieveAll();
    }

    @GetMapping("/getById")
    @ResponseBody
    public PostTag getPostTagById(@RequestParam("id") Long id) {
        System.out.println("Received request for post ID: " + id);
        return this.postTagService.findByIdPostTag(id);
    }

    @PostMapping("/addPostTag")
    @ResponseBody
    public PostTag addPostTag(@RequestBody PostTag postTag) {
        return this.postTagService.addPostTag(postTag);
    }

    @PutMapping("/updatePostTag")
    @ResponseBody
    public PostTag updatePostTag(@RequestBody PostTag postTag) {
        return this.postTagService.updatePostTag(postTag);
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deletePostTagById(@RequestParam("id") Long id) {
        System.out.println("Received request for post ID: " + id);
        return this.postTagService.deletePostTagById(id);

    }
}
