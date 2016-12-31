package cn.lelight.replace.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * Copyright 2016 Lelight
 *
 * All right reserved.
 *
 * @author itlowly
 * @version 创建时间：2016年12月31日 下午5:58:22 类说明
 */
public class ImageUtils {
	
	public static void replaceImageColor(String filePath, Color srcColor, Color targetColor) throws IOException{
		File file2 = new File(filePath);
		try {
		    BufferedImage bi = ImageIO.read(file2);
		    for (int i = 0; i < bi.getWidth(); i++) {
	            for (int j = 0; j < bi.getHeight(); j++) {
	                  System.out.println(bi.getRGB(i, j));
	                  if(srcColor.getRGB() == bi.getRGB(i, j)){
	                      System.out.println(i+","+j+"  from:"+srcColor.getRGB()+"to"+targetColor.getRGB());
	                      bi.setRGB(i, j, targetColor.getRGB());
	                  }
	            }
	        }
	        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
	        ImageWriter writer = it.next();
	        File f = new File(filePath);
	        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
	        writer.setOutput(ios);
	        writer.write(bi);
	        bi.flush();
	        ios.flush();
	        ios.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}		  
    }
    
    public void createImage(int width, int height) throws IOException{
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphic = bi.createGraphics();
        graphic.setColor(new Color(0.2f,0.3f,0.4f,0.4f));
        graphic.fillRect(0, 0, width, height);
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                  //result[i][j] = bi.getRGB(i, j) & 0xFFFFFF;
                  System.out.println(bi.getRGB(i, j));
                 // bi.setRGB(i, j, 0xFFFFFF);
            }
       }
        
        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = it.next();
        File f = new File("c://test02.png");
        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
        writer.setOutput(ios);
        
        writer.write(bi);
    }
}
