package com.springbootpractice.postapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "poster_id")
    private int posterId;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private int likes;

    public Comment() {
    }

    public Comment(int postId, int posterId, String content) {
        this.postId = postId;
        this.posterId = posterId;
        this.content = content;
        likes = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", posterId=" + posterId +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                '}';
    }
}
