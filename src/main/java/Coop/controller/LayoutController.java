package Coop.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Coop.mapper.InviteMapper;
import Coop.mapper.ProjectMapper;
import Coop.model.Active;
import Coop.model.Project;
import Coop.service.MobileAuthenticationService;
import Coop.service.UserService;

@Controller
@RequestMapping("/layout")
public class LayoutController {
	
	@Autowired ProjectMapper projectMapper;
	@Autowired UserService userService;
	@Autowired InviteMapper inviteMapper;
	@Autowired MobileAuthenticationService mobileAuthenticationService;
	
	@RequestMapping("/main/home.do")
    public String index(Model model) {
		model.addAttribute("ProjectList",projectMapper.selectById(userService.getCurrentUser()));
		model.addAttribute("user",userService.getCurrentUser());
		Active act = new Active();
		act.setAct(null);
		model.addAttribute("class",act);
		model.addAttribute("inviteList",inviteMapper.selectByRecipient(userService.getCurrentUser().getId()));
		model.addAttribute("noticeList",projectMapper.selectByNotice(userService.getCurrentUser().getId()));
		return "layout/main/home";
    }
	
    @RequestMapping(value="/home/login.do", method=RequestMethod.GET)
    public String login(Model model) {
        return "home/login";
    }
    
    /*Mobile Url*/
    
    @ResponseBody
    @RequestMapping(value="/noticeList.do", method=RequestMethod.GET)
    public List<Project> list(Model model,HttpServletResponse response) {
    	response.addHeader("Access-Control-Allow-Origin", "*");
		if(mobileAuthenticationService.AuthenticationUser(userService.getCurrentUser())){
			return projectMapper.selectByNotice(userService.getCurrentUser().getId());
		}
		else{
			return null;
		}
    }
}
