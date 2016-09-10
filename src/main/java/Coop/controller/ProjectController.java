package Coop.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Coop.mapper.FileMapper;
import Coop.mapper.InviteMapper;
import Coop.mapper.ProUserMapper;
import Coop.mapper.ProjectMapper;
import Coop.mapper.UserMapper;
import Coop.model.Active;
import Coop.model.ChartData;
import Coop.model.Invite;
import Coop.model.Pro_User;
import Coop.model.Project;
import Coop.model.User;
import Coop.service.UserService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired ProjectMapper projectMapper;
	@Autowired UserService userService;
	@Autowired FileMapper fileMapper;
	@Autowired ProUserMapper proUserMapper;
	@Autowired UserMapper userMapper;
	@Autowired InviteMapper inviteMapper;

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
		proUserMapper.insertPro_user(pro_user);
		
	   model.addAttribute("ProjectList",projectMapper.selectById(userService.getCurrentUser()));
       return "layout/main/home";
	}
	@RequestMapping(value = "/edit.do",method = RequestMethod.POST)
	 public String edit(Project project,Model model) {
		projectMapper.update(project);
		model.addAttribute("project",projectMapper.selectByProjectId(project.getId()));
		model.addAttribute("fileList", fileMapper.selectByProjectId(project.getId()));
		model.addAttribute("userList",userMapper.selectAll());
		model.addAttribute("pro_user",proUserMapper.selectByProjectId(project.getId()));
		return "layout/project/info";
		
	}
	@RequestMapping(value = "/{id}/proInfo.do",method = RequestMethod.GET)
	 public String goInfo(@PathVariable String id,Model model) {
			Project project = projectMapper.selectByProjectId(Integer.parseInt(id));
			model.addAttribute("project",project);
			model.addAttribute("fileList", fileMapper.selectByProjectId(project.getId()));
			Pro_User pro = new Pro_User();
			pro.setCont(1);
			pro.setProId(project.getId());
			pro.setUserId(userService.getCurrentUser().getId());
			proUserMapper.updateCont(pro);
			model.addAttribute("userList",userMapper.selectAll());
			model.addAttribute("pro_user",proUserMapper.selectByProjectId(project.getId()));
			return "layout/project/info";
	}
	@RequestMapping(value="/search.do",method = RequestMethod.POST)
	public String search(@RequestParam("search") String search,Model model){
		model.addAttribute("ProjectList",projectMapper.selectBySearch(search));
		model.addAttribute("user",userService.getCurrentUser());
		Active act = new Active();
		act.setAct("active");
		model.addAttribute("act",act);
		return "layout/main/home";
	}
	@RequestMapping(value="/{userId}/{projectId}/invite.do",method = RequestMethod.GET)
	public String invite(@PathVariable String userId,@PathVariable String projectId,Model model){
		System.out.println("inv");
		Invite invite = new Invite();
		invite.setProjectId(Integer.parseInt(projectId));
		invite.setSender(userService.getCurrentUser().getId());
		invite.setRecipient(userId);
		inviteMapper.insert(invite);
		model.addAttribute("pro_user",proUserMapper.selectByProjectId(Integer.parseInt(projectId)));
		return "redirect:" + "/project/"+projectId+"/proInfo.do";
	}
	
	@ResponseBody
	@RequestMapping(value="/chart.do", method=RequestMethod.GET)
    public List<ChartData> chart(@RequestParam String id,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<ChartData> list = proUserMapper.selectCont(Integer.parseInt(id));
		return list;
        
    }
	

	/*모바일 url*/
	@ResponseBody
	@RequestMapping(value = "/{id}/proList.do",method = RequestMethod.GET)
	 public List<Project> ListDo(@PathVariable String id,HttpServletResponse response) {
			System.out.println(id);
	        response.addHeader("Access-Control-Allow-Origin", "*");
			return projectMapper.selectById2(id);
			
	}
	@ResponseBody
	@RequestMapping(value = "/{id}/proList.do",method = RequestMethod.GET)
	 public List<User> member(@PathVariable String id,HttpServletResponse response) {
			
			return proUserMapper.selectByProjectId(Integer.parseInt(id));
			
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
	 @RequestMapping(value = "/edit.do",method = RequestMethod.POST)
	 public String editMobile(@RequestParam String id,@RequestParam String owner,@RequestParam String des ,
			 Model model,@RequestParam String name) {
		Project project = new Project();
		project.setId(Integer.parseInt(id));
		project.setDes(des);
		project.setName(name);
		project.setOwner(owner);
		if(project.getName()==null || project.getName().isEmpty()){
			return "check Project Name";
		}
		if(project.getOwner()==null || project.getOwner().isEmpty()){
			return "check Owner Name";
		}
		
		return "success";
		
	}
}
