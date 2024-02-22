package org.exiva.simpleimagefinder.ux;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JImageScroller extends JScrollPane {

	private static final long serialVersionUID = 3487801140667604334L;

	
	JPanel panelList = new JPanel(new GridLayout(0, 1, 0, 3));
	Vector<JImageRowPanel> images = new Vector<JImageRowPanel>();
	
	public JImageScroller() {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setViewportView(panelList);
		
	}
	
	public Vector<BufferedImage> getImages() {
		Vector<BufferedImage> rets = new Vector<BufferedImage>();
		for (JImageRowPanel row : images) {
			if(row.getImage()!=null) {
				rets.add(row.getImage());
			}
		}
		return rets;
	}
	
	public JImageRowPanel add(BufferedImage image) {
		//validade if exists in list
		for (JImageRowPanel row : images) {
			if(row.getImage()==image) return null;
		}
				
		JImageRowPanel jirp = new JImageRowPanel(image, this);
		images.add(jirp);
		
		this.panelList.add(jirp);
		this.panelList.revalidate();
		this.panelList.repaint();
		
		return jirp;
	}

	public JImageRowPanel remove(JImageRowPanel rowPanel) {
		return this.remove(rowPanel.getImage());
	}
	
	public JImageRowPanel remove(BufferedImage image) {
		for (JImageRowPanel row : images) {
			if(row.getImage()==image) {
				images.remove(row);
				this.panelList.remove(row);
				this.panelList.revalidate();
				this.panelList.repaint();
				return row;
			}
		}
		return null;
	}
	
	
}
