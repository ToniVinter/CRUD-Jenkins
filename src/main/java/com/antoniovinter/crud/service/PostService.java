package com.antoniovinter.crud.service;

import com.antoniovinter.crud.converter.PostConverter;
import com.antoniovinter.crud.dto.PostDto;
import com.antoniovinter.crud.exceptions.PostNotFound;
import com.antoniovinter.crud.exceptions.UserNotFound;
import com.antoniovinter.crud.model.Post;
import com.antoniovinter.crud.model.User;
import com.antoniovinter.crud.repository.PostRepository;
import com.antoniovinter.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository repo;
    private UserRepository userRepo;
    private PostConverter postConverter;

    public PostService(PostRepository repo, UserRepository userRepo, PostConverter postConverter) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.postConverter = postConverter;
    }

    public List<PostDto> findAllPosts() {
        List<Post> post = repo.findAll();
        List<PostDto> postDtos = repo.findAll().stream()
                .map(p -> postConverter.PostToDto(p))
                .collect(Collectors.toList());
        return postDtos;
    }

    public PostDto findPostById(int id) {
        Post post =  repo.findById(id)
                .orElseThrow(() -> new PostNotFound("post with id not found, id: " + id));

        return postConverter.PostToDto(post);
    }

    public List<PostDto> findAllPostsByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFound("user not found with id: " + userId));
        List<PostDto> postDtos = repo.findAll().stream()
                .filter(p -> p.getUser().equals(user))
                .map(p -> postConverter.PostToDto(p))
                .collect(Collectors.toList());

        if (postDtos != null) {
            return postDtos;
        } else {
            throw new PostNotFound("posts not found with user ID: " + userId);
        }
    }

    public Post savePost(int userId, Post post) {
        Optional<User> userProfile = userRepo.findById(userId);
        if (userProfile != null) {
            post.setUser(userProfile.get());
            return post;
        } else {
            throw new UserNotFound("User Is Not Found");
        }

    }

}
