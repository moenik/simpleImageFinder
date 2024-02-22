package org.exiva.simpleimagefinder.ux;

import java.awt.AWTException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.SpringLayout;

public class JGlass extends JDialog{

	private static final long serialVersionUID = -5364258704900420415L;
	
	private ImageManager imgMnger;
	
	public JGlass( ImageManager imgMngr) {
		this.imgMnger = imgMngr;
		this.setSize(200, 200);
		this.setAlwaysOnTop(true);
		this.setTitle("Glass Screen Cutter");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setupScreen();
		this.setupEvents();
		
		this.setVisible(true);
	}

	JImage img = new JImage(null);
	private void setupScreen() {
		Container c = this.getContentPane();
		SpringLayout sl = new SpringLayout();
		c.setLayout(sl);
		
		c.add(img);
		
		sl.putConstraint(SpringLayout.NORTH, img, 0, SpringLayout.NORTH, c);
		sl.putConstraint(SpringLayout.SOUTH, img, 0, SpringLayout.SOUTH, c);
		sl.putConstraint(SpringLayout.EAST, img, 0, SpringLayout.EAST, c);
		sl.putConstraint(SpringLayout.WEST, img, 0, SpringLayout.WEST, c);
	}

	private void setupEvents() {
		img.addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent e) {}
			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = JGlass.this.img.getLocationOnScreen();
				Dimension d = JGlass.this.img.getSize();
				JGlass.this.setVisible(false);
				JGlass.this.repaint();
				try {
					JGlass.this.img.updateImage(
							new Robot().createScreenCapture(new Rectangle((int)p.getX(), (int)p.getY(), (int)d.getWidth(), (int)d.getHeight()))
					);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
				JGlass.this.setVisible(true);
			}
		});
		
		this.addWindowListener(new WindowListener() {
			@Override public void windowOpened(WindowEvent e) {}			
			@Override public void windowIconified(WindowEvent e) {}			
			@Override public void windowDeiconified(WindowEvent e) {}			
			@Override public void windowDeactivated(WindowEvent e) {}			
			@Override public void windowClosing(WindowEvent e) {}			
			@Override
			public void windowClosed(WindowEvent e) {
				if(imgMnger!=null) {
					imgMnger.addImage(img.getImage());
				}
			}			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}

}
