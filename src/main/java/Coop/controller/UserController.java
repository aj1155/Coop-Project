package Coop.controller;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Coop.mapper.ImageMapper;
import Coop.mapper.UserMapper;
import Coop.model.Image;
import Coop.model.NoticeUser;
import Coop.model.User;
import Coop.service.FileService;
import Coop.service.MobileAuthenticationService;
import Coop.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserMapper userMapper;
	@Autowired UserService userService;
	@Autowired ImageMapper imageMapper;
	@Autowired FileService fileService;
	@Autowired MobileAuthenticationService mobileAuthenticationService;
	
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
	
	 @RequestMapping(value="/{id}/profile.do", method = RequestMethod.GET)
	 public String edit(@PathVariable String id,Model model) {
		
		 User user = userMapper.selectById(id);
		 model.addAttribute("user",user);
		 
		 return "layout/user/profile";
	 }
	 @RequestMapping(value="/{id}/profile.do", method = RequestMethod.POST)
	 public String edit(@PathVariable String id,User user,
		 ModelAndView model,@RequestParam("file") MultipartFile uploadedFile) throws IOException {
		 user.setImg(id);
		 fileService.writeFile(uploadedFile,"C:\\Users\\USER\\Documents\\website\\neonWork\\Coop\\src\\main\\webapp\\res\\images",id+".jpg");
		 if(uploadedFile.getSize()>0){
			 Image image = new Image();
			 image.setUserId(id);
			 image.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
			 System.out.println(image.getFileName());
			 image.setFileSize((int)uploadedFile.getSize());
			 image.setData(uploadedFile.getBytes());
			 //imageMapper.insert(image);
		 }
		 userMapper.updateUserImage(user);
		 
		 return "layout/main/home";
	 }
	 
	 
	 	/*모바일 url*/
	 	@ResponseBody
		@RequestMapping(value="/registProMobile.do", method=RequestMethod.POST)
	    public String regMobile(@RequestParam String password,@RequestParam String name,@RequestParam String email
	    		,@RequestParam String id,HttpServletResponse response) {
	 		
	 		response.addHeader("Access-Control-Allow-Origin", "*");
			if(mobileAuthenticationService.AuthenticationUser(userService.getCurrentUser())){
				User user = new User();
		 		user.setId(id);
		 		user.setEmail(email);
		 		user.setName(name);
		 		user.setPassword(userService.encryptPasswd(password));
		        String message = userService.validateBeforeInsert(user);
		        if (message == null) {
		        	userMapper.insertUser(user);
		        } else{
		        	
		        	return "message";
		        }
		        
		        return "success";
			}
			else{
				return "false";
			}
			
	 		
			
	    }
	 	
	 	@ResponseBody
	 	@RequestMapping(value="/mobileProfile.do", method = RequestMethod.POST)
		 public User edit(@RequestParam String password,@RequestParam String email,@RequestParam String id
			 ) throws IOException {
	 		
	 		
			if(mobileAuthenticationService.AuthenticationUser(userService.getCurrentUser()) && userService.getCurrentUser().getId().equals(id)){
				User user = new User();
		 		 
		 		 user.setId(id);
		 		 user.setEmail(email);
		 		 user.setPassword(userService.encryptPasswd(password));
		 		 user.setImg(id);
				 
				 
				 
				 return user;
				
			}
			else{
				return null;
			}
	 		 
		 }
	 	@ResponseBody
	 	@RequestMapping(value="/mobileProfile.do", method = RequestMethod.POST)
		 public String edit(@RequestParam String id,@RequestParam byte[] data
			 ) throws IOException {
	 		
	 		
			if(mobileAuthenticationService.AuthenticationUser(userService.getCurrentUser()) && userService.getCurrentUser().getId().equals(id)){
				 
				if(data!=null){
	                Image image = new Image();
	                image.setUserId(id);
	                image.setFileName(id);
	                image.setFileSize((int)data.length);
	                image.setData(data);
	                imageMapper.insert(image);
	                //fileService.writeFile(uploadedFile,"C:\\Users\\USER\\Documents\\website\\neonWork\\Coop\\src\\main\\webapp\\res\\images",id+".jpg");
	                User user = userMapper.selectById(id);
	                user.setImg(id);
	                userMapper.updateUserImage(user);
	             }
	            
				 
				 
				 return "success";
				
			}
			else{
				return "fail";
			}
	 		 
		 }
	 	
	 	@ResponseBody
	 	@RequestMapping(value="/mobileUserInfo.do", method = RequestMethod.GET)
		 public User info(@RequestParam String id,HttpServletResponse response) throws IOException {
	 		response.addHeader("Access-Control-Allow-Origin", "*");
			if(mobileAuthenticationService.AuthenticationUser(userService.getCurrentUser()) && userService.getCurrentUser().getId().equals(id)){
				return userMapper.selectById(id);
			}
			else{
				return null;
			}
	 		
		 }
		 
}
