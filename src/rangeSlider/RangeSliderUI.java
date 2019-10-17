package rangeSlider;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class RangeSliderUI extends JComponent {

	private static final int BARWIDTH = 200;
	private static final int BARHEIGHT = 10;
	private static final int BUTWIDTH = 10;
	private static final int BUTHEIGHT = 20;

	// x coordinate of the bar
	private int posBarMin = BUTWIDTH;
	private int posBarMax = posBarMin + BARWIDTH;

	// x coordinate of the center of the button
	private int posButtonL;
	private int posButtonR;

	// y coordinate offset of the bar and button
	private int barOffsetY = BUTHEIGHT;
	private int butOffsetY = 3 * barOffsetY / 4;

	// extremum Value for this slider
	private int valMin;
	
	// Nb pixel per value
	private int pixVal;

	protected RangeSlider rangeSlider;

	// Display of the two values
	public JLabel displayValLeft;
	public JLabel displayValRight;

	public RangeSliderUI(int min, int max) {

		valMin = min;
		pixVal = BARWIDTH / (max - min);
		posButtonL = posBarMin + pixVal * (max - min) / 4;
		int valL = valMin + (posButtonL - posBarMin) / pixVal;
		posButtonR = posBarMin + pixVal * 3 * (max - min) / 4;
		int valR = valMin + (posButtonR - posBarMin) / pixVal;

		displayValLeft = new JLabel(Integer.toString(valL));
		displayValRight = new JLabel(Integer.toString(valR));

		rangeSlider = new RangeSlider(valL, valR, min, max);

		setListener();
	}

	private void setListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				computeMousePressed(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				computeMouseReleased(evt);
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				computeMouseDragged(evt);
			}
		});
	}

	private void computeMousePressed(MouseEvent evt) {
		Point p = evt.getPoint();
		if (p.x >= posBarMin && p.x < posButtonL - BUTWIDTH / 2 && p.y >= barOffsetY && p.y <= barOffsetY + BARHEIGHT) {
			int val = valMin + (p.x - posBarMin) / pixVal;
			rangeSlider.clickLeft(val);
			posButtonL = p.x;
			displayValLeft.setText(Integer.toString(val));
		}
		if (p.x >= posButtonL - BUTWIDTH / 2 && p.x <= posButtonL + BUTWIDTH / 2 && p.y >= butOffsetY
				&& p.y <= butOffsetY + BUTHEIGHT) {
			rangeSlider.clickLeftButton();
		}
		if (p.x >= posButtonR - BUTWIDTH / 2 && p.x <= posButtonR + BUTWIDTH / 2 && p.y >= butOffsetY
				&& p.y <= butOffsetY + BUTHEIGHT) {
			rangeSlider.clickRightButton();
		}
		if (p.x > posButtonR + BUTWIDTH / 2 && p.x <= posBarMax && p.y >= barOffsetY && p.y <= barOffsetY + BARHEIGHT) {
			int val = valMin + (p.x - posBarMin) / pixVal;
			rangeSlider.clickRight(val);
			posButtonR = p.x;
			displayValRight.setText(Integer.toString(val));
		}
		if (p.x > posButtonL + BUTWIDTH / 2 && p.x < posButtonR - BUTWIDTH / 2 && p.y >= barOffsetY && p.y <= barOffsetY + BARHEIGHT) {
			int center = posButtonR - posButtonL;
			int val = valMin + (p.x - posBarMin) / pixVal;
			if (p.x <= center) {
				rangeSlider.clickLeft(val);
				posButtonL = p.x;
				displayValLeft.setText(Integer.toString(val));
			}
			else {
				rangeSlider.clickRight(val);
				posButtonR = p.x;
				displayValLeft.setText(Integer.toString(val));
			}
		}
		this.repaint();
	}

	private void computeMouseReleased(MouseEvent evt) {
		rangeSlider.mouseReleased();
	}

	private void computeMouseDragged(MouseEvent evt) {
		Point p = evt.getPoint();
		if (rangeSlider.getLeftPressed()) {
			if (p.x >= posBarMin && p.x < posButtonR) {
				int val = valMin + (p.x - posBarMin) / pixVal;
				rangeSlider.dragLeftButton(val);
				posButtonL = p.x;
				displayValLeft.setText(Integer.toString(val));
			}
		} else if (rangeSlider.getRightPressed()) {
			if (p.x > posButtonL && p.x <= posBarMax) {
				int val = valMin + (p.x - posBarMin) / pixVal;
				rangeSlider.dragRightButton(val);
				posButtonR = p.x;
				displayValRight.setText(Integer.toString(val));
			}
		}
		this.repaint();
	}

	public JLabel getLeftLabel() {
		return displayValLeft;
	}

	public JLabel getRightLabel() {
		return displayValRight;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(new Rectangle2D.Double(posBarMin, barOffsetY, posButtonL - BUTWIDTH / 2 - posBarMin, BARHEIGHT));

		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle2D.Double(posButtonL - BUTWIDTH / 2, butOffsetY, BUTWIDTH, BUTHEIGHT));

		g2d.setColor(Color.BLUE);
		g2d.fill(new Rectangle2D.Double(posButtonL + BUTWIDTH / 2, barOffsetY, posButtonR - posButtonL, BARHEIGHT));

		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle2D.Double(posButtonR - BUTWIDTH / 2, butOffsetY, BUTWIDTH, BUTHEIGHT));

		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(new Rectangle2D.Double(posButtonR + BUTWIDTH / 2, barOffsetY, posBarMax - posButtonR - BUTWIDTH / 2,
				BARHEIGHT));
	}
}
