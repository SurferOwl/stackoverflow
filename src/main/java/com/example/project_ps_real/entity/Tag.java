package com.example.project_ps_real.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tag")
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"tag"}) // so PostTag still includes post but avoids recursion
    private List<PostTag> postTagList;

    public Tag(Long tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public Tag() {}
}

