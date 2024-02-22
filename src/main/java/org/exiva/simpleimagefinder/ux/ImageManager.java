package org.exiva.simpleimagefinder.ux;

import java.awt.image.BufferedImage;
import java.util.Vector;

public interface ImageManager {
	public Vector<BufferedImage> getImages();
	public void addImage(BufferedImage img);
}
