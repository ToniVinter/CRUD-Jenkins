package com.antoniovinter.crud.controller;

import com.antoniovinter.crud.dto.PostDto;
import com.antoniovinter.crud.model.Post;
import com.antoniovinter.crud.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public List<PostDto> findAllPosts(){
        return postService.findAllPosts();
    }

    @PostMapping()
    public Post savePost(@RequestParam int id, @RequestBody Post post){
        return postService.savePost(id, post);
    }


}
