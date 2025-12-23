package com.xitter.app.service;

import com.xitter.app.dao.PostDAO;
import com.xitter.app.dao.UserDAO;
import com.xitter.app.entity.Post;
import com.xitter.app.model.WebPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostServiceImpl implements PostService{
    private PostDAO postDAO;
    private UserDAO userDAO;

    @Autowired
    public PostServiceImpl(PostDAO postDAO, UserDAO userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Post> findAllPosts() {
        return postDAO.findAllPosts();
    }

    @Override
    public List<Post> findPostsByUsername(String username) {
        return postDAO.findPostsByUsername(username);
    }

    @Override
    public Post findPostById(int id) {
        return postDAO.findPostById(id);
    }

    @Override
    public void createPost(WebPost webPost) {
        Post post = new Post(userDAO.findUserByName(webPost.getUsername()), webPost.getContent());
        post.setComments(new ArrayList<>());
        userDAO.findUserByName(webPost.getUsername()).addPost(post);
        postDAO.createPost(post);
    }

    @Override
    public void updatePost(WebPost webPost) {
        Post post = findPostById(webPost.getId());
        post.setContent(webPost.getContent());
        postDAO.updatePost(post);
    }

    @Override
    public void deletePostById(int id) {
        postDAO.deletePostById(id);
    }
}
