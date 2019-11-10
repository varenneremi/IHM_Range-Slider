package markingMenu;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import paint.PaintPanel;

public class ShapeTool extends Tool implements MouseInputListener {
	
	public static ShapeTool tool;
	
	private Point origin;
	private Shape shape;
	PaintPanel panel;

	public ShapeTool(String name, PaintPanel panel) {
		super(name);
		this.panel = panel;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			setOrigin(e.getPoint());
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			setShape(null);
		}
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	@Override
	public void exec() {
		panel.removeMouseListener(tool);
		panel.removeMouseMotionListener(tool);
		tool = this;
		panel.addMouseListener(tool);
		panel.addMouseMotionListener(tool);
	}
}
