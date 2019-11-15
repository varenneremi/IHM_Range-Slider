package markingMenu;

import java.awt.Color;
import java.awt.Shape;

public class ColoredShape {

	private Shape shape;
	private Color color;
	private boolean fill;
	
	public ColoredShape(Shape shape, Color color, boolean fill) {
		this.setShape(shape);
		this.setColor(color);
		this.setFill(fill);
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

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}
}
