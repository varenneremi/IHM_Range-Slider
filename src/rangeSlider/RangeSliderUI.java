package rangeSlider;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class RangeSliderUI extends JPanel {

	protected RangeSlider rangeSlider;
	
	public RangeSliderUI() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(420,420));
        
        setBackground(new Color(255, 255, 255));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
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

        g.drawString("BLAH", 20, 20);
        g.drawRect(200, 200, 200, 200);
    }
}
