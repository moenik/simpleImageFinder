package org.exiva.simpleimagefinder.ux;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class JImageRowPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JImage lblImage =  new JImage(null);
	private JButton btnRemove = new JButton("🗑");
	private JImageScroller scrooler;
	
	public BufferedImage getImage() {
		return this.lblImage.getImage();
	}
	
	public JImageRowPanel(BufferedImage image, JImageScroller scrooler) {
		this.lblImage.updateImage(image);
		this.scrooler = scrooler;
		this.setMinimumSize(new Dimension(100, 70));
		this.setPreferredSize(new Dimension(100, 80));
		this.setMaximumSize(new Dimension(100, 80));
		this.setSize(this.getPreferredSize());
		
		SpringLayout sl = new SpringLayout();
		this.setLayout(sl);
		
		this.add(lblImage);
		this.add(btnRemove);
		
		sl.putConstraint(SpringLayout.NORTH, btnRemove, 00, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnRemove, 00, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST,  btnRemove, 00, SpringLayout.EAST,  this);
		sl.putConstraint(SpringLayout.WEST,  btnRemove,-45, SpringLayout.EAST,  btnRemove);
		
		sl.putConstraint(SpringLayout.NORTH, lblImage, 00, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, lblImage, 00, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST,  lblImage, 00, SpringLayout.WEST,  btnRemove);
		sl.putConstraint(SpringLayout.WEST,  lblImage, 00, SpringLayout.WEST,  this);
		
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JImageRowPanel.this.scrooler!=null) {
					JImageRowPanel.this.scrooler.remove(JImageRowPanel.this);
					JImageRowPanel.this.scrooler=null;
				}
			}
		});
	}
	
}
