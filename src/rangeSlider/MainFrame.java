package rangeSlider;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public MainFrame() {
		this.setTitle("Range Slider Frame");
	    this.setSize(400, 500);
	    this.setLayout(new BorderLayout());
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    JPanel rangePanel1 = new JPanel();
	    rangePanel1.setLayout(new BorderLayout());
	    RangeSliderUI rangeSlider1 = new RangeSliderUI(0, 100);
	    rangePanel1.add(rangeSlider1,BorderLayout.CENTER);
	    rangePanel1.add(rangeSlider1.getLeftLabel(), BorderLayout.WEST);
	    rangePanel1.add(rangeSlider1.getRightLabel(), BorderLayout.EAST);
	    
	    JPanel rangePanel2 = new JPanel();
	    rangePanel2.setLayout(new BorderLayout());
	    RangeSliderUI rangeSlider2 = new RangeSliderUI(0, 100);
	    rangePanel2.add(rangeSlider2,BorderLayout.CENTER);
	    rangePanel2.add(rangeSlider2.getLeftLabel(), BorderLayout.WEST);
	    rangePanel2.add(rangeSlider2.getRightLabel(), BorderLayout.EAST);
	    
	    this.add(rangePanel1, BorderLayout.CENTER);
	    this.add(rangePanel2, BorderLayout.SOUTH);
	    
	    this.setVisible(true);
	}
	
	public static void main(String args[]) {
		MainFrame frame = new MainFrame();
	}
}
