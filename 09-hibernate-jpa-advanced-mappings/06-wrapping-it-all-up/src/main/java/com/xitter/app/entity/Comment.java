package com.xitter.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name="user")
    private User user ;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name="post")
    private Post post ;

    @Column(name="content")
    private String content;

    @Column(name="likes")
    private int likes;

    public Comment() {
    }

    public Comment(User user, Post post, String content, int likes) {
        this.user = user;
        this.post = post;
        this.content = content;
        likes = 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Comment {\n" +
                "id: " + id +
                "\nuser: " + user +
                "\npost: " + post +
                "\nlikes: " + likes +
                "\n}";
    }
}
