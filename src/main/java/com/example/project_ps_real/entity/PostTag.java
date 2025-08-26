package com.example.project_ps_real.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_tag")
@Getter
@Setter
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postTagId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"postTagList", "comments", "votes", "user"}) // prevent recursion
    private Post post;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @JsonIgnoreProperties({"postTagList"}) // prevent infinite recursion
    private Tag tag;

    public PostTag() {
    }

    public PostTag(Long postTagId, Post post, Tag tag) {
        this.postTagId = postTagId;
        this.post = post;
        this.tag = tag;
    }

    public PostTag(Post savedPost, Tag tag) {}
}

