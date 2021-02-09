package com.spring.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
//******************
// ALL POSTS
//******************
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public  String posts(){
        return "posts index page";
    }


    //******************
//    INDIVUAL POST
    //******************
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public  String post(@PathVariable String id){
        return "View an individual post";
    }
    //******************
//    Get form for CREATE POST
    //******************
    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public  String post_create(){
        return "View form for creating a post";
    }


    //******************
// POST CREATE URL
    //******************
    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public  String post_post(){
        return "create a new post";
    }
}





