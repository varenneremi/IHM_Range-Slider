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

@SuppressWarnings("serial")
public class RangeSliderUI extends JComponent {

	private static final int BARWIDTH = 200;
	private static final int BARHEIGHT = 10;
	private static final int BUTWIDTH = 10;
	private static final int BUTHEIGHT = 20;

	private int posBarMin = BUTWIDTH;
	private int posBarMax = BUTWIDTH + BARWIDTH;
	private int posButtonL;
	private int posButtonR;

	private int butOffsetX = posBarMin - BUTWIDTH / 2;
	private int barOffsetY = BUTHEIGHT;
	private int butOffsetY = 3 * barOffsetY / 4;
	
	private int valMin;
	private int pixVal;

	protected RangeSlider rangeSlider;
	protected IRangeSliderModel model;

	public RangeSliderUI(int min, int max) {

		valMin = min;
		pixVal = BARWIDTH / (max - min);
		posButtonL = butOffsetX + pixVal * (max - min) / 4;
		posButtonR = butOffsetX + pixVal * 3 * (max - min) / 4;

		rangeSlider = new RangeSlider(min + (max - min) / 4, min + 3 * (max - min) / 4, min, max, this);
		model = rangeSlider.getModel();

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
		if(p.x >= posBarMin && p.x < posButtonL && p.y >= barOffsetY && p.y <= barOffsetY + BARHEIGHT) {
			rangeSlider.clickLeft(valMin + (p.x - posBarMin) / pixVal);
		}
		if(p.x >= posButtonL && p.x <= posButtonL + BUTWIDTH && p.y >= butOffsetY && p.y <= butOffsetY + BUTHEIGHT) {
			rangeSlider.clickLeftButton();
		}
		if(p.x >= posButtonR && p.x <= posButtonR + BUTWIDTH && p.y >= butOffsetY && p.y <= butOffsetY + BUTHEIGHT) {
			rangeSlider.clickRightButton();
		}
		if(p.x > posButtonR + BUTWIDTH && p.x <= posBarMax && p.y >= barOffsetY && p.y <= barOffsetY + BARHEIGHT) {
			rangeSlider.clickRight();
		}
	}

	private void computeMouseReleased(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	private void computeMouseDragged(MouseEvent evt) {
		// TODO Auto-generated method stub

	}
	
	public void computeLeftBut(int newValLeft) {
		posButtonL = butOffsetX + pixVal * newValLeft;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(new Rectangle2D.Double(posBarMin, barOffsetY, posButtonL - posBarMin, BARHEIGHT));

		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle2D.Double(posButtonL, butOffsetY, BUTWIDTH, BUTHEIGHT));

		g2d.setColor(Color.BLUE);
		g2d.fill(new Rectangle2D.Double(posButtonL + BUTWIDTH, barOffsetY, posButtonR - posButtonL + BUTWIDTH,
				BARHEIGHT));

		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle2D.Double(posButtonR, butOffsetY, BUTWIDTH, BUTHEIGHT));

		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(
				new Rectangle2D.Double(posButtonR + BUTWIDTH, barOffsetY, posBarMax - posButtonR - BUTWIDTH, BARHEIGHT));
	}
}
