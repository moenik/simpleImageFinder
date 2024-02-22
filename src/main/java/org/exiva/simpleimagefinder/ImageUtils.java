package org.exiva.simpleimagefinder;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class ImageUtils {
	/**
	 * Search occurrences of image in sourceImage, ignoring Colors in image like greenScreen or other colors.<br/>
	 * PERFORMANCE WARNING! It searches pixel by pixel. 
	 * @param sourceImage - source image  
	 * @param image - image to look for
	 * @param ignoredColors - vector of pixel colors in image to be ignored in the process
	 * @return vector of top left corner of Points of images found in sourceImage. 
	 */
	public static Vector<Point> findImageInImage(BufferedImage sourceImage, BufferedImage image, Vector<Color> ignoredColors) {
		
		Vector<Point> points = new Vector<Point>();
		ignoredColors = ignoredColors==null?new Vector<Color>():ignoredColors;
		if( image.getHeight()>sourceImage.getHeight() 
		 || image.getWidth() >sourceImage.getWidth() ) {
			 return points; //case image bigger than source, ignore
		 }
		
		int ignoredRGBs[] = new int[ignoredColors.size()];
		for (int i = 0; i < ignoredRGBs.length; i++) {
			ignoredRGBs[i] = ignoredColors.get(i).getRGB();
		}
		
		for (int sourceX = 0; sourceX < sourceImage.getWidth()-image.getWidth(); sourceX++) {
			for (int sourceY = 0; sourceY < sourceImage.getHeight()-image.getHeight(); sourceY++) {
				
				boolean match = true;
				for (int imageX = 0; imageX < image.getWidth() && match; imageX++) {
					for (int imageY = 0; imageY < image.getHeight() && match; imageY++) {
						boolean ignorePixel = false;
						int imageRGB = image.getRGB(imageX, imageY);
						for (int i = 0; i < ignoredRGBs.length && !ignorePixel; i++) {
							if(ignoredRGBs[i]==imageRGB) {
								ignorePixel=true;
							}
						}
						
						if(!ignorePixel && sourceImage.getRGB(sourceX+imageX, sourceY+imageY)!=imageRGB) {
							match=false;
						}
					}
				}
				
				if(match) {
					points.add(new Point(sourceX, sourceY));
				}				
			}
		}
		
		
		return points;
	}
	
	public static BufferedImage copyImage(BufferedImage input) {
		BufferedImage ret = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
		int[] rgb = input.getRGB(0, 0, input.getWidth(), input.getHeight(), null, 0, input.getWidth());
		ret.setRGB(0, 0, ret.getWidth(), ret.getHeight(), rgb, 0, input.getWidth());
		return ret;
	}

	
	public static BufferedImage drawLine(BufferedImage srcImg, Point startPoint, Point endPoint, Color color, int thickness) {
		thickness = thickness<=0?1:thickness;
		color = color==null?Color.RED:color;
		int clr = color.getRGB();
		//math line: y=mx+c
		//      and: x=(y-c)/m
		// so m = deltaY/deltaX
		double deltaY = (endPoint.y - startPoint.y);
		deltaY = deltaY==0?1:deltaY;
		double deltaX = (endPoint.x - startPoint.x);
		deltaX = deltaX==0?1:deltaX;
		double m = deltaY/deltaX;
		// so c = y - mx
		double c = startPoint.y - (m*startPoint.x);
		
		for (int i = startPoint.x; i < endPoint.x; i++) {
			int x = i;
			int y = (int)((m*i)+c);
			try {
				srcImg.setRGB(x, y, clr);
			}catch (Exception e) {}
		}
		for (int i = startPoint.y; i < endPoint.y; i++) {
			int y = i;
			int x = (int)((y-c)/m);
			try {
				srcImg.setRGB(x, y, clr);
			}catch (Exception e) {}
		}
		
		return srcImg;
	}
	
	/**
	 * Copy the source image and Draw a rectangle(only border line) in the new instance. 
	 * 
	 * @param srcImg - source image 
	 * @param topLeftCorner - top left corner of the rectangle
	 * @param height - of the rectangle
	 * @param width - of the rectangle
	 * @param color - color of the rectangle
	 * @param thickness - of the border min 1 pixel
	 * @return new instance with draw rectangle.
	 */
	public static BufferedImage drawRectangle(BufferedImage srcImg, Point topLeftCorner, int height, int width, Color color, int thickness ) {
		//BufferedImage copy = copyImage(srcImg);
		thickness = thickness<=0?1:thickness;
		color = color==null?Color.RED:color;
		
		//other 3 cornerPoints
		Point bttLeft = new Point(topLeftCorner.x, topLeftCorner.y+width);
		Point topRight  = new Point(topLeftCorner.x+height, topLeftCorner.y);
		Point bttRight = new Point(topLeftCorner.x+height, topLeftCorner.y+width);
		
		//do vertical lines;
		srcImg = drawLine(srcImg, topLeftCorner, bttLeft, color, thickness);
		srcImg = drawLine(srcImg, topRight, bttRight, color, thickness);
		//do horizontal lines;
		srcImg = drawLine(srcImg, topLeftCorner, topRight, color, thickness);
		srcImg = drawLine(srcImg, bttLeft, bttRight, color, thickness);
		
		return srcImg;
	}
}
