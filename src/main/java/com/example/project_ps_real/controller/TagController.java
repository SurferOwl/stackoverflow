package com.example.project_ps_real.controller;

import com.example.project_ps_real.entity.Tag;
import com.example.project_ps_real.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/getAllTags")
    @ResponseBody
    public List<Tag> getAllTag(){
        return this.tagService.getAllTag();
    }

    @GetMapping("/getById")
    @ResponseBody
    public Tag getTagById(Long id) {
        return this.tagService.getTagById(id);
    }

    @PostMapping("/addTag")
    @ResponseBody
    public Tag insertTag(@RequestBody Tag tag) {return this.tagService.addTag(tag);}

    @PutMapping("/updateTag")
    @ResponseBody
    public Tag updateTag(@RequestBody Tag tag) {return this.tagService.updateTag(tag);}

    @DeleteMapping("/deleteTagById")
    @ResponseBody
    public String deleteTagById(@RequestParam Long id) {return this.tagService.deleteTagById(id);}

}
