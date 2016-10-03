package Coop.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class ImageCompareService {
	
	
	public String[] compare(String before,String current,int count,String path) throws IOException{
		
		
		System.out.println(before);
		System.out.println(current);
		String fileStore[] = current.split(".pdf");
		String result[] = new String[count+1];
		for(int i=0;i<=count;i++){
			
			String file1 = path+before+".pdf"+i+".png";
			String file2 = path+fileStore[0]+".pdf"+i+".png";
			System.out.println(file1);
			System.out.println(file2);
			System.out.println(path+current+"result"+i+".png");
			result[i] = current+"result"+i+".png";
			String[] cmd = new String[] {"compare",file1,file2,path+current+"result"+i+".png"};
			System.out.println(cmd);
			Process compProces = Runtime.getRuntime().exec(cmd);
		}
		
		return result;
	}
	public String[] compare2(String before,String current,int count,String path) throws IOException{
		
		
		System.out.println(before);
		System.out.println(current);
		
		String result[] = new String[count];
		for(int i=0;i<count;i++){
			
			String file1 = path+before+i+".png";
			String file2 = path+current+i+".png";
			System.out.println(file1);
			System.out.println(file2);
			System.out.println(path+current+"result"+i+".png");
			result[i] = current+"result"+i+".png";
			String[] cmd = new String[] {"compare",file1,file2,path+current+"result"+i+".png"};
			System.out.println(cmd);
			Process compProces = Runtime.getRuntime().exec(cmd);
		}
		
		return result;
	}

}