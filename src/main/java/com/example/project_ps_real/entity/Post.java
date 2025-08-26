package com.example.project_ps_real.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="posts")
@Getter
@Setter

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"posts"})
    private User user;

    @Column(name="date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties({"comments", "postTagList", "votes", "user", "parentPost"})
    private Post parentPost;

    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"parentPost", "postTagList", "votes", "user", "comments"})
    private List<Post> comments;

    @Column(name="title")
    private String title;

    @Column(name="text")
    private String text;

    @Column(name = "image")
    private String image;

    @Column(name="status")
    private String status;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"post"})
    private List<PostTag> postTagList;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"post"})
    private List<Vote> votes;

    public Post() {}
}