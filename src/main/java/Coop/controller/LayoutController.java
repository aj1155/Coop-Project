package Coop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Coop.mapper.ProjectMapper;
import Coop.service.UserService;

@Controller
@RequestMapping("/layout")
public class LayoutController {
	
	@Autowired ProjectMapper projectMapper;
	@Autowired UserService userService;
	@RequestMapping("/main/home.do")
    public String index(Model model) {
		model.addAttribute("ProjectList",projectMapper.selectById(userService.getCurrentUser()));
		model.addAttribute("user",userService.getCurrentUser());
        return "layout/main/home";
    }
	
    @RequestMapping(value="/home/login.do", method=RequestMethod.GET)
    public String login(Model model) {
        return "home/login";
    }
}
