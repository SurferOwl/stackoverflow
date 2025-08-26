package com.example.project_ps_real.service;
import com.example.project_ps_real.entity.Tag;
import com.example.project_ps_real.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTag(){
        return (List<Tag>) this.tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        Optional<Tag> tag=this.tagRepository.findById(id);
        if(tag.isPresent()){
            System.out.println("Tag found with ID: " + id);
            return tag.get();
        }
        else
        {
            System.out.println("Tag not found!");
            return null;
        }
    }

    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag updateTag(Tag tag) {
        Optional<Tag> isTag = this.tagRepository.findById(tag.getTagId());
        if(isTag.isPresent()){
            return this.tagRepository.save(tag);
        }else{
            return null;
        }
    }

    public String deleteTagById(Long id){
        Optional<Tag> tag=this.tagRepository.findById(id);
        if(tag.isPresent()) {
            this.tagRepository.deleteById(id);
            return "Deletion Successfully";
        }
        else {
            return "Failed to delete user with id " + id;
        }
    }
}
