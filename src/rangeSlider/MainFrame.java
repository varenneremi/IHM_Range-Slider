package rangeSlider;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	// Variables declaration - do not modify
    private JPanel jPanel;
    
	public MainFrame() {
        initComponents();
    }
	
	
	private void initComponents() {
		jPanel = new RangeSliderUI();
		
		// add the component to the frame to see it!
        this.setContentPane(jPanel);
        // be nice to testers..
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();		
	}


	public static void main(String args[]) {
	    java.awt.EventQueue.invokeLater(new Runnable() {

	        public void run() {
	            new RangeSliderUI().setVisible(true);
	        }
	    });
	}
}
