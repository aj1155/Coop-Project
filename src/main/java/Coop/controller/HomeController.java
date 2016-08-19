package Coop.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Coop.mapper.ProjectMapper;
import Coop.mapper.UserMapper;
import Coop.model.ChartData;
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
		
		@ResponseBody
		@RequestMapping(value="/chart.do", method=RequestMethod.GET)
	    public List<ChartData> chart(@RequestParam String id,HttpServletResponse response) {
			response.addHeader("Access-Control-Allow-Origin", "*");
			System.out.println(id);
			
			List<ChartData> list = projectMapper.selectCont(id);
			return list;
	        
	    }
		

	    @RequestMapping(value="/regist.do", method=RequestMethod.GET)
	    public String login(Model model) {
	    	model.addAttribute("user",null);
	        return "regist";
	    }

}
