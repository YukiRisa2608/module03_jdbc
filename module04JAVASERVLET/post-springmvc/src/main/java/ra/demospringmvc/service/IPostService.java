package ra.demospringmvc.service;

import ra.demospringmvc.model.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAllPost();
    List<Post> findAllPostByTitleOrContent(String search);
    void addPost(Post post);
    void editPost(Post post);
    void deletePost(int postId);
}
