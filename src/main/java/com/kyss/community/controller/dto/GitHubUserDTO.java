package com.kyss.community.controller.dto;

/**
 * @ClassName GitHubUserDTO
 * @Description TODO
 * @Author davidt
 * @Date 6/4/2020 12:09 PM
 * @Version 1.0
 **/


public class GitHubUserDTO {
    private Long id;
    private String name;
    private String bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
