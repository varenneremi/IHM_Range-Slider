package rangeSlider;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	public MainFrame() {
		this.setTitle("Range Slider Frame");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    JPanel rangePanel = new JPanel();
		rangePanel.setLayout(new BoxLayout(rangePanel, BoxLayout.PAGE_AXIS));
		
	    rangePanel.add(new RangeSliderUI());
	    rangePanel.setPreferredSize(new Dimension(380, 100));
	    this.add(rangePanel);
	    
	    this.setVisible(true);
	}
	
	public static void main(String args[]) {
		MainFrame frame = new MainFrame();
	}
}
