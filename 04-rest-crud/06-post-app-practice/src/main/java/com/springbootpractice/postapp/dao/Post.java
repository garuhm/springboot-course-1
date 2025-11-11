package com.springbootpractice.postapp.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "poster_id")
    private int posterId;

    @Column(name = "content")
    private String content;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "likes")
    private int likes;

    public Post() {
    }

    public Post(int posterId, String content) {
        this.posterId = posterId;
        this.content = content;
        commentCount = 0;
        likes = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void addComment(){
        commentCount++;
    }

    public void removeComment(){
        commentCount--;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void addLike(){
        likes++;
    }

    public void removeLike(){
        likes--;
    }
}
