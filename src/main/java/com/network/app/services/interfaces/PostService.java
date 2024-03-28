package com.network.app.services.interfaces;

import com.network.app.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPostById(UUID id);
    List<Post> getAllPosts();
    List<Post> getAllPostsByUsername(String username);
    void deletePost(Post post);
    Post updatePost(Post post);
    void addPostByUsername(Post post, String username);
}
