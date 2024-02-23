package org.exiva.simpleimagefinder.main;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.exiva.simpleimagefinder.ImageUtils;
import org.exiva.simpleimagefinder.ux.JMain;

public class Main {
	public static void main(String[] args) {
		if(args.length==0) {
			System.out.println("no args -> show UI!");
			new JMain();
		}else {
			BufferedImage sourceImage = null;
			Vector<BufferedImage> images = new Vector<BufferedImage>();
			File output = null;
			boolean help = false;
			for (int i = 0; i < args.length; i++) {
				switch (args[i]) {
				case "-s":
				case "--source":
					if(++i < args.length) {
						try {
							sourceImage = ImageIO.read(new File(args[i]));
						} catch (Exception e) {
							System.out.println("Error loading file:" + args[i]);
							sourceImage = null;
						}
					}
					break;
				case "-i":
				case "--image":
					if(++i < args.length) {
						try {
							images.add(ImageIO.read(new File(args[i])));
						} catch (Exception e) {
							System.out.println("Error loading file:" + args[i]);
						}
					}
					break;
				case "-o":
				case "--output":
					if(++i < args.length) {
						try {
							output = new File(args[i]);
						} catch (Exception e) {}
					}
					break;
				case "-h":
				case "--help":
					help=true;
					break;
				default:
					break;
				}
			}
			if(help) {
				System.out.println("This program searches for exact match of images inside a source image.");
				System.out.println("It returns the top left pixel point(s) of matched image.");
				System.out.println("It does NOT have performance! It uses brute force to compare images (pixel by pixel)");
				System.out.println("Works better with PNG files because compression");
				System.out.println("Params:");
				System.out.println("-s  --source\t- path of source image to use (necessary)");
				System.out.println("-i  --image\t- path of image to find, could be more than one Ex: -i file1 -i file2 ... -i fileN ");
				System.out.println("-o  --output\t- output file if specified");
				return;
			}
			if(sourceImage==null) {
				System.out.println("Source image not found/loaded");
				return;
			}
			BufferedImage resultImage = ImageUtils.copyImage(sourceImage);
			for (BufferedImage img : images) {
				Vector<Point> ps = ImageUtils.findImageInImage(sourceImage, img, null);
				for (Point p : ps) {
					resultImage = ImageUtils.drawRectangle(resultImage, p, img.getWidth(), img.getHeight(), Color.RED, 1);
					System.out.println("X="+p.x+",Y="+p.y);
				}
			}
			if(output!=null) {
				try {
					ImageIO.write(resultImage, "PNG", output);
				} catch (Exception e) {
					System.out.println("Error saving output file");
				}
				
			}
		}
	}
}
