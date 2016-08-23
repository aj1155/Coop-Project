package Coop.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Coop.mapper.InviteMapper;
import Coop.mapper.ProjectMapper;
import Coop.mapper.UserMapper;
import Coop.model.ChartData;
import Coop.model.Invite;
import Coop.model.User;
import Coop.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
		@Autowired UserMapper userMapper;
		@Autowired ProjectMapper projectMapper;
		@Autowired UserService userService;
		@Autowired InviteMapper inviteMapper;
		
		@RequestMapping(value="/index.do", method=RequestMethod.GET)
	    public String index(Model model) {
			System.out.println("조인");
	        return "home";
	    }
		@ResponseBody
		@RequestMapping(value="/chart.do", method=RequestMethod.GET)
	    public List<ChartData> chart(@RequestParam String id,HttpServletResponse response) {
			response.addHeader("Access-Control-Allow-Origin", "*");
			List<ChartData> list = projectMapper.selectCont(id);
			return list;
	        
	    }
		

	    @RequestMapping(value="/regist.do", method=RequestMethod.GET)
	    public String login(Model model) {
	    	model.addAttribute("user",null);
	        return "regist";
	    }
	    
	    @RequestMapping(value="/regist.do", method = RequestMethod.POST)
		 public String regist(User user, Model model) {
				System.out.println("register");
				System.out.println(user);
			 	user.setPassword(userService.encryptPasswd(user.getPassword()));
		        String message = userService.validateBeforeInsert(user);
		        if (message == null) {
		        	userMapper.insertUser(user);
		        } else{
		        	model.addAttribute("user",user);
		        	model.addAttribute("msg", message);
		        	return "regist";
		        }
		            
		      
		        return "home";
		    }
	    
	    
	    
	    /*모바일 url*/
	    @ResponseBody
		@RequestMapping(value="/loginProMobile.do", method=RequestMethod.POST)
	    public String logMobile(@RequestBody String id,@RequestBody String password,Model model) {
			
			if(userMapper.loginProcess(id,userService.encryptPasswd(password))!=null){
				return "success";
			}
			else{
				return "fail";
			}
			
	    }
	    @ResponseBody
		@RequestMapping(value="/requestList.do", method=RequestMethod.GET)
	    public List<Invite> requestList(@RequestParam String id) {
			
			
	    	return inviteMapper.selectByRecipient(id);
			
	    }
		

}
