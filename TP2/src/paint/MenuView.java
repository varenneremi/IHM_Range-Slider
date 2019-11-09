package paint;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

public class MenuView extends JPanel {

	PaintPanel panel;
	MenuModel model;
	MenuController controller;
	
	Point center;
	Point p;
	
	public MenuView(PaintPanel panel) {
		this.panel = panel;
		model = new MenuModel(this, panel);
		controller = new MenuController(model);
		
        long eventMask = AWTEvent.MOUSE_EVENT_MASK;
        System.out.println(eventMask);
        	ProcessListener(eventMask);
		setListener();
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	private void ProcessListener(Long mask) {
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			public void eventDispatched(AWTEvent e) {
				if (e.getID() == MouseEvent.MOUSE_RELEASED)  {
					if (!panel.active) {
						p = new Point(Integer.parseInt(e.paramString().substring(16, 19)), Integer.parseInt(e.paramString().substring(20, 23)));
						System.out.println(center);
						System.out.println(p);
						System.out.println(getTool());
					}
					
				}
				
			}
		}, mask);

	
	}
	private void setListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//System.out.println(e.getID());
				//System.out.println(MouseEvent.MOUSE_PRESSED);
			}
			public void mouseClicked(MouseEvent e) {
				System.out.println("test");
			}
		});
	}
	
	
	
	public int getTool() {
		int indexTool = 0;
		if (p.getX() >= center.getX()) {
			if (p.getY() >= center.getY()) {
				if (p.getX() >= p.getY()) 
					indexTool = 7;
				else 
					indexTool = 8;
			}
			else {
				if (p.getX() >= p.getY()) 
					indexTool = 1;
				else 
					indexTool = 2;
			}
		}
		else {
			if (p.getY() >= center.getY()) {
				if (p.getX() >= p.getY()) 
					indexTool = 5;
				else 
					indexTool = 6;
			}
			else {
				if (p.getX() >= p.getY()) 
					indexTool = 3;
				else 
					indexTool = 4;
			}
		}
		return indexTool;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("print");
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/*
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setColor(Color.BLACK);
		g2.fillOval(center.x, center.y, 100, 100);
		*/
		
		g2.setStroke(new BasicStroke(2.0f));
		g2.setPaint(Color.GRAY);
		g2.draw(new Arc2D.Double(center.x-100, center.y-100, 200, 200, 0, 45, Arc2D.PIE));
		//g2.drawString("test", center.x-50, center.y-50);;
		g2.draw(new Arc2D.Double(center.x-100, center.y-100, 200, 200, 45, 45, Arc2D.PIE));
		g2.draw(new Arc2D.Double(center.x-100, center.y-100, 200, 200, 90, 45, Arc2D.PIE));
		g2.draw(new Arc2D.Double(center.x-100, center.y-100, 200, 200, 135, 45, Arc2D.PIE));
		g2.draw(new Arc2D.Double(center.x-100, center.y-100, 200, 200, 180, 45, Arc2D.PIE));
		g2.draw(new Arc2D.Double(center.x-100, center.y-100, 200, 200, 225, 45, Arc2D.PIE));
		g2.draw(new Arc2D.Double(center.x-100, center.y-100, 200, 200, 270, 45, Arc2D.PIE));
		g2.draw(new Arc2D.Double(center.x-100, center.y-100, 200, 200, 315, 45, Arc2D.PIE));

	}
}
