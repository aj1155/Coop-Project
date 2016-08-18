package Coop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Coop.mapper.ProjectMapper;
import Coop.model.Pro_User;
import Coop.model.Project;
import Coop.service.UserService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import Coop.mapper.FileMapper;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired ProjectMapper projectMapper;
	@Autowired UserService userService;
	@Autowired FileMapper fileMapper;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDate() {
        return format.format(new Date());
    }

	
	@RequestMapping(value = "/{id}/create.do",method = RequestMethod.GET)
	 public String create(@PathVariable String id,Model model) {
		
		model.addAttribute("id",id);
        return "layout/project/create";
    }
	@RequestMapping(value = "/{id}/create.do",method = RequestMethod.POST)
	 public String regist(@PathVariable String id,Project project,Model model) {
		
		project.setCreate_time(getCurrentDate());
		projectMapper.insertProject(project);
		Pro_User pro_user = new Pro_User();
		pro_user.setProId(project.getId());
		pro_user.setUserId(id);
		projectMapper.insertPro_user(pro_user);
		
	   model.addAttribute("ProjectList",projectMapper.selectById(userService.getCurrentUser()));
       return "layout/main/home";
	}
	@ResponseBody
	@RequestMapping(value = "/{id}/proList.do",method = RequestMethod.GET)
	 public List<Project> ListDo(@PathVariable String id,HttpServletResponse response) {
			System.out.println(id);
	        response.addHeader("Access-Control-Allow-Origin", "*");
			return projectMapper.selectById2(id);
			
	}
	@ResponseBody
	@RequestMapping(value = "/proList.do",method = RequestMethod.POST)
	 public List<Project> ListProject(@RequestParam String id,HttpServletResponse response) {
		if(userService.getCurrentUser().getId().equals(id))
		{
			response.addHeader("Access-Control-Allow-Origin", "*");
			return projectMapper.selectById2(id);
		}
		else{
			return null;
		}
		
			
	}
	@RequestMapping(value = "/{id}/proInfo.do",method = RequestMethod.GET)
	 public String goInfo(@PathVariable String id,Model model) {
			System.out.println(id);
			Project project = projectMapper.selectByProjectId(Integer.parseInt(id));
			model.addAttribute("project",project);
			model.addAttribute("fileList", fileMapper.selectByProjectId(project.getId()));
			return "layout/project/info";
	}
	@RequestMapping(value="/search.do",method = RequestMethod.POST)
	public String search(@RequestParam("search") String search,Model model){
		System.out.println(search);
		model.addAttribute("ProjectList",projectMapper.selectBySearch(search));
		model.addAttribute("user",userService.getCurrentUser());
		return "layout/main/home";
	}
}
