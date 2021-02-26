package com.spring.springblog.controllers;


import com.spring.springblog.Repositories.PostRepository;
import com.spring.springblog.Repositories.UserRepository;
import com.spring.springblog.models.Post;

import com.spring.springblog.models.User;
import com.spring.springblog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.spring.springblog.services.EmailService;


import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final UserService userService;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, UserService userService, EmailService emailService){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {

        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(Model model, @PathVariable long id) {
//        get single post by id later
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/edit/{id}")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        Post post = postsDao.getOne(id);
        User user = userService.getLoggedInUser();
        if (user.getId()==post.getUser().getId()){
            model.addAttribute("post", postsDao.getOne(id));
        }else return "redirect:/posts";
        return "posts/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        //TODO: Change user to logged in user dynamic
        User user = userService.getLoggedInUser();
        if (user.getId() == post.getUser().getId()){
            postsDao.save(post);
        }
        else return "redirect:/posts";


        return "redirect:/posts";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, @ModelAttribute Post post){
        User user = userService.getLoggedInUser();
        if (user.getId() == post.getUser().getId()){
            postsDao.save(post);
            System.out.println("Deleting post...");
            postsDao.deleteById(id);
        }
        else return "redirect:/posts";

        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        // Will throw if no users in the db!
        // In the future, we will get the logged in user
        User user = userService.getLoggedInUser();
        post.setUser(user);

        Post savedPost = postsDao.save(post);

        //send an email when an ad is successfully saved
        String subject = "New Post Created: " + savedPost.getTitle();
        String body = "Dear " + savedPost.getUser().getUsername()
                + ". Thank you for creating a post. Your post id is "
                + savedPost.getId();

        emailService.prepareAndSend(savedPost, subject, body);
        return "redirect:/posts";
    }
}



