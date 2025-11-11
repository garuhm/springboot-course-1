package com.springbootpractice.postapp.service;

import com.springbootpractice.postapp.entity.Comment;
import com.springbootpractice.postapp.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getPosts();
    Post getPostById(int id);
    List<Post> getPostsByUser(int id);
    Post createPost(Post post);
    String deletePost(int id);
    int deletePostsByUser(int id);

    Comment getCommentById(int id);
    List<Comment> getCommentsByPost(int id);
    List<Comment> getCommentsByUser(int id);
    int getCommentAmountByUserInPost(int posterId, int postId);
    List<Integer> getPostIdsWithCommentsByUser(int id);
    List<Integer> getUserIdsWithCommentOnPost(int id);
    Comment createComment(Comment comment);
    String deleteComment(int id);
    int deleteCommentsByPost(int id);
    int deleteCommentsByUser(int id);
}
