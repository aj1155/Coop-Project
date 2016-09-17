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
import Coop.mapper.ICommentMapper;
import Coop.mapper.InviteMapper;
import Coop.mapper.IssueMapper;
import Coop.mapper.NoticeMapper;
import Coop.mapper.ProUserMapper;
import Coop.mapper.ProjectMapper;
import Coop.mapper.UserMapper;
import Coop.model.Active;
import Coop.model.ChartData;
import Coop.model.IComment;
import Coop.model.Invite;
import Coop.model.Issue;
import Coop.model.Notice;
import Coop.model.NoticeUser;
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
	@Autowired NoticeMapper noticeMapper;
	@Autowired IssueMapper issueMapper;
	@Autowired ICommentMapper iCommentMapper;
	
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
		NoticeUser noticeUser=  new NoticeUser();
		noticeUser.setProjectId(project.getId());
		noticeUser.setMember(userService.getCurrentUser().getId());
		model.addAttribute("noticeList",noticeMapper.select(noticeUser));
		model.addAttribute("project",projectMapper.selectByProjectId(project.getId()));
		model.addAttribute("fileList", fileMapper.selectByProjectId(project.getId()));
		model.addAttribute("userList",userMapper.selectProject(project.getId()));
		model.addAttribute("pro_user",proUserMapper.selectByProjectId(project.getId()));
		model.addAttribute("issueList", issueMapper.selectByProjectId(project.getId()));
		return "layout/project/info";
		
	}
	@RequestMapping(value = "/{id}/proInfo.do",method = RequestMethod.GET)
	 public String goInfo(@PathVariable String id,Model model) {
			Project project = projectMapper.selectByProjectId(Integer.parseInt(id));
			Pro_User pro = new Pro_User();
			NoticeUser noticeUser=  new NoticeUser();
			pro.setCont(1);
			pro.setProId(project.getId());
			pro.setUserId(userService.getCurrentUser().getId());
			proUserMapper.updateCont(pro);
			noticeUser.setProjectId(project.getId());
			noticeUser.setMember(userService.getCurrentUser().getId());
			List<User> userList = userMapper.selectProject(project.getId());
			List<User> pro_user = proUserMapper.selectByProjectId(project.getId());
			model.addAttribute("noticeList",noticeMapper.select(noticeUser));
			model.addAttribute("project",project);
			model.addAttribute("fileList", fileMapper.selectByProjectId(project.getId()));
			model.addAttribute("userList",userList);
			model.addAttribute("pro_user",pro_user);
			model.addAttribute("issueList", issueMapper.selectByProjectId(project.getId()));
			
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
	@RequestMapping(value="/{projectId}/{issueId}/issueInfo.do",method = RequestMethod.GET)
	public String issueInfo(@PathVariable String projectId,@PathVariable String issueId,Model model){
		
		model.addAttribute("issue",issueMapper.selectById(Integer.parseInt(issueId)));
		model.addAttribute("project", projectMapper.selectByProjectId(Integer.parseInt(projectId)));
		model.addAttribute("commentList",iCommentMapper.selectByIssueId(Integer.parseInt(issueId)));
		return "layout/issue/info";
	}
	@RequestMapping(value="/{projectId}/{issueId}/issueInfo.do",method = RequestMethod.POST)
	public String issueInfo(@PathVariable String projectId,@PathVariable String issueId,
			IComment icomment,Model model){
		
		icomment.setIssueId(Integer.parseInt(issueId));
		icomment.setProjectId(Integer.parseInt(projectId));
		icomment.setUserId(userService.getCurrentUser().getId());
		icomment.setUserName(userService.getCurrentUser().getName());
		
		iCommentMapper.insert(icomment);
		
		model.addAttribute("issue",issueMapper.selectById(Integer.parseInt(issueId)));
		model.addAttribute("project", projectMapper.selectByProjectId(Integer.parseInt(projectId)));
		model.addAttribute("commentList",iCommentMapper.selectByIssueId(Integer.parseInt(issueId)));
		
		return "layout/issue/info";
	}
	@RequestMapping(value="/{projectId}/issue.do",method = RequestMethod.GET)
	public String issue(@PathVariable String projectId,Model model){
		
		return "layout/project/issue";
	}
	@RequestMapping(value="/{projectId}/issue.do",method = RequestMethod.POST)
	public String issueMake(@PathVariable String projectId,Issue issue,Model model){
		issue.setProjectId(Integer.parseInt(projectId));
		issue.setUserId(userService.getCurrentUser().getId());
		issue.setUserName(userService.getCurrentUser().getName());
		
		issueMapper.insert(issue);
		
		NoticeUser noticeUser=  new NoticeUser();
		noticeUser.setProjectId(Integer.parseInt(projectId));
		noticeUser.setMember(userService.getCurrentUser().getId());
		model.addAttribute("noticeList",noticeMapper.select(noticeUser));
		model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(projectId)));
		model.addAttribute("fileList", fileMapper.selectByProjectId(Integer.parseInt(projectId)));
		model.addAttribute("userList",userMapper.selectProject(Integer.parseInt(projectId)));
		model.addAttribute("pro_user",proUserMapper.selectByProjectId(Integer.parseInt(projectId)));
		model.addAttribute("issueList", issueMapper.selectByProjectId(Integer.parseInt(projectId)));
		
		return "layout/project/info";
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
	@RequestMapping(value = "/memberList.do",method = RequestMethod.GET)
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
	@ResponseBody
	@RequestMapping(value = "/request.do",method = RequestMethod.GET)
	 public List<Notice> request(@RequestParam String id,HttpServletResponse response) {
		
		NoticeUser noticeUser=  new NoticeUser();
		noticeUser.setProjectId(Integer.parseInt(id));
		noticeUser.setMember(userService.getCurrentUser().getId());
		
		return noticeMapper.select(noticeUser);
		
			
	}
	 @RequestMapping(value = "/mobileEdit.do",method = RequestMethod.POST)
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
