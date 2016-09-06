package Coop.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.imageio.ImageIO;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import org.springframework.stereotype.Service;

@Service
public class PDFChange {
	
	public int changePDF(String change) throws IOException{
		//파일을 받은 저장 경로,파일 둘다 가능
        File file = new File(change);
        System.out.println(file.getName());
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdffile = new PDFFile(buf);
   

        // pdf파일 장수를 가져옴
        int pageNum = pdffile.getNumPages();
        //String fileStore[] = change.split(".pdf");
        
        
       
        
        //이미지 변환 반복문
        for(int i=0;i<=pageNum;i++){
        	//pdf파일 변환시 이미지 크기나 사이즈 조정
        	 PDFPage page = pdffile.getPage(i);
        	 /*Rectangle rect = new Rectangle(0,0,
             (int)page.getBBox().getWidth(),
             (int)page.getBBox().getHeight());*/
        	 Rectangle rect = new Rectangle(0,0,
                     595,
                     842);
        	 
        	  Image image = page.getImage(
        			  rect.width, rect.height, //width & height
        			  rect, // 각 고정
        			  null, // ImageObserver툴은 일단 중지
        			  true, // 배경색 설정 일단 흰색으로 고정
        			  true  // 변경 전 까지는 블락킹
              );
        
		        int w = image.getWidth(null);
		        int h = image.getHeight(null);
		        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		        Graphics2D g2 = bi.createGraphics();
		        g2.drawImage(image, 0, 0, null);
		        g2.dispose();
		        
		        
		        try
		        {
		            ImageIO.write(bi, "png", new File(change+i+".png"));
		        }
		        catch(IOException ioe)
		        {
		            System.out.println("write: " + ioe.getMessage());
		        }                
        }
	
	
        return pageNum;
	}
}
