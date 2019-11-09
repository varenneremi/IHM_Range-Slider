package paint;

import java.awt.AWTEvent;
import java.awt.AlphaComposite;
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

@SuppressWarnings("serial")
public class MenuView extends JPanel {

	private static final Double DIAMETRE = 200.0;

	PaintPanel panel;
	MenuModel model;
	MenuController controller;
	
	public static final int SIZEMENU = 200;
	
	Point center;
	Point p;

	public MenuView(PaintPanel panel) {
		this.panel = panel;
		model = new MenuModel(this, panel);
		controller = new MenuController(model);
		
        long eventMask = AWTEvent.MOUSE_EVENT_MASK;
        ProcessListener(eventMask);
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
						System.out.println((int)(getAngle()/(360/model.currentMenu.length)));
					}
					
				}
				
			}
		}, mask);

	
	}
	private void setListener() {
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				p = e.getPoint();
				System.out.println(getTool());
			}
		});
	}

	public void resetMenu() {
		model.resetMenu();
	}

	public int getTool() {
		int indexTool = 0;
		if (p.getX() >= center.getX()) {
			if (p.getY() >= center.getY()) {
				if (p.getX() >= p.getY())
					indexTool = 7;
				else
					indexTool = 8;
			} else {
				if (p.getX() >= p.getY())
					indexTool = 1;
				else
					indexTool = 2;
			}
		} else {
			if (p.getY() >= center.getY()) {
				if (p.getX() >= p.getY())
					indexTool = 5;
				else
					indexTool = 6;
			} else {
				if (p.getX() >= p.getY())
					indexTool = 3;
				else
					indexTool = 4;
			}
		}
		return indexTool;
	}
	
	public double distance (Point a, Point b) {
		System.out.println(Math.sqrt( Math.pow(b.getY()-a.getY(),2) + Math.pow(b.getX()-a.getX(), 2)));
		return Math.sqrt( Math.pow(b.getY()-a.getY(),2) + Math.pow(b.getX()-a.getX(), 2));
	}
	
	public int getAngle() {
		int angle = 0;
		if ((p.getX() >= center.getX()) && (p.getY() < center.getY()))
			angle = (int)Math.toDegrees(Math.acos(distance(center, new Point((int)p.getX(), (int)center.getY()))/distance(center, p)));
		else if ((p.getX() < center.getX()) && (p.getY() < center.getY()))
			angle = 90 + (int)Math.toDegrees(Math.asin(distance(center, new Point((int)p.getX(), (int)center.getY()))/distance(center, p)));
		else if ((p.getX() < center.getX()) && (p.getY() >= center.getY()))
			angle = 180 + (int)Math.toDegrees(Math.acos(distance(center, new Point((int)p.getX(), (int)center.getY()))/distance(center, p)));
		else 
			angle = 270 + (int)Math.toDegrees(Math.asin(distance(center, new Point((int)p.getX(), (int)center.getY()))/distance(center, p)));
		return angle;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

		g2.setColor(Color.WHITE); 
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setStroke(new BasicStroke(2.0f));
		g2.setPaint(Color.GRAY);
		
		int lastAngle = 0;
		int angle = 360 / model.currentMenu.length;
		for (int i = 0; i < model.currentMenu.length; i++) {
			if(i == model.currentMenu.length - 1) {
				g2.draw(new Arc2D.Double(center.x - (DIAMETRE / 2), center.y - (DIAMETRE / 2), DIAMETRE, DIAMETRE, lastAngle, 360 - lastAngle, Arc2D.PIE));
			} else {
				g2.draw(new Arc2D.Double(center.x - (DIAMETRE / 2), center.y - (DIAMETRE / 2), DIAMETRE, DIAMETRE, lastAngle, angle, Arc2D.PIE));
				lastAngle += angle;
			}
		}
        /*
		g2.draw(new Arc2D.Double(center.x-SIZEMENU/2, center.y-SIZEMENU/2, SIZEMENU, SIZEMENU, 0, 45, Arc2D.PIE));
		g2.drawString("1", center.x+SIZEMENU/4, center.y-SIZEMENU/8);
		
		g2.draw(new Arc2D.Double(center.x-SIZEMENU/2, center.y-SIZEMENU/2, SIZEMENU, SIZEMENU, 45, 45, Arc2D.PIE));
		g2.drawString("2", center.x+SIZEMENU/8, center.y-SIZEMENU/4);
		
		g2.draw(new Arc2D.Double(center.x-SIZEMENU/2, center.y-SIZEMENU/2, SIZEMENU, SIZEMENU, 90, 45, Arc2D.PIE));
		g2.drawString("3", center.x-SIZEMENU/8, center.y-SIZEMENU/4);

		g2.draw(new Arc2D.Double(center.x-SIZEMENU/2, center.y-SIZEMENU/2, SIZEMENU, SIZEMENU, 135, 45, Arc2D.PIE));
		g2.drawString("4", center.x-SIZEMENU/4, center.y-SIZEMENU/8);

		g2.draw(new Arc2D.Double(center.x-SIZEMENU/2, center.y-SIZEMENU/2, SIZEMENU, SIZEMENU, 180, 45, Arc2D.PIE));
		g2.drawString("5", center.x-SIZEMENU/4, center.y+SIZEMENU/8);

		g2.draw(new Arc2D.Double(center.x-SIZEMENU/2, center.y-SIZEMENU/2, SIZEMENU, SIZEMENU, 225, 45, Arc2D.PIE));
		g2.drawString("6", center.x-SIZEMENU/8, center.y+SIZEMENU/4);

		g2.draw(new Arc2D.Double(center.x-SIZEMENU/2, center.y-SIZEMENU/2, SIZEMENU, SIZEMENU, 270, 45, Arc2D.PIE));
		g2.drawString("7", center.x+SIZEMENU/8, center.y+SIZEMENU/4);

		g2.draw(new Arc2D.Double(center.x-SIZEMENU/2, center.y-SIZEMENU/2, SIZEMENU, SIZEMENU, 315, 45, Arc2D.PIE));
		g2.drawString("8", center.x+SIZEMENU/4, center.y+SIZEMENU/8);
        */

	}
}
