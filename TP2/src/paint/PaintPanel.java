package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import markingMenu.ColoredShape;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {

	MenuView menuView;

	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	Color color;
	boolean active;
	Boolean isExpert = false;

	
	public PaintPanel() {
		this.color = Color.BLACK;
		this.active = true;
		this.add(setExpertMode());
		setListener();
	}

	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public JButton setExpertMode() {
		JButton b = new JButton("Expert mode");
		Color colorDefault = b.getBackground();
		b.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if (!isExpert) {
		    		isExpert = true;
		    		b.setBackground(Color.LIGHT_GRAY);
		    	} else {
		    		isExpert = false;
		    		b.setBackground(colorDefault);
		    	}
		    }
		});
		b.setVisible(true);
		return b;
	}

	public Vector<ColoredShape> getShapes() {
		return shapes;
	}

	private void setListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					active = false;
					menuView.active = true;
					menuView.setCenter(e.getPoint());
					menuView.resetMenu();
					if (!isExpert) {
						menuView.setVisible(true);
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					active = true;
					menuView.active = false;
					menuView.setVisible(false);
				}
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					menuView.dispatchEvent(e);
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		for (ColoredShape shape : shapes) {
			g2.setColor(shape.getColor());
			
			if(!shape.isFill()) {
				g2.draw(shape.getShape());
			} else {
				g2.fill(shape.getShape());
			}
		}
	}
}
