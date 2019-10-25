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
import markingMenu.Menu;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {

	Paint parentFrame;
	
	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	Color color;
	
	boolean rightClicked = false;
	Menu[] menus;

	public PaintPanel(Paint parentFrame) {
		this.parentFrame = parentFrame;
		
		this.color = Color.BLACK;
		setListener();
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
					rightClicked = true;
					menus = parentFrame.getInitialMenu();
					System.out.println("droit");
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					rightClicked = false;
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
