package com.network.app.controllers;

import com.network.app.models.Post;
import com.network.app.services.interfaces.PostService;
import com.network.app.services.interfaces.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post/")
@PreAuthorize("hasAuthority('USER')")
public class PostController {

    private final PostService postService;
    private final UserServices userServices;

    @Autowired
    public PostController(PostService postService, UserServices userServices) {
        this.postService = postService;
        this.userServices = userServices;
    }

    @PostMapping("{username}")
    public ResponseEntity<String> addPost(
            @RequestBody Post post,
            @PathVariable("username") String username
    ){
        if (!userServices.isCurrentUserEquals(username))
            return new ResponseEntity<>("Can't add post to current user", HttpStatus.BAD_REQUEST);

        postService.addPostByUsername(post, username);
        return new ResponseEntity<>("Post successfully added", HttpStatus.OK);
    }

    @PutMapping("{username}")
    public ResponseEntity<String> updatePost(
            @RequestBody Post post,
            @PathVariable("username") String username
    ){
        if (!userServices.isCurrentUserEquals(username))
            return new ResponseEntity<>("Can't add post to current user", HttpStatus.BAD_REQUEST);

        postService.updatePost(post);
        return new ResponseEntity<>("Post successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("{username}/{postId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> deletePost(
            @PathVariable("username") String username,
            @PathVariable("postId") UUID postId
            ){
        boolean isUserAuthority = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("USER"));

        if (!userServices.isCurrentUserEquals(username) && isUserAuthority)
            return new ResponseEntity<>("Can't add post to current user", HttpStatus.BAD_REQUEST);

        postService.deletePost(postService.getPostById(postId));
        return new ResponseEntity<>("Post successfully added", HttpStatus.OK);
    }

    // todo add methods with pagination to get posts
}
