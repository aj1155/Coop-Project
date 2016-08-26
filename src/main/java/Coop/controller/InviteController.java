package Coop.controller;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Coop.mapper.InviteMapper;
import Coop.mapper.ProUserMapper;
import Coop.mapper.ProjectMapper;
import Coop.mapper.UserMapper;
import Coop.model.Pro_User;
import Coop.model.Project;
import Coop.service.UserService;

@Controller
@RequestMapping("/invite")
public class InviteController {
	
	@Autowired ProjectMapper projectMapper;
	@Autowired UserMapper userMapper;
	@Autowired ProUserMapper proUserMapper;
	@Autowired UserService userService;
	@Autowired InviteMapper inviteMapper;
	
	@RequestMapping(value="/{projectId}/info.do",method = RequestMethod.GET)
	public String info(@PathVariable String projectId,Model model){
		
		Project project = projectMapper.selectByProjectId(Integer.parseInt(projectId));
		model.addAttribute("project", project);
		model.addAttribute("user",userMapper.selectById(project.getOwner()));
		return "invite/info";
	}
	
	@RequestMapping(value="/{projectId}/{result}/result.do",method = RequestMethod.GET)
	public String result(@PathVariable String result,@PathVariable String projectId,Model model){
		System.out.println(result);
		if(result.equals("ACCEPT")){
			String userId = userService.getCurrentUser().getId();
			Pro_User proUser = new Pro_User();
			proUser.setProId(Integer.parseInt(projectId));
			proUser.setUserId(userId);
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("param",proUser);
			inviteMapper.updateConfirm(map);
			proUserMapper.insertPro_user(proUser);
		}
		return "layout/main/home";
	}
	
	/*Mobile url*/
	@ResponseBody
	@RequestMapping(value="/mobileResult.do",method = RequestMethod.GET)
	public String mobileResult(@RequestParam("result") String result,@RequestParam("projectId") String projectId,Model model){
		System.out.println(result);
		if(result.equals("ACCEPT")){
			String userId = userService.getCurrentUser().getId();
			Pro_User proUser = new Pro_User();
			proUser.setProId(Integer.parseInt(projectId));
			proUser.setUserId(userId);
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("param",proUser);
			inviteMapper.updateConfirm(map);
			proUserMapper.insertPro_user(proUser);
		}
		return "success";
	}

}
