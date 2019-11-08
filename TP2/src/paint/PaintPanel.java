package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

import markingMenu.ColoredShape;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {

	MenuView menuView;
	
	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	Color color;
	boolean active;
	
	public PaintPanel() {
		this.color = Color.BLACK;
		setListener();
	}
	
	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Vector<ColoredShape> getShapes(){
		return shapes;
	}
	
	private void setListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					active = false;
					menuView.setCenter(e.getPoint());
					menuView.setVisible(true);
					System.out.println("droit");
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					active = true;
				}
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		for (ColoredShape shape : shapes) {
			g2.setColor(shape.getColor());
			g2.draw(shape.getShape());
		}
	}
}
