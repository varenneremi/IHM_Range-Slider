package markingMenu;

import java.awt.Color;
import java.awt.Shape;

public class ColoredShape {

	private Shape shape;
	private Color color;
	
	public ColoredShape(Shape shape, Color color) {
		this.setShape(shape);
		this.setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
