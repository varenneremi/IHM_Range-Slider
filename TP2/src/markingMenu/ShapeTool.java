package markingMenu;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.event.MouseInputListener;

@SuppressWarnings("serial")
public class ShapeTool extends AbstractAction implements MouseInputListener {
	
	public static ShapeTool tool;
	
	Point o;
	Shape shape;
	PaintPanel panel;

	public ShapeTool(String name, PaintPanel panel) {
		super(name);
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("using tool " + this);
		panel.removeMouseListener(tool);
		panel.removeMouseMotionListener(tool);
		tool = this;
		panel.addMouseListener(tool);
		panel.addMouseMotionListener(tool);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		o = e.getPoint();
	}

	public void mouseReleased(MouseEvent e) {
		shape = null;
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}
}
