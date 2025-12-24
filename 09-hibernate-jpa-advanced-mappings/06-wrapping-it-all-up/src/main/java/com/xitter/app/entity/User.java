package com.xitter.app.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Collection<Role> roles;

    @Column(name="username")
    private String username;
    @Column(name="display_name")
    private String displayName;
    @Column(name="password")
    private String password;
    @Column(name="enabled")
    private boolean enabled;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "follower_following",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User> following;
    @ManyToMany(mappedBy = "following")
    private Set<User> followers;

    public User() {
    }

    public User(String username, String displayName, String password, boolean enabled) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String displayName, String password, Collection<Role> roles, boolean enabled) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if(roles == null){
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public void addPost(Post post){
        if(posts == null){
            posts = new ArrayList<>();
        }
        posts.add(post);
    }

    public void removePost(Post post){
        posts.remove(post);
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

    public void disconnectCommentsOnPostsFromUsers(){
        for(Post post : posts){
            for(Comment comment : post.getComments()){
                if(!comment.getUser().equals(this)){
                    comment.getUser().getComments().remove(comment);
                }
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "User {\n" +
                "id: " + id +
                "\nroles: " + roles +
                "\nusername: '" + username + '\'' +
                "\ndisplayName: '" + displayName + '\'' +
                "\npassword: '" + password + '\'' +
                "\nenabled: " + enabled +
                "\nposts count: " + posts.size() +
                "\ncomments count: " + comments.size() +
                "\nfollowing count: " + following.size() +
                "\nfollowers count: " + followers.size() +
                "\n}";
    }
}
