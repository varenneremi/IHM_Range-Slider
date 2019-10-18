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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/* paint *******************************************************************/

@SuppressWarnings("serial")
class Paint extends JFrame {
	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	ShapeTool tool;
	JPanel panel;
	Color color = Color.BLACK;

	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));

		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				for (ColoredShape shape : shapes) {
					g2.setColor(shape.c);
					g2.draw(shape.shape);
				}
			}
		};
		
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
		
		add(toolPanel);
		add(panel);

		pack();
		setVisible(true);
	}

	private ColorTool[] createColorTools() {
		ColorTool colorTools[] = { 
				new ColorTool("red", Color.RED, color),
				new ColorTool("blue", Color.BLUE, color),
				new ColorTool("green", Color.GREEN, color) 
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
					shapes.add(new ColoredShape(shape, color));
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
					shapes.add(new ColoredShape(shape, color));
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
					shapes.add(new ColoredShape(shape, color));
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
				Paint paint = new Paint("paint");
			}
		});
	}
}
