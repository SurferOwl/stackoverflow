package com.example.project_ps_real.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name ="users")
@Setter
@Getter
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="score")
    private Float score;

    @Column(name="is_admin")
    private Boolean isAdmin;

    @Column(name="is_banned")
    private Boolean isBanned;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"user"})
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"user"})
    private List<Vote> votes;

    @Column(name = "image")
    private String profilePicURL;

    public User(String email, Long userId, String name, String password, String phoneNumber, Float score, Boolean isAdmin, Boolean isBanned, List<Post> posts, List<Vote> votes, String profilePic) {
        this.email = email;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.score = score;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
        this.posts = posts;
        this.votes = votes;
        this.profilePicURL = profilePic;
    }

    public User() {

    }

    public void setPassword(String password) {
        if (password == null) {
            this.password = null;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.password = encoder.encode(password);
        }
    }
}
