package markingMenu;
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

/* paint *******************************************************************/

@SuppressWarnings("serial")
class Paint extends JFrame {
	ShapeTool tool;
	PaintPanel panel;

	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));

		panel = new PaintPanel();
		
		JPanel toolPanel = new JPanel(new FlowLayout());
		ShapeTool shapeTools[] = createShapeTools();
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
		
		add(toolPanel, BorderLayout.NORTH);
		add(panel);

		pack();
		setVisible(true);
	}

	private ColorTool[] createColorTools() {
		ColorTool colorTools[] = { 
				new ColorTool("red", Color.RED, panel),
				new ColorTool("blue", Color.BLUE, panel),
				new ColorTool("green", Color.GREEN, panel) 
				};
		return colorTools;
	}

	private ShapeTool[] createShapeTools() {
		ShapeTool tools[] = { new ShapeTool("pen", panel) {
			public void mouseDragged(MouseEvent e) {
				Path2D.Double path = (Path2D.Double) shape;
				if (path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					shape = path;
					panel.getShapes().add(new ColoredShape(shape, panel.getColor()));
				}
				path.lineTo(e.getX(), e.getY());
				panel.repaint();
			}
		}, new ShapeTool("rect", panel) {
			public void mouseDragged(MouseEvent e) {
				Rectangle2D.Double rect = (Rectangle2D.Double) shape;
				if (rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					shape = rect;
					panel.getShapes().add(new ColoredShape(shape, panel.getColor()));
				}
				rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				panel.repaint();
			}
		}, new ShapeTool("ellipse", panel) {
			public void mouseDragged(MouseEvent e) {
				Ellipse2D.Double ellipse = (Ellipse2D.Double) shape;
				if (ellipse == null) {
					ellipse = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
					shape = ellipse;
					panel.getShapes().add(new ColoredShape(shape, panel.getColor()));
				}
				ellipse.setFrameFromCenter(o.getX(), o.getY(), e.getX(), e.getY());
				panel.repaint();
			}
		} };

		return tools;
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
