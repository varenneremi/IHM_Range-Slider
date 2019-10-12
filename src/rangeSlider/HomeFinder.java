package rangeSlider;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HomeFinder extends JFrame {

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;
	
	public HomeFinder() {
		this.setTitle("Home Finder");
	    this.setSize(WIDTH, HEIGHT);
	    this.setLayout(new BorderLayout());
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    Container mainContainer = this.getContentPane();
	    
	    JPanel controlPanel = buildControlPanel();
	    controlPanel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
	    
	    mainContainer.add(controlPanel, BorderLayout.EAST);
	    
	    this.setVisible(true);
	}

	private JPanel buildControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
		
		controlPanel.add(new JLabel("Price:"));
		JPanel pricePanel = createRangeSlider(0, 100);
	    controlPanel.add(pricePanel);
	    	    
	    controlPanel.add(new JLabel("Rooms:"));
	    JPanel roomPanel = createRangeSlider(1, 7);
	    controlPanel.add(roomPanel);
	    
	    return controlPanel;
	}

	private JPanel createRangeSlider(int min, int max) {
		JPanel panel = new JPanel(new BorderLayout());
		
		RangeSliderUI rangeSlider = new RangeSliderUI(min, max);
	    panel.add(rangeSlider,BorderLayout.CENTER);
	    panel.add(rangeSlider.getLeftLabel(), BorderLayout.WEST);
	    panel.add(rangeSlider.getRightLabel(), BorderLayout.EAST);
	    
	    return panel;
	}
}
