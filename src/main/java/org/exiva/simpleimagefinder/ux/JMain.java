package org.exiva.simpleimagefinder.ux;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JMain extends JFrame{

	private static final long serialVersionUID = 1L;

	JTabbedPane tabs = new JTabbedPane();
	JSourceImagePanel srcImagePanel = new JSourceImagePanel();
	JToFindImagePanel toFindImagePanel = new JToFindImagePanel(); 
	JResultPanel resultPanel = new JResultPanel(srcImagePanel, toFindImagePanel); 
	
	public JMain() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setTitle("Simple Image Finder");
		
		this.setupWindow();
		this.setupEvents();
		this.setVisible(true);
	}

	private void setupWindow() {
		
		this.tabs.addTab("Source Image", srcImagePanel);
		this.tabs.addTab("Images to Find", toFindImagePanel);
		this.tabs.addTab("Result", resultPanel);
		
		this.getContentPane().add(this.tabs);	
	}
	
	private void setupEvents() {
		// TODO Auto-generated method stub
		
	}

	
}
