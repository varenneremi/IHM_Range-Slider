package markingMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Vector;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {

	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	Color color;

	public PaintPanel() {
		this.color = Color.BLACK;
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		for (ColoredShape shape : shapes) {
			g2.setColor(shape.color);
			g2.draw(shape.shape);
		}
	}
}
