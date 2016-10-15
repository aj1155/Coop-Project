package Coop.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Coop.mapper.InviteMapper;
import Coop.mapper.ProjectMapper;
import Coop.mapper.TextDiffMapper;
import Coop.model.Active;
import Coop.model.Project;
import Coop.service.FileService;
import Coop.service.FileToText;
import Coop.service.MobileAuthenticationService;
import Coop.service.UserService;

@Controller
@RequestMapping("/layout")
public class LayoutController {
	
	@Autowired ProjectMapper projectMapper;
	@Autowired UserService userService;
	@Autowired InviteMapper inviteMapper;
	@Autowired MobileAuthenticationService mobileAuthenticationService;
	@Autowired FileToText fileToText;
	@Autowired TextDiffMapper textDiffMapper;
	@Autowired FileService fileService;
	
	@RequestMapping(value = "/text.do" ,method=RequestMethod.GET)
    public String textDiff(Model model) {
		
		return "webview/textDiff";
    }
	@RequestMapping(value="/text.do", method=RequestMethod.POST)
    public String textDiff(Model model,
            @RequestParam("file") File[] files) throws IOException {
		String fPath = files[0].getCanonicalPath().replace("\\", "/");
		String sPath = files[1].getCanonicalPath().replace("\\", "/");
		System.out.println(fPath);
		System.out.println(sPath);


		String[] result = fileToText.compareText("C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/1.docx/1.docx","C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/1.docx/2.docx");
		String[] show = fileService.compareText(result[0], result[1]).split("\n");
		
		model.addAttribute("showDiff", show);
		return "webview/textDiff";
		
		
       
    }

	
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
