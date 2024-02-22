package org.exiva.simpleimagefinder.ux;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class JSourceImagePanel extends JPanel implements ImageManager{

	private static final long serialVersionUID = 1L;

	public JImage image = new JImage(null);
	public JButton btnPrintScreen =  new JButton("Print Screen");
	public JButton btnLoadFile =  new JButton("Open file..");
	
	public JSourceImagePanel() {
		this.setBorder(BorderFactory.createEtchedBorder());
		
		SpringLayout sl = new SpringLayout();		
		this.setLayout(sl);
		
		this.add(btnPrintScreen);
		this.add(btnLoadFile);
		this.add(image);
		
		
		sl.putConstraint(SpringLayout.NORTH, btnPrintScreen, 00, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnPrintScreen, 25, SpringLayout.NORTH, btnPrintScreen);
		sl.putConstraint(SpringLayout.EAST,  btnPrintScreen,150, SpringLayout.WEST,  btnPrintScreen);
		sl.putConstraint(SpringLayout.WEST,  btnPrintScreen, 00, SpringLayout.WEST,  this);
		
		sl.putConstraint(SpringLayout.NORTH, btnLoadFile, 00, SpringLayout.NORTH, btnPrintScreen);
		sl.putConstraint(SpringLayout.SOUTH, btnLoadFile, 00, SpringLayout.SOUTH, btnPrintScreen);
		sl.putConstraint(SpringLayout.EAST,  btnLoadFile,150, SpringLayout.WEST,  btnLoadFile);
		sl.putConstraint(SpringLayout.WEST,  btnLoadFile, 00, SpringLayout.EAST,  btnPrintScreen);

		sl.putConstraint(SpringLayout.NORTH, image, 01, SpringLayout.SOUTH, btnPrintScreen);
		sl.putConstraint(SpringLayout.SOUTH, image, 00, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST,  image, 00, SpringLayout.EAST,  this);
		sl.putConstraint(SpringLayout.WEST,  image, 00, SpringLayout.WEST,  this);
		
		
		btnPrintScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JSourceImagePanel.this.updateImage(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())));
				} catch (Exception x) {
					x.printStackTrace();
					JOptionPane.showMessageDialog(JSourceImagePanel.this, x.getMessage());
				}
			}
		});
		
		btnLoadFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				switch (f.showOpenDialog(JSourceImagePanel.this)) {
				case JFileChooser.APPROVE_OPTION:
					try {
						JSourceImagePanel.this.updateImage(ImageIO.read(f.getSelectedFile()));						
					}catch (Exception x) {
						x.printStackTrace();
						JOptionPane.showMessageDialog(JSourceImagePanel.this, x.getMessage());
					}
					break;
				default:
					break;
				}
			}
		});
	}

	public void updateImage(BufferedImage newImage) {
		this.image.updateImage(newImage);
	}

	@Override
	public Vector<BufferedImage> getImages() {
		Vector<BufferedImage> ret =  new Vector<BufferedImage>();
		ret.add(this.image.getImage());
		return ret;
	}

	@Override
	public void addImage(BufferedImage img) {
		this.updateImage(img);
	}
}
