package com.afc.springreact.post;

import java.util.Date;

import com.afc.springreact.user.User;
import com.afc.springreact.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    PostRepository postRepository;
    UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void save(Post post, User user) {
        post.setTimestamp(new Date());
        post.setUser(user);
        postRepository.save(post);
    }

    public Page<Post> getPosts(Pageable page) {
        return postRepository.findAll(page);
    }

    public Page<Post> getPostsOfUser(String username, Pageable page) {
        User user = userService.getByUsername(username);
        return postRepository.findByUser(user, page);
    }

    public Page<Post> getOldPosts(long id, Pageable page) {
        return postRepository.findByIdLessThan(id, page);
    }

    public Page<Post> getOldPostsOfUser(long id, String username, Pageable page) {
        User user = userService.getByUsername(username);
        return postRepository.findByIdLessThanAndUser(id, user, page);
    }

    public long getNewPostsCount(long id) {
        return postRepository.countByIdGreaterThan(id);
    }
    
}
