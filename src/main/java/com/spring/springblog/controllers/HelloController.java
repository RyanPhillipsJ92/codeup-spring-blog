package com.spring.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String seyHello(@PathVariable String name){
        return "Hello " + name + "!";
    }


//    DEFAULTS TO A GET CAN DO A POST AS WELL
    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public  String addOne(@PathVariable int number){
    return number + " plus one is " + (number + 1) + "!";
    }
}
