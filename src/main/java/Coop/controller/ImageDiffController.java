package Coop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ImageDiff")
public class ImageDiffController implements Runnable {
	
	
	
	
	@RequestMapping(value = "/pdf.do",method = RequestMethod.GET)
	public void diff() throws IOException{
		
		String[] cmd2 = new String[] {"convert","C:/ImageMagick/skhuhelper명세서.pdf","C:/Users/USER/kimjava/TestImage/temp.png"};
		Process process2 = Runtime.getRuntime().exec(cmd2);
		System.out.println("출입");
		Thread t = new Thread(new ImageDiffController());
		t.start();
	}
	
	
	public void run(){
		try {
			System.out.println("왜 안돼 시발");
			//String[] cmd = new String[] {"compare","C:/ImageMagick/temp0.png","C:/ImageMagick/temp1.png","C:/Users/USER/kimjava/TestImage/src/tempcompareImage.png"};
			//Process process = Runtime.getRuntime().exec(cmd);
			
			
			
		}catch(Exception e) {
        	System.out.println(e);
        }
		
	}

}
