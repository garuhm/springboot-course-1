package com.xitter.app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WebComment {
    @NotNull(message = "required")
    @Size(min=1, message = "must contain at least 1 character")
    private String content;
    private String username;
    private int id;
    private int postId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}

