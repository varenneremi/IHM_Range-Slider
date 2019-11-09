package markingMenu;

import java.awt.Color;

import paint.PaintPanel;

public class ColorTool extends Tool {

	private Color color;
	PaintPanel panel;
	
	public ColorTool(String name, Color color, PaintPanel panel) {
		super(name);
		this.color = color;
		this.panel = panel;
	}

	@Override
	public void exec() {
		System.out.println("using tool " + this);
		panel.setColor(color);
	}

}
