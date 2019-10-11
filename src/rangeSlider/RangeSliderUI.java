package rangeSlider;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class RangeSliderUI extends JComponent {

	protected RangeSlider rangeSlider;
	
	public RangeSliderUI() {
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                //jPanel2MousePressed(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                //jPanel2MouseReleased(evt);
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //jPanel2MouseDragged(evt);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	Graphics2D g2d = (Graphics2D) g;
    	
    	g2d.setColor(Color.GRAY);
    	g2d.fill(new Rectangle2D.Double(10, 10, 100, 10));
        
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(25, 5, 5, 20));
        g2d.fill(new Rectangle2D.Double(75, 5, 5, 20));
    }
}
