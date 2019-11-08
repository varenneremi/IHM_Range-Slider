package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MenuView extends JPanel {

	PaintPanel panel;
	MenuModel model;
	MenuController controller;
	
	Point center;
	
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
		addMouseListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				System.out.println("mouve");
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("print");
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setColor(Color.BLACK);
		g2.fillOval(center.x, center.y, 100, 100);
	}
}
