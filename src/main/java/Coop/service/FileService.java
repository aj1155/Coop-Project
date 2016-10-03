package Coop.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	private FileOutputStream fos;
    
    public void writeFile(MultipartFile file, String path, String fileName){
         
        try{
         
            byte fileData[] = file.getBytes();
             
            fos = new FileOutputStream(path + "\\" + fileName);
             
            fos.write(fileData);
         
        }catch(Exception e){
             
            e.printStackTrace();
             
        }finally{
             
            if(fos != null){
                 
                try{
                    fos.close();
                }catch(Exception e){}
                 
                }
        }// try end;
         
    }// wirteFile() end;
    
    public void deleteFolder(String parentPath) {
    	
        File file = new File(parentPath);
        String[] fnameList = file.list();
        int fCnt = fnameList.length;
        String childPath = "";
        
        for(int i = 0; i < fCnt; i++) {
          childPath = parentPath+"/"+fnameList[i];
          File f = new File(childPath);
          if( ! f.isDirectory()) {
            f.delete();   //파일이면 바로 삭제
          }
          else {
            deleteFolder(childPath);
          }
        }
        
        File f = new File(parentPath);
        f.delete();   //폴더는 맨 나중에 삭제
        
      }
    
    public String compareText(String a1,String a2){
		StringBuilder s3 = new StringBuilder("");
		
		String[] s1 = a1.split("\n");
		String[] s2 = a2.split("\n");
		for(int i=0;i<s2.length;i++){
			if(s2[i].startsWith("$cha")){
				if(s2.length>=i){
					s3.append("$add ");
					s3.append(s2[i].replace("$cha", ""));
					s3.append("\n");
				}
				else{
					s3.append("$del ");
					s3.append(s1[i]);
					s3.append("\n");
					s3.append("$add ");
					s3.append(s2[i].replace("$cha", ""));
					s3.append("\n");
				}
				
			}
			else{
				s3.append(s2[i]);
				s3.append("\n");
			}
		}
		
		return s3.toString();
    }

}
