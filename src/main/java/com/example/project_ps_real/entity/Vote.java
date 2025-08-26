package com.example.project_ps_real.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"votes", "posts"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnoreProperties({"votes", "postTagList", "comments", "user"})
    private Post post;

    @Column(name = "is_liked")
    private boolean liked;

    public Vote() {
    }

    public Vote(Long voteId, User user, Post post, Boolean isLiked) {
        this.voteId = voteId;
        this.user = user;
        this.post = post;
        this.liked = isLiked;
    }
}
