package rangeSlider;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame() {
		this.setTitle("Range Slider Frame");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    this.add(new RangeSliderUI(0, 100));
	    this.add(new RangeSliderUI(0, 100));
	    
	    this.setVisible(true);
	}
	
	public static void main(String args[]) {
		MainFrame frame = new MainFrame();
	}
}
