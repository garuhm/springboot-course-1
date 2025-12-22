package com.xitter.app.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne()
    @JoinColumn(name="user")
    private User user ;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Comment> comments;

    @Column(name="likes")
    private int likes;

    public Post() {
    }

    public Post(User user, List<Comment> comments) {
        this.user = user;
        this.comments = comments;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Post {\n" +
                "id: " + id +
                "\nuser: " + user +
                "\ncomments count: " + comments.size() +
                "\nlikes: " + likes +
                "\n}";
    }
}
