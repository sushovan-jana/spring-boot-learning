package com.kodnest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class Greeting {

    @GetMapping("/greet")
    @ResponseBody
    public String greet() {
        return "Hello User";
    }
}