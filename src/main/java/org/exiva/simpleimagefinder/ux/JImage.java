package org.exiva.simpleimagefinder.ux;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JImage extends JLabel{	

	private static final long serialVersionUID = -4871904090064105927L;

	public JImage(BufferedImage bi) {
		updateImage(bi);
	}
	
	private BufferedImage image;
	
	public void updateImage(BufferedImage bi) {
		this.image = bi;
		if(this.image==null) {
			this.setIcon(new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)));
		}else {
			this.setIcon(new ImageIcon(this.image));
		}
		this.repaint();
	}

	public BufferedImage getImage() {
		return this.image;
	}
}
