package Coop.controller;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import Coop.mapper.FileMapper;
import Coop.mapper.ProUserMapper;
import Coop.mapper.ProjectMapper;
import Coop.mapper.UserMapper;
import Coop.model.File;
import Coop.model.Pro_User;
@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired ProjectMapper projectMapper;
	@Autowired UserMapper userMapper;
	@Autowired FileMapper fileMapper;
	@Autowired ProUserMapper proUserMapper;
	
	@RequestMapping(value = "/{projectId}/{userId}/create.do",method = RequestMethod.GET)
	public String create(@PathVariable String projectId,@PathVariable String userId,Model model) {
		
		
		model.addAttribute("userId",userId);
		model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(projectId)));
        return "layout/file/create";
    }
	@ResponseBody
	@RequestMapping(value = "/fileList.do",method = RequestMethod.GET)
	 public List<File> ListFile(@RequestParam String id,HttpServletResponse response) {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		return fileMapper.selectByProjectId(Integer.parseInt(id));	
			
	}
	@RequestMapping(value = "/{projectId}/{userId}/create.do",method = RequestMethod.POST)
	public String create(@PathVariable String projectId,@PathVariable String userId,@RequestParam("des") String des,
			@RequestParam("file") MultipartFile uploadedFile,Model model) throws IOException {
		String user = userId;
		String project = projectId;
		Pro_User pro = new Pro_User();
		pro.setCont(3);
		pro.setProId(Integer.parseInt(projectId));
		pro.setUserId(userId);
		proUserMapper.updateCont(pro);
		if(uploadedFile.getSize()>0){
			 File file = new File();
			 file.setUserId(user);
			 file.setProjectId(Integer.parseInt(project));
			 file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
			 file.setFileSize((int)uploadedFile.getSize());
			 file.setData(uploadedFile.getBytes());
			 file.setDes(des);
			 fileMapper.insert(file);
		 }
		
		model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(project)));
		model.addAttribute("FileList",fileMapper.selectByProjectId(Integer.parseInt(project)));
        return "layout/project/info";
    }
	@RequestMapping(value = "/{fileId}/download.do",method = RequestMethod.GET)
	public void download(@PathVariable String fileId,Model model,HttpServletResponse response) throws IOException {
		   File file = fileMapper.selectById(Integer.parseInt(fileId));
	       if (file == null) return;
	        String fileName = URLEncoder.encode(file.getFileName(),"UTF-8");
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
	        try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
	            output.write(file.getData());
	        }

		
        
    }
	

}
