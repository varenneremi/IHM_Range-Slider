package paint;
//////////////////////////////////////////////////////////////////////////////

// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/
import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import markingMenu.ColorTool;
import markingMenu.ColoredShape;
import markingMenu.SubMenu;
import markingMenu.Menu;
import markingMenu.ShapeTool;

/* paint *******************************************************************/

@SuppressWarnings("serial")
class Paint extends JFrame {
	PaintPanel panel;
	MenuView menuView;

	protected Menu[] initialMenu;

	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));

		panel = new PaintPanel();
		menuView = new MenuView(panel);
		panel.setMenuView(menuView);

		JPanel toolPanel = new JPanel(new FlowLayout());
/*		ShapeTool shapeTools[] = createShapeTools();
		toolPanel.add(new JToolBar() {
			{
				for (AbstractAction tool : shapeTools) {
					add(tool);
				}
			}
		});

		ColorTool colorTools[] = createColorTools();
		toolPanel.add(new JToolBar() {
			{
				for (AbstractAction tool : colorTools) {
					add(tool);
				}
			}
		});

		initialMenu = new Menu[] { new SubMenu("Shape", shapeTools), new SubMenu("Color", colorTools) };*/

		add(toolPanel, BorderLayout.NORTH);
		add(menuView);
		setVisible(true);
		menuView.setVisible(false);
		add(panel);

		pack();
		setVisible(true);
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

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Paint paint = new Paint("paint");
			}
		});
	}
}
