package com.xitter.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name="user")
    private User user ;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(name="content")
    private String content;

    @Column(name="likes")
    private int likes;


    public Post() {
    }

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
        likes = 0;
    }

    public void addComment(Comment comment){
        if(comments == null){
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    public void disconnectCommentsFromUsers() {
        for(Comment comment : comments){
            comment.getUser().removeComment(comment);
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
                "\ncontent: '" + content + '\'' +
                "\nuser: " + user +
                "\ncomments count: " + comments.size() +
                "\nlikes: " + likes +
                "\n}";
    }
}
