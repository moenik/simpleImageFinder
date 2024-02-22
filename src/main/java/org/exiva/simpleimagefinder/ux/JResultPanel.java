package org.exiva.simpleimagefinder.ux;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.exiva.simpleimagefinder.ImageUtils;

public class JResultPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JImage image = new JImage(null);
	public JButton btnProccess =  new JButton("Process");
	private ImageManager sourceManager;
	private ImageManager findManager;
	
	public JResultPanel(ImageManager sourceManager, ImageManager findManager) {
		this.sourceManager = sourceManager;
		this.findManager = findManager;
		this.setBorder(BorderFactory.createEtchedBorder());
		
		SpringLayout sl = new SpringLayout();		
		this.setLayout(sl);
		
		this.add(btnProccess);
		this.add(image);
		
		
		sl.putConstraint(SpringLayout.NORTH, btnProccess, 00, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnProccess, 25, SpringLayout.NORTH, btnProccess);
		sl.putConstraint(SpringLayout.EAST,  btnProccess,150, SpringLayout.WEST,  btnProccess);
		sl.putConstraint(SpringLayout.WEST,  btnProccess, 00, SpringLayout.WEST,  this);

		sl.putConstraint(SpringLayout.NORTH, image, 01, SpringLayout.SOUTH, btnProccess);
		sl.putConstraint(SpringLayout.SOUTH, image, 00, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST,  image, 00, SpringLayout.EAST,  this);
		sl.putConstraint(SpringLayout.WEST,  image, 00, SpringLayout.WEST,  this);
		
		btnProccess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<Point> foundPoints = new Vector<Point>();
					BufferedImage sourceImg = JResultPanel.this.sourceManager.getImages().firstElement();
					BufferedImage resultImage = ImageUtils.copyImage(sourceImg);
					for (BufferedImage imgToFind : JResultPanel.this.findManager.getImages()) {
						Vector<Point> ps = ImageUtils.findImageInImage(sourceImg, imgToFind, null);
						for (Point p : ps) {
							resultImage = ImageUtils.drawRectangle(resultImage, p, imgToFind.getWidth(), imgToFind.getHeight(), Color.RED, 1);
							foundPoints.add(p);
						}
					}
					JResultPanel.this.updateImage(resultImage);
				} catch (Exception x) {
					x.printStackTrace();
					JOptionPane.showMessageDialog(JResultPanel.this, x.getMessage());
				}
			}
		});
		
		
	}

	public void updateImage(BufferedImage newImage) {
		this.image.updateImage(newImage);
	}

}
