package org.exiva.simpleimagefinder.ux;

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

public class JToFindImagePanel extends JPanel implements ImageManager{

	private static final long serialVersionUID = 1L;

	public JImageScroller imageScroller = new JImageScroller();
	public JButton btnFromScreen =  new JButton("From Screen");
	public JButton btnLoadFile =  new JButton("Open file..");
	
	public JToFindImagePanel() {
		this.setBorder(BorderFactory.createEtchedBorder());
		
		SpringLayout sl = new SpringLayout();		
		this.setLayout(sl);
		
		this.add(btnFromScreen);
		this.add(btnLoadFile);
		this.add(imageScroller);
		
		
		sl.putConstraint(SpringLayout.NORTH, btnFromScreen, 00, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnFromScreen, 25, SpringLayout.NORTH, btnFromScreen);
		sl.putConstraint(SpringLayout.EAST,  btnFromScreen,150, SpringLayout.WEST,  btnFromScreen);
		sl.putConstraint(SpringLayout.WEST,  btnFromScreen, 00, SpringLayout.WEST,  this);
		
		sl.putConstraint(SpringLayout.NORTH, btnLoadFile, 00, SpringLayout.NORTH, btnFromScreen);
		sl.putConstraint(SpringLayout.SOUTH, btnLoadFile, 00, SpringLayout.SOUTH, btnFromScreen);
		sl.putConstraint(SpringLayout.EAST,  btnLoadFile,150, SpringLayout.WEST,  btnLoadFile);
		sl.putConstraint(SpringLayout.WEST,  btnLoadFile, 00, SpringLayout.EAST,  btnFromScreen);

		sl.putConstraint(SpringLayout.NORTH, imageScroller, 01, SpringLayout.SOUTH, btnFromScreen);
		sl.putConstraint(SpringLayout.SOUTH, imageScroller, 00, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST,  imageScroller, 00, SpringLayout.EAST,  this);
		sl.putConstraint(SpringLayout.WEST,  imageScroller, 00, SpringLayout.WEST,  this);
		
		
		btnFromScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JGlass(JToFindImagePanel.this);
			}
		});
		
		btnLoadFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				switch (f.showOpenDialog(JToFindImagePanel.this)) {
				case JFileChooser.APPROVE_OPTION:
					try {
						JToFindImagePanel.this.addImageToFind(ImageIO.read(f.getSelectedFile()));						
					}catch (Exception x) {
						x.printStackTrace();
						JOptionPane.showMessageDialog(JToFindImagePanel.this, x.getMessage());
					}
					break;
				default:
					break;
				}
			}
		});
	}

	private void addImageToFind(BufferedImage img) {
		if(img==null) { return;}
		this.imageScroller.add(img);
	}

	@Override
	public Vector<BufferedImage> getImages() {
		return this.imageScroller.getImages();
	}

	@Override
	public void addImage(BufferedImage img) {
		if(img==null) {return;}
		this.addImageToFind(img);
	}

	
}
