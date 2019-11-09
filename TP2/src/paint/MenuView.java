package paint;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
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

	Point center;
	Point p;

	public MenuView(PaintPanel panel) {
		this.panel = panel;
		model = new MenuModel(this, panel);
		controller = new MenuController(model);

		setListener();
	}

	public void setCenter(Point center) {
		this.center = center;
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
	}
}
