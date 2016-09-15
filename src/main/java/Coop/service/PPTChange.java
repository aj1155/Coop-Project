package Coop.service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import org.springframework.stereotype.Service;

@Service
public class PPTChange {
	
	public int convert(String direct,String fileName,String name) throws IOException{
		File file=new File(fileName);
		System.out.println(fileName);
		System.out.println(direct);
		System.out.println(name);
		XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
		File dir = new File(direct);
	
		Dimension pageSize = ppt.getPageSize();
		XSLFSlide[] slide = ppt.getSlides();
		
		for(int i=0; i<slide.length; i++){
			BufferedImage image = new BufferedImage(pageSize.width,pageSize.height,BufferedImage.TYPE_INT_RGB);
			
			Graphics2D graphics = image.createGraphics();
			graphics.setPaint(Color.white);
			graphics.fill(new Rectangle2D.Float(0, 0,pageSize.width,pageSize.height));
			
			slide[i].draw(graphics);
			
			if(!dir.isDirectory()){
				dir.mkdirs();
			}
			
			FileOutputStream fos = new FileOutputStream(dir+"/"+name+i+".png");
			ImageIO.write(image, "png", fos);
			
		}
		
		return slide.length;
	}

}
