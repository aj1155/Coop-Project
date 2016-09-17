package Coop.controller;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.HashMap;
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

import Coop.mapper.CommentMapper;
import Coop.mapper.FileInnerMapper;
import Coop.mapper.FileMapper;
import Coop.mapper.FilePngCountMapper;
import Coop.mapper.NoticeMapper;
import Coop.mapper.NoticeUserMapper;
import Coop.mapper.PngFilesMapper;
import Coop.mapper.ProUserMapper;
import Coop.mapper.ProjectMapper;
import Coop.mapper.UserMapper;
import Coop.model.Comment;
import Coop.model.File;
import Coop.model.FileInner;
import Coop.model.FilePngCount;
import Coop.model.Notice;
import Coop.model.NoticeUser;
import Coop.model.PngFiles;
import Coop.model.Pro_User;
import Coop.model.Project;
import Coop.model.User;
import Coop.service.FileService;
import Coop.service.ImageCompareService;
import Coop.service.PDFChange;
import Coop.service.PPTChange;
import Coop.service.UserService;
@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired ProjectMapper projectMapper;
	@Autowired UserMapper userMapper;
	@Autowired FileMapper fileMapper;
	@Autowired ProUserMapper proUserMapper;
	@Autowired UserService userService;
	@Autowired CommentMapper commentMapper;
	@Autowired FileInnerMapper fileInnerMapper;
	@Autowired PDFChange pdfChange;
	@Autowired FileService fileService;
	@Autowired ImageCompareService imageCompareService;
	@Autowired PngFilesMapper pngFilesMapper;
	@Autowired FilePngCountMapper filePngCountMapper;
	@Autowired PPTChange pptChange;
	@Autowired NoticeMapper noticeMapper;
	@Autowired NoticeUserMapper noticeUserMapper;
	
	
	
	@RequestMapping(value = "/{projectId}/{userId}/create.do",method = RequestMethod.GET)
	public String create(@PathVariable String projectId,@PathVariable String userId,Model model) {
		
		
		model.addAttribute("userId",userId);
		model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(projectId)));
        return "layout/file/create";
    }
	@RequestMapping(value = "/{projectId}/{userId}/{fileId}/create2.do",method = RequestMethod.GET)
	public String create2(@PathVariable String projectId,@PathVariable String userId
			,Model model,@PathVariable String fileId) {
		
		
		model.addAttribute("userId",userId);
		model.addAttribute("fileId", fileId);
		model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(projectId)));
        return "layout/file/createIn";
    }
	@RequestMapping(value = "/{fileId}/{proId}/detail.do",method = RequestMethod.GET)
	public String detail(@PathVariable String fileId,@PathVariable String proId,Model model) {
		
		model.addAttribute("project", projectMapper.selectByProjectId(Integer.parseInt(proId)));
		model.addAttribute("file",fileMapper.selectById(Integer.parseInt(fileId)));
		model.addAttribute("commentList",commentMapper.selectByFileId(Integer.parseInt(fileId)));
		model.addAttribute("pngs",pngFilesMapper.selectByFileId(Integer.parseInt(fileId)));
        return "layout/file/detail";
    }
	@RequestMapping(value = "{noticeId}/{fileId}/{proId}/detailCheck.do",method = RequestMethod.GET)
	public String detailCheck(@PathVariable String fileId,@PathVariable String proId,
			@PathVariable String noticeId,Model model) {
		
		NoticeUser noticeUser = new NoticeUser();
		noticeUser.setMember(userService.getCurrentUser().getId());
		noticeUser.setId(Integer.parseInt(noticeId));
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("param",noticeUser);
		
		noticeUserMapper.updateConfirm(map);
		
		model.addAttribute("project", projectMapper.selectByProjectId(Integer.parseInt(proId)));
		model.addAttribute("file",fileMapper.selectById(Integer.parseInt(fileId)));
		model.addAttribute("commentList",commentMapper.selectByFileId(Integer.parseInt(fileId)));
		model.addAttribute("pngs",pngFilesMapper.selectByFileId(Integer.parseInt(fileId)));
        return "layout/file/detail";
    }
	
	@RequestMapping(value = "/{projectId}/{userId}/create.do",method = RequestMethod.POST)
	public String create(@PathVariable String projectId,@PathVariable String userId,@RequestParam("des") String des,
			@RequestParam("file") MultipartFile uploadedFile,Model model) throws IOException {
		String user = userId;
		String project = projectId;
		Notice notice = new Notice();
		Pro_User pro = new Pro_User();
		pro.setCont(3);
		pro.setProId(Integer.parseInt(projectId));
		pro.setUserId(userId);
		proUserMapper.updateCont(pro);
		File file = new File();
		if(uploadedFile.getSize()>0){
			 
			 
			 file.setUserId(user);
			 file.setProjectId(Integer.parseInt(project));
			 file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
			 file.setFileSize((int)uploadedFile.getSize());
			 file.setData(uploadedFile.getBytes());
			 file.setDes(des);
			 fileMapper.insert(file);
			 String path = "C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+file.getFileName();
			 java.io.File dir = new java.io.File(path);
			 if (!dir.exists()) { //폴더 없으면 폴더 생성
	            dir.mkdirs();
	         }
			 fileService.writeFile(uploadedFile,"C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+file.getFileName()+"/",file.getFileName());
			 FilePngCount filePngCount = new FilePngCount();
			 filePngCount.setFileId(file.getId());
			 if(file.getFileName().endsWith(".pptx")){
				 filePngCount.setPngCount(pptChange.convert("C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+file.getFileName(), "C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+file.getFileName()+"/"+file.getFileName(), file.getFileName()));
			 }
			 else{
				 filePngCount.setPngCount(pdfChange.changePDF("C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+file.getFileName()+"/"+file.getFileName()));
				 filePngCountMapper.insert(filePngCount);
			 }
			 
		}
		notice.setProjectId(Integer.parseInt(projectId));
		notice.setUserId(userService.getCurrentUser().getId());
		String noDes = userService.getCurrentUser().getName()+"님이 "+"새로운 파일을 업로드 했습니다";
		notice.setDes(noDes);
		notice.setFileId(file.getId());
		noticeMapper.insert(notice);
		
		List<User> proUser = proUserMapper.selectByProjectId(Integer.parseInt(projectId));
		
		for(int i=0;i<proUser.size();i++){
			if(!userService.getCurrentUser().getId().equals(proUser.get(i).getId())){
				NoticeUser noticeUser = new NoticeUser();
				noticeUser.setId(notice.getId());
				noticeUser.setProjectId(Integer.parseInt(projectId));
				noticeUser.setMember(proUser.get(i).getId());
				
				noticeUserMapper.insert(noticeUser);
			}
			
					
		}
		NoticeUser noticeUser=  new NoticeUser();
		noticeUser.setProjectId(Integer.parseInt(projectId));
		noticeUser.setMember(userService.getCurrentUser().getId());
		model.addAttribute("noticeList",noticeMapper.select(noticeUser));
		model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(projectId)));
		model.addAttribute("fileList", fileMapper.selectByProjectId(Integer.parseInt(projectId)));
		model.addAttribute("userList",userMapper.selectProject(Integer.parseInt(projectId)));
		model.addAttribute("pro_user",proUserMapper.selectByProjectId(Integer.parseInt(projectId)));
        return "layout/project/info";
    }
	
	@RequestMapping(value = "/{projectId}/{userId}/{fileId}/create2.do",method = RequestMethod.POST)
	public String create2(@PathVariable String projectId,@PathVariable String userId,@RequestParam("des") String des,
			@RequestParam("file") MultipartFile uploadedFile,Model model,@PathVariable String fileId) throws IOException {
		List<FileInner> fileInner = fileInnerMapper.selectByRefFileId(Integer.parseInt(fileId));
		
		Notice notice = new Notice();
		if(fileInner.size()<1){
			String user = userId;
			String project = projectId;
			String result[] = null;
			int pngCount;
			File resFile = fileMapper.selectById(Integer.parseInt(fileId));
			Pro_User pro = new Pro_User();
			pro.setCont(3);
			pro.setProId(Integer.parseInt(projectId));
			pro.setUserId(userId);
			proUserMapper.updateCont(pro);
			if(uploadedFile.getSize()>0){
				 //String path = "C:/Users/USER/FileSave/"+resFile.getFileName();
				 String path = "C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName();
				 java.io.File dir = new java.io.File(path);
		         if (!dir.exists()) { //폴더 없으면 폴더 생성
		            dir.mkdirs();
		         }
				 FileInner file = new FileInner();
				 file.setUserId(user);
				 file.setProjectId(Integer.parseInt(project));
				 file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
				 file.setFileSize((int)uploadedFile.getSize());
				 file.setData(uploadedFile.getBytes());
				 file.setDes(des);
				 file.setRefFile(Integer.parseInt(fileId));
				 fileInnerMapper.insert(file);
				 pngFilesMapper.delete(Integer.parseInt(fileId));
				 //fileService.writeFile(uploadedFile,"C:\\Users\\USER\\FileSave\\"+resFile.getFileName()+"\\",file.getFileName());
				 fileService.writeFile(uploadedFile,"C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName()+"\\",file.getFileName());
				 //pngCount = pdfChange.changePDF("C:/Users/USER/FileSave/"+resFile.getFileName()+"/"+file.getFileName());
				 if(file.getFileName().endsWith(".pptx")){
					 pngCount = pptChange.convert("C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName(), "C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName()+"/"+file.getFileName(), file.getFileName());
				 }else{
					 pngCount = pdfChange.changePDF("C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName()+"/"+file.getFileName());
				 }
				
				 
				 //imageCompareService.compare(resFile.getFileName(), file.getFileName(), pngCount, path);
				 //result = imageCompareService.compare("skh", file.getFileName(),1, path+"/");
				 //result = imageCompareService.compare(resFile.getFileName().substring(0,resFile.getFileName().indexOf('.')), file.getFileName(),2, path+"/");
				 result = imageCompareService.compare2(resFile.getFileName(),file.getFileName(),2, path+"/");
			}
			PngFiles[] png = new PngFiles[result.length];
			System.out.println(png.length);
			for(int i=0;i<png.length;i++){
				png[i] = new PngFiles();
				png[i].setFileId(resFile.getId());
				png[i].setSrc(result[i]);
				pngFilesMapper.insert(png[i]);
			}
			model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(project)));
			model.addAttribute("fileList",fileMapper.selectByProjectId(Integer.parseInt(project)));
	        return "layout/project/info";
		}
		else{
			String user = userId;
			String project = projectId;
			String result[] = null;
			int pngCount;
			File resFile = fileMapper.selectById(Integer.parseInt(fileId));
			Pro_User pro = new Pro_User();
			pro.setCont(3);
			pro.setProId(Integer.parseInt(projectId));
			pro.setUserId(userId);
			proUserMapper.updateCont(pro);
			if(uploadedFile.getSize()>0){
				 //String path = "C:/Users/USER/FileSave/"+resFile.getFileName();
				 String path = "C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName();
				 java.io.File dir = new java.io.File(path);
		         if (!dir.exists()) { //폴더 없으면 폴더 생성
		            dir.mkdirs();
		         }
				 FileInner file = new FileInner();
				 file.setUserId(user);
				 file.setProjectId(Integer.parseInt(project));
				 file.setFileName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
				 file.setFileSize((int)uploadedFile.getSize());
				 file.setData(uploadedFile.getBytes());
				 file.setDes(des);
				 file.setRefFile(Integer.parseInt(fileId));
				 fileInnerMapper.insert(file);
				 pngFilesMapper.delete(Integer.parseInt(fileId));
				 //fileService.writeFile(uploadedFile,"C:\\Users\\USER\\FileSave\\"+resFile.getFileName()+"\\",file.getFileName());
				 fileService.writeFile(uploadedFile,"C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName()+"\\",file.getFileName());
				 //pngCount = pdfChange.changePDF("C:/Users/USER/FileSave/"+resFile.getFileName()+"/"+file.getFileName());
				 if(file.getFileName().endsWith(".pptx")){
					 pngCount = pptChange.convert("C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName(), "C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName()+"/"+file.getFileName(), file.getFileName());
				 }else{
					 pngCount = pdfChange.changePDF("C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+resFile.getFileName()+"/"+file.getFileName());
				 }
				 //imageCompareService.compare(resFile.getFileName(), file.getFileName(), pngCount, path);
				 //result = imageCompareService.compare("skh", file.getFileName(),1, path+"/");
				 //result = imageCompareService.compare(fileInner.get(0).getFileName().substring(0,resFile.getFileName().indexOf('.')), file.getFileName(),2, path+"/");
				 result = imageCompareService.compare2(fileInner.get(0).getFileName(),file.getFileName(),2, path+"/");
			}
			PngFiles[] png = new PngFiles[result.length];
			System.out.println(png.length);
			for(int i=0;i<png.length;i++){
				png[i] = new PngFiles();
				png[i].setFileId(resFile.getId());
				png[i].setSrc(result[i]);
				pngFilesMapper.insert(png[i]);
			}
			
			notice.setProjectId(Integer.parseInt(projectId));
			notice.setUserId(userService.getCurrentUser().getId());
			String noDes = userService.getCurrentUser().getName()+"님이 "+"새로운 파일을 업로드 했습니다";
			notice.setDes(noDes);
			notice.setFileId(resFile.getId());
			noticeMapper.insert(notice);
			List<User> proUser = proUserMapper.selectByProjectId(Integer.parseInt(projectId));
			
			for(int i=0;i<proUser.size();i++){
				if(!userService.getCurrentUser().getId().equals(proUser.get(i).getId())){
					NoticeUser noticeUser = new NoticeUser();
					noticeUser.setId(notice.getId());
					noticeUser.setProjectId(Integer.parseInt(projectId));
					noticeUser.setMember(proUser.get(i).getId());
					
					noticeUserMapper.insert(noticeUser);
				}
				
						
			}
			NoticeUser noticeUser=  new NoticeUser();
			noticeUser.setProjectId(Integer.parseInt(projectId));
			noticeUser.setMember(userService.getCurrentUser().getId());
			model.addAttribute("noticeList",noticeMapper.select(noticeUser));
			model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(projectId)));
			model.addAttribute("fileList", fileMapper.selectByProjectId(Integer.parseInt(projectId)));
			model.addAttribute("userList",userMapper.selectProject(Integer.parseInt(projectId)));
			model.addAttribute("pro_user",proUserMapper.selectByProjectId(Integer.parseInt(projectId)));
	        return "layout/project/info";
		}
		
		
		
		
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
	@RequestMapping(value = "/{fileId}/downloadInner.do",method = RequestMethod.GET)
	public void downloadInner(@PathVariable String fileId,Model model,HttpServletResponse response) throws IOException {
		   FileInner file = fileInnerMapper.selectById(Integer.parseInt(fileId));
	       if (file == null) return;
	        String fileName = URLEncoder.encode(file.getFileName(),"UTF-8");
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
	        try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
	            output.write(file.getData());
	        }

		
        
    }
	@RequestMapping(value = "/{proId}/{fileId}/comment.do",method = RequestMethod.POST)
	public String comment(@PathVariable String proId,@PathVariable String fileId,@RequestParam String text,Model model)  {
		Comment comment = new Comment();
		Notice notice = new Notice();
		comment.setProjectId(Integer.parseInt(proId));
		comment.setFileId(Integer.parseInt(fileId));
		comment.setUserId(userService.getCurrentUser().getId());
		comment.setContent(text);
		File file = fileMapper.selectById(Integer.parseInt(fileId));
		notice.setProjectId(Integer.parseInt(proId));
		notice.setUserId(userService.getCurrentUser().getId());
		String des = userService.getCurrentUser().getName()+"님이 "+file.getFileName()+" 파일에 댓글을 남겼습니다";
		notice.setDes(des);
		notice.setFileId(file.getId());
		noticeMapper.insert(notice);
		commentMapper.insert(comment);
		
		List<User> proUser = proUserMapper.selectByProjectId(Integer.parseInt(proId));
		
		for(int i=0;i<proUser.size();i++){
			if(!userService.getCurrentUser().getId().equals(proUser.get(i).getId())){
				NoticeUser noticeUser = new NoticeUser();
				noticeUser.setId(notice.getId());
				noticeUser.setProjectId(Integer.parseInt(proId));
				noticeUser.setMember(proUser.get(i).getId());
				
				noticeUserMapper.insert(noticeUser);
			}
			
					
		}
		model.addAttribute("project", projectMapper.selectByProjectId(Integer.parseInt(proId)));
		model.addAttribute("file",fileMapper.selectById(Integer.parseInt(fileId)));
		model.addAttribute("commentList",commentMapper.selectByFileId(Integer.parseInt(fileId)));
		model.addAttribute("pngs",pngFilesMapper.selectByFileId(Integer.parseInt(fileId)));
        return "layout/file/detail";
		
        
    }
	@RequestMapping(value = "{projectId}/{fileId}/delete.do",method = RequestMethod.GET)
	public String delete(@PathVariable String fileId,Model model,@PathVariable String projectId) throws IOException {
		  File file = fileMapper.selectById(Integer.parseInt(fileId));
		  User user = userService.getCurrentUser();
		  Project project = projectMapper.selectByProjectId(Integer.parseInt(projectId));
		  if(user.getId().equals(file.getUserId())){
			  pngFilesMapper.delete(file.getId());
			  fileInnerMapper.delete(file.getId());
			  fileMapper.delete(Integer.parseInt(fileId));
			  fileService.deleteFolder("C:/Users/USER/Documents/website/neonWork/Coop/src/main/webapp/res/FileSave/"+file.getFileName());
			  	NoticeUser noticeUser=  new NoticeUser();
				noticeUser.setProjectId(Integer.parseInt(projectId));
				noticeUser.setMember(userService.getCurrentUser().getId());
				model.addAttribute("noticeList",noticeMapper.select(noticeUser));
				model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(projectId)));
				model.addAttribute("fileList", fileMapper.selectByProjectId(Integer.parseInt(projectId)));
				model.addAttribute("userList",userMapper.selectProject(Integer.parseInt(projectId)));
				model.addAttribute("pro_user",proUserMapper.selectByProjectId(Integer.parseInt(projectId)));
			  return "layout/project/info";
		  }
		  else{
			  	NoticeUser noticeUser=  new NoticeUser();
				noticeUser.setProjectId(Integer.parseInt(projectId));
				noticeUser.setMember(userService.getCurrentUser().getId());
				model.addAttribute("noticeList",noticeMapper.select(noticeUser));
				model.addAttribute("project",projectMapper.selectByProjectId(Integer.parseInt(projectId)));
				model.addAttribute("fileList", fileMapper.selectByProjectId(Integer.parseInt(projectId)));
				model.addAttribute("userList",userMapper.selectProject(Integer.parseInt(projectId)));
				model.addAttribute("pro_user",proUserMapper.selectByProjectId(Integer.parseInt(projectId)));
			  return "layout/project/info";
		  }
		
        
    }
	@RequestMapping(value = "{commentId}/{projectId}/{fileId}/commentDelete.do",method = RequestMethod.GET)
	public String commentDelete(@PathVariable String fileId,Model model,@PathVariable String projectId,
			@PathVariable String commentId) throws IOException {
		  Comment comment = commentMapper.selectById(Integer.parseInt(commentId));
		  File file = fileMapper.selectById(Integer.parseInt(fileId));
		  User user = userService.getCurrentUser();
		  Project project = projectMapper.selectByProjectId(Integer.parseInt(projectId));
		  if(user.getId().equals(comment.getUserId())){
			    commentMapper.delete(Integer.parseInt(commentId));
			    model.addAttribute("project", projectMapper.selectByProjectId(Integer.parseInt(projectId)));
				model.addAttribute("file",fileMapper.selectById(Integer.parseInt(fileId)));
				model.addAttribute("commentList",commentMapper.selectByFileId(Integer.parseInt(fileId)));
				model.addAttribute("pngs",pngFilesMapper.selectByFileId(Integer.parseInt(fileId)));
		        return "layout/file/detail";
		  }
		  else{
			    model.addAttribute("project", projectMapper.selectByProjectId(Integer.parseInt(projectId)));
				model.addAttribute("file",fileMapper.selectById(Integer.parseInt(fileId)));
				model.addAttribute("commentList",commentMapper.selectByFileId(Integer.parseInt(fileId)));
				model.addAttribute("pngs",pngFilesMapper.selectByFileId(Integer.parseInt(fileId)));
		        return "layout/file/detail";
		  }
		
        
    }
	@RequestMapping(value = "/{fileId}/history.do",method = RequestMethod.GET)
	public String history(@PathVariable String fileId,Model model) throws IOException {
		  
		model.addAttribute("fileList",fileInnerMapper.selectByRefFileId(Integer.parseInt(fileId)));
		return "webview/FileList";
        
    }
	/*모바일 url*/
	@ResponseBody
	@RequestMapping(value = "/commentList.do",method = RequestMethod.GET)
	public List<Comment> comment(@RequestParam String fileId,Model model) {
		return commentMapper.selectByFileId(Integer.parseInt(fileId));
    }
	@ResponseBody
	@RequestMapping(value = "/fileList.do",method = RequestMethod.GET)
	 public List<File> ListFile(@RequestParam String id,HttpServletResponse response) {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		return fileMapper.selectByProjectId(Integer.parseInt(id));	
			
	}
	@ResponseBody
	@RequestMapping(value = "/commentWrite.do",method = RequestMethod.POST)
	public String commentList(@RequestParam String proId,@RequestParam String fileId,@RequestParam String text,Model model)  {
		Comment comment = new Comment();
		comment.setProjectId(Integer.parseInt(proId));
		comment.setFileId(Integer.parseInt(fileId));
		comment.setUserId(userService.getCurrentUser().getId());
		comment.setContent(text);
		
		commentMapper.insert(comment);
        return "success";
		
        
    }
	@RequestMapping(value = "/show.do",method = RequestMethod.GET)
	public String pngReturn(@RequestParam String fileId,Model model) {
		
		model.addAttribute("file", fileMapper.selectById(Integer.parseInt(fileId)));
		model.addAttribute("pngs",pngFilesMapper.selectByFileId(Integer.parseInt(fileId)));
        return "webview/pngview";
    }
	@RequestMapping(value = "/history.do",method = RequestMethod.GET)
	public String historyMobile(@RequestParam String fileId,Model model) throws IOException {
		  
		model.addAttribute("fileList",fileInnerMapper.selectByRefFileId(Integer.parseInt(fileId)));
		return "webview/FileList";
        
    }
	@RequestMapping(value = "/downloadInner.do",method = RequestMethod.GET)
	public void downloadInnerMobile(@RequestParam String fileId,Model model,HttpServletResponse response) throws IOException {
		   FileInner file = fileInnerMapper.selectById(Integer.parseInt(fileId));
	       if (file == null) return;
	        String fileName = URLEncoder.encode(file.getFileName(),"UTF-8");
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
	        try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
	            output.write(file.getData());
	        }

		
        
    }
	
	@ResponseBody
	@RequestMapping(value = "/upload.do",method = RequestMethod.POST)
	public String upload(@RequestParam String projectId,@RequestParam("des") String des,
			@RequestParam("fileData") byte[] data ,@RequestParam("fileName") String fileName,
			Model model) throws IOException {
		String user = userService.getCurrentUser().getId();
		String project = projectId;
		Notice notice = new Notice();
		Pro_User pro = new Pro_User();
		pro.setCont(3);
		pro.setProId(Integer.parseInt(projectId));
		pro.setUserId(user);
		proUserMapper.updateCont(pro);
		File file = new File();
		if(data!=null){
			 
			 
			 file.setUserId(user);
			 file.setProjectId(Integer.parseInt(project));
			 file.setFileName(fileName);
			 file.setData(data);
			 file.setDes(des);
			 fileMapper.insert(file);
			 
			 
		}
		notice.setProjectId(Integer.parseInt(projectId));
		notice.setUserId(userService.getCurrentUser().getId());
		String noDes = userService.getCurrentUser().getName()+"님이 "+"새로운 파일을 업로드 했습니다";
		notice.setDes(noDes);
		notice.setFileId(file.getId());
		noticeMapper.insert(notice);
		
		List<User> proUser = proUserMapper.selectByProjectId(Integer.parseInt(projectId));
		
		for(int i=0;i<proUser.size();i++){
			if(!userService.getCurrentUser().getId().equals(proUser.get(i).getId())){
				NoticeUser noticeUser = new NoticeUser();
				noticeUser.setId(notice.getId());
				noticeUser.setProjectId(Integer.parseInt(projectId));
				noticeUser.setMember(proUser.get(i).getId());
				
				noticeUserMapper.insert(noticeUser);
			}
			
					
		}
		
		return "success";
    }
	

}
