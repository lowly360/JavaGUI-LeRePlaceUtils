package cn.lelight.replace.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
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

	public static void replaceImageColor(String filePath, Color srcColor, Color targetColor) throws IOException {
		System.out.println("from:" + Integer.toHexString(srcColor.getRGB()) + " to:" + targetColor.getRGB());

		File file2 = new File(filePath);
		try {
			// BufferedImage buffImg = new BufferedImage(w, h,
			// BufferedImage.TYPE_INT_ARGB);
			BufferedImage bi = ImageIO.read(file2);

			int minx = bi.getMinX();
			int miny = bi.getMinY();

			int[] srcRgb = new int[3];
			int[] rgb = new int[3];

			srcRgb[0] = (srcColor.getRGB() & 0xff0000) >> 16;
			srcRgb[1] = (srcColor.getRGB() & 0xff00) >> 8;
			srcRgb[2] = (srcColor.getRGB() & 0xff);

			int rongCha = 50;

			for (int i = minx; i < bi.getWidth(); i++) {
				for (int j = miny; j < bi.getHeight(); j++) {
					int pixel = bi.getRGB(i, j);
					/**
					 * 分别进行位操作得到 r g b上的值
					 */
					rgb[0] = (pixel & 0xff0000) >> 16;
					rgb[1] = (pixel & 0xff00) >> 8;
					rgb[2] = (pixel & 0xff);

					if (Math.abs(srcRgb[0] - rgb[0]) < rongCha && Math.abs(srcRgb[1] - rgb[1]) < rongCha
							&& Math.abs(srcRgb[2] - rgb[2]) < rongCha) {
//						System.out.println(i + "," + j + " from:" + Integer.toHexString(srcColor.getRGB()) + "  to"
//								+ Integer.toHexString(targetColor.getRGB()));
						bi.setRGB(i, j, targetColor.getRGB());
					}
				}
			}
			File f = new File(filePath);
			FileOutputStream ops = new FileOutputStream(f);

			ImageIO.write(bi, "png", ops);
			bi.flush();
			ops.flush();
			ops.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void isAlmostRGB(int srcColor, int drcColor) {
		if (srcColor == drcColor) {

		}
	}

	// public void createImage(int width, int height) throws IOException{
	// BufferedImage bi = new BufferedImage(width, height,
	// BufferedImage.TYPE_4BYTE_ABGR);
	// Graphics2D graphic = bi.createGraphics();
	// graphic.setColor(new Color(0.2f,0.3f,0.4f,0.4f));
	// graphic.fillRect(0, 0, width, height);
	//
	// for (int i = 0; i < width; i++) {
	// for (int j = 0; j < height; j++) {
	// //result[i][j] = bi.getRGB(i, j) & 0xFFFFFF;
	// System.out.println(bi.getRGB(i, j));
	// // bi.setRGB(i, j, 0xFFFFFF);
	// }
	// }
	//
	// Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
	// ImageWriter writer = it.next();
	// File f = new File("c://test02.png");
	// ImageOutputStream ios = ImageIO.createImageOutputStream(f);
	// writer.setOutput(ios);
	//
	// writer.write(bi);
	// }
}
