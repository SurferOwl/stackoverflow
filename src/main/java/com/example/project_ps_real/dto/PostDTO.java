package com.example.project_ps_real.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostDTO {
    private Long userId;
    private String title;
    private String text;
    private String status;
    private String image;
    private List<Long> tagIds; // only pass tag IDs from frontend
}

