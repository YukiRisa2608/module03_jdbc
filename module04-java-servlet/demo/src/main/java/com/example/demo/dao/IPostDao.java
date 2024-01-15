package com.example.demo.dao;

import com.example.demo.model.Post;

import java.util.List;

public interface IPostDao {
    List<Post> findAllPost();
    List<Post> findAllPostByTitleOrContent(String search);
    void addPost(Post post);
    void editPost(Post post);
    void deletePost(int postId);
}
