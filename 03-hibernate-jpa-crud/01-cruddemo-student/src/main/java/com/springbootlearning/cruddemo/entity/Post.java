package com.springbootlearning.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "likes")
    private int likes;

    @Column(name = "comments")
    private int comments;

    @Column(name = "content")
    private String content;

    @Column(name = "is_edited")
    private boolean isEdited;

    public Post() {
    }

    public Post(String content) {
        this.content = content;
        likes = 0;
        comments = 0;
        isEdited = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", likes=" + likes +
                ", comments=" + comments +
                ", content='" + content + '\'' +
                ", isEdited=" + isEdited +
                '}';
    }
}
