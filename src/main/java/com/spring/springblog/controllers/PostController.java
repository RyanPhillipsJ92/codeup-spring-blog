package com.spring.springblog.controllers;


import com.spring.springblog.Repositories.PostRepository;
import com.spring.springblog.models.Post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {


    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model){
        List <Post> posts = postDao.findAll();

        model.addAttribute("title", "All Posts");

        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable long id, Model model){
//        get single post by id later
        model.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm(){
        return "Create a post here!";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "Creating a new post...";
    }

    @GetMapping("/posts/delete/{id}")
    public RedirectView deleteAd (@PathVariable Long id, Model model){
        if (postDao.findById(id).isPresent()){
            postDao.deleteById(id);
            return new RedirectView("/posts");
        }
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Post postToEdit = postDao.getOne(id);
        model.addAttribute("post", postToEdit);

       return "posts/edit";

    }
    @PostMapping("/posts/edit")
    public String editComplete(@RequestParam Long id, @RequestParam String title, @RequestParam String body ) {
        Post post = postDao.getOne(id);
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/posts";

    }

}



