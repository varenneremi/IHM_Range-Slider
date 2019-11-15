package paint;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

import markingMenu.ColorTool;
import markingMenu.ColoredShape;
import markingMenu.SubMenu;
import markingMenu.Menu;
import markingMenu.ShapeTool;

public class MenuModel {

	protected static Menu[] initialMenu;

	protected Menu[] currentMenu;
	protected Menu[] previousMenu;
	protected MenuView view;
	PaintPanel panel;

	public MenuModel(MenuView view, PaintPanel panel) {
		this.view = view;
		this.panel = panel;

		ShapeTool shapeTools[] = createShapeTools();
		shapeTools[0].exec();
		ColorTool colorTools[] = createColorTools();
		initialMenu = new Menu[] { new SubMenu("Shape", shapeTools), new SubMenu("Color", colorTools) };
		previousMenu = new Menu[] { new SubMenu("Retour", initialMenu) };
		currentMenu = concat(initialMenu, previousMenu);
	}

	private ColorTool[] createColorTools() {
		ColorTool colorTools[] = { new ColorTool("red", Color.RED, panel), new ColorTool("blue", Color.BLUE, panel),
				new ColorTool("green", Color.GREEN, panel) };
		return colorTools;
	}

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
		currentMenu = concat(initialMenu, previousMenu);
	}

	public void setCurrentMenu(Menu[] menu) {
		previousMenu = new Menu[] { new SubMenu("Retour", Arrays.copyOf(currentMenu, currentMenu.length-1)) };
		currentMenu = concat(menu, previousMenu);
	}

	Menu[] concat(Menu[] A, Menu[] B) {
		int aLen = A.length;
		int bLen = B.length;
		Menu[] c = new Menu[aLen + bLen];
		System.arraycopy(A, 0, c, 0, aLen);
		System.arraycopy(B, 0, c, aLen, bLen);
		return c;
	}

}
