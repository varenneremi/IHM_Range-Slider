package markingMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import paint.PaintPanel;

@SuppressWarnings("serial")
public class ColorTool extends AbstractAction implements Tool {

	private Color color;
	PaintPanel panel;
	
	public ColorTool(String name, Color color, PaintPanel panel) {
		super(name);
		this.color = color;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("using tool " + this);
		panel.setColor(color);
	}

}
