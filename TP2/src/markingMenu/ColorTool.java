package markingMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.event.MouseInputListener;

@SuppressWarnings("serial")
public class ColorTool extends AbstractAction implements MouseInputListener {

	private Color color;
	Color masterColor;
	
	public ColorTool(String name, Color color, Color masterColor) {
		super(name);
		this.color = color;
		this.masterColor = masterColor;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		masterColor = color;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("using tool " + this);
	}

}
