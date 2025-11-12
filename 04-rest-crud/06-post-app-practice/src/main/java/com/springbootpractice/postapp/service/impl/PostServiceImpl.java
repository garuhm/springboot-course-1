package com.springbootpractice.postapp.service.impl;

import com.springbootpractice.postapp.dao.CommentRepository;
import com.springbootpractice.postapp.dao.PostRepository;
import com.springbootpractice.postapp.entity.Comment;
import com.springbootpractice.postapp.entity.Post;
import com.springbootpractice.postapp.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(int id) {
        Optional<Post> result = postRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RuntimeException("Could not find post with id of " + id);
    }

    @Override
    public List<Post> getPostsByUser(int id) {
        return postRepository.getByUser(id);
    }

    @Override
    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public String deletePost(int id) {
        postRepository.deleteById(id);
        return "Successfully deleted post with id of: " + id;
    }

    @Override
    @Transactional
    public int deletePostsByUser(int id) {
        return postRepository.deleteByUser(id);
    }

    @Override
    public Comment getCommentById(int id) {
        Optional<Comment> result = commentRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RuntimeException("Could not find comment with id of " + id);
    }

    @Override
    public List<Comment> getCommentsByPost(int id) {
        return commentRepository.findByPost(id);
    }

    @Override
    public List<Comment> getCommentsByUser(int id) {
        return commentRepository.findByUser(id);
    }

    @Override
    public int getCommentAmountByUserInPost(int posterId, int postId) {
        return commentRepository.findNumberOfByUserInPost(posterId,postId).size();
    }

    @Override
    public List<Integer> getPostIdsWithCommentsByUser(int id) {
        return commentRepository.findPostsWithCommentsByUser(id);
    }

    @Override
    public List<Integer> getUserIdsWithCommentOnPost(int id) {
        return commentRepository.findUsersWithCommentsOnPost(id);
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public String deleteComment(int id) {
        commentRepository.deleteById(id);
        return "Successfully deleted comment with id of: " + id;
    }

    @Override
    @Transactional
    public int deleteCommentsByPost(int id) {
        return commentRepository.deleteByPost(id);
    }

    @Override
    @Transactional
    public int deleteCommentsByUser(int id) {
        return commentRepository.deleteByUser(id);
    }
}
