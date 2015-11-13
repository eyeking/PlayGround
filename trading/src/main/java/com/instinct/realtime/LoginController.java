package com.instinct.realtime;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by nshah on 10/19/2015.
 */
@Controller
public class LoginController {

    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public String welcomePage(Model model)
    {
        model.addAttribute("title", "Spring Security Hello World");
        model.addAttribute("message", "This is welcome page!");
        return "hello";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {

        model.addAttribute("title", "Spring Security Hello World");
        model.addAttribute("message", "This is protected page!");
        return "admin";

    }

}
