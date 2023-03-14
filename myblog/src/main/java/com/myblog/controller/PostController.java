package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {

        this.postService = postService;
    }

   //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }

    @GetMapping
    public List<PostDto> getAllPosts(){
       return postService.getAllPosts();
    }

    //http://localhost:8080/api/posts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);

    }
    //http://localhost:8080/api/posts/{id}
   @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id){
      PostDto postResponse = postService.updatePost(postDto, id);
      return ResponseEntity.ok(postResponse);

    }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id")long id){
        postService.deletePostById(id);
        return new ResponseEntity<String>("Post is Deleted!", HttpStatus.OK);



    }
}
