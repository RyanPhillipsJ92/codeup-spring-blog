package com.spring.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollController {
    //******************
    // URL /roll-dice
    //******************
    @RequestMapping(path = "choose-dice", method = RequestMethod.GET)
    public  String GetDiceGuess(){
        return "choose-dice";
    }

    @RequestMapping(path = "/dice-roll/{n}", method = RequestMethod.GET)
    @ResponseBody
    public  String UserDiceGuess(@PathVariable int n){
        double random = Math.random();
        random = random * 6 + 1;
        int randomint = (int) random;
        if (n == randomint){
            return "you guessed" + n + "and the number was" + randomint;
        }
        return "you guessed wrong, " + n + " and "  + randomint+ " are different";
    }

//    @PostMapping("/dice-roll")
//    public String joinCohort(@RequestParam(name = "userNumber") Integer userNumber, Model model) {
//        model.addAttribute("userNumber", "you guessed " + userNumber + "!");
//        return "dice-roll";
//    }

}

