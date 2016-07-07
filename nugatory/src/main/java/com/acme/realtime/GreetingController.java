package com.acme.realtime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {
	
	@RequestMapping("/greeting/hello")
	public String getHello(Model model)
	{
		model.addAttribute("name", "World");
		System.out.println("From getHello");
		return "helloGreeting";
	}

}
