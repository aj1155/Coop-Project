package Coop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Coop.mapper.ProjectMapper;
import Coop.mapper.UserMapper;
import Coop.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
		@Autowired UserMapper userMapper;
		@Autowired ProjectMapper projectMapper;
		@Autowired UserService userService;
		
		@RequestMapping(value="/index.do", method=RequestMethod.GET)
	    public String index(Model model) {
			System.out.println("조인");
	        return "home";
	    }
		
		

	    @RequestMapping(value="/regist.do", method=RequestMethod.GET)
	    public String login(Model model) {
	    	model.addAttribute("user",null);
	        return "regist";
	    }

}
