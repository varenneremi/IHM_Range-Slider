package paint;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import markingMenu.ColorTool;
import markingMenu.ColoredShape;
import markingMenu.MarkingMenu;
import markingMenu.Menu;
import markingMenu.ShapeTool;

public class MenuModel {

	protected static Menu[] initialMenu;

	protected Menu[] currentMenu;
	protected MenuView view;
	PaintPanel panel;

	public MenuModel(MenuView view, PaintPanel panel) {
		this.view = view;
		this.panel = panel;

		ShapeTool shapeTools[] = createShapeTools();
		ColorTool colorTools[] = createColorTools();
		initialMenu = new Menu[] { new MarkingMenu("Shape", shapeTools), new MarkingMenu("Color", colorTools), new MarkingMenu("3", colorTools), new MarkingMenu("4", colorTools),
				new MarkingMenu("5", colorTools), new MarkingMenu("6", colorTools), new MarkingMenu("7", colorTools), new MarkingMenu("8", colorTools), new MarkingMenu("9", colorTools)};
		currentMenu = initialMenu;
	}

	private ColorTool[] createColorTools() {
		ColorTool colorTools[] = { new ColorTool("red", Color.RED, panel), new ColorTool("blue", Color.BLUE, panel),
				new ColorTool("green", Color.GREEN, panel) };
		return colorTools;
	}

	@SuppressWarnings("serial")
	private ShapeTool[] createShapeTools() {
		ShapeTool tools[] = { new ShapeTool("pen", panel) {
			public void mouseDragged(MouseEvent e) {
				if (panel.active) {
					Path2D.Double path = (Path2D.Double) getShape();
					if (path == null) {
						path = new Path2D.Double();
						path.moveTo(getOrigin().getX(), getOrigin().getY());
						setShape(path);
						panel.getShapes().add(new ColoredShape(getShape(), panel.getColor()));
					}
					path.lineTo(e.getX(), e.getY());
					panel.repaint();
				}
			}
		}, new ShapeTool("rect", panel) {
			public void mouseDragged(MouseEvent e) {
				if (panel.active) {
					Rectangle2D.Double rect = (Rectangle2D.Double) getShape();
					if (rect == null) {
						rect = new Rectangle2D.Double(getOrigin().getX(), getOrigin().getY(), 0, 0);
						setShape(rect);
						panel.getShapes().add(new ColoredShape(getShape(), panel.getColor()));
					}
					rect.setRect(min(e.getX(), getOrigin().getX()), min(e.getY(), getOrigin().getY()),
							abs(e.getX() - getOrigin().getX()), abs(e.getY() - getOrigin().getY()));
					panel.repaint();
				}
			}
		}, new ShapeTool("ellipse", panel) {
			public void mouseDragged(MouseEvent e) {
				if (panel.active) {
					Ellipse2D.Double ellipse = (Ellipse2D.Double) getShape();
					if (ellipse == null) {
						ellipse = new Ellipse2D.Double(getOrigin().getX(), getOrigin().getY(), 0, 0);
						setShape(ellipse);
						panel.getShapes().add(new ColoredShape(getShape(), panel.getColor()));
					}
					ellipse.setFrameFromCenter(getOrigin().getX(), getOrigin().getY(), e.getX(), e.getY());
					panel.repaint();
				}
			}
		} };

		return tools;
	}

	public Menu[] getInitialMenu() {
		return initialMenu;
	}

	public void resetMenu() {
		currentMenu = initialMenu;
	}
	
	public void setCurrentMenu(Menu[] menu) {
		currentMenu = menu;
	}
}
