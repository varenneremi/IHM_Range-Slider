package paint;
//////////////////////////////////////////////////////////////////////////////

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/* paint *******************************************************************/

@SuppressWarnings("serial")
class Paint extends JFrame {
	PaintPanel panel;
	MenuView menuView;

	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));

		panel = new PaintPanel();
		menuView = new MenuView(panel);
		panel.setMenuView(menuView);

		add(menuView);
		setVisible(true);
		menuView.setVisible(false);
		add(panel);

		pack();
		setVisible(true);
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Paint paint = new Paint("paint");
			}
		});
	}
}
