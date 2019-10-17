package rangeSlider;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class HomeFinder extends JFrame {

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;
	RangeSliderUI price, nbRooms;
	ArrayList<Home> homes = new ArrayList<Home>();
	Map map;

	public HomeFinder() {
		this.setTitle("Home Finder");
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		homes = createHome(70);

		Container mainContainer = this.getContentPane();

		JPanel controlPanel = buildControlPanel();
		controlPanel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));

		mainContainer.add(controlPanel, BorderLayout.EAST);

		JPanel mapPanel = buildMap();
		mainContainer.add(mapPanel, BorderLayout.CENTER);

		this.setVisible(true);
	}

	private ArrayList<Home> createHome(int i) {
		ArrayList<Home> list = new ArrayList<Home>();
		Random rnd = new Random();
		int nbRooms, price, x, y;
		for (int k = 0; k < i; k ++) {
			nbRooms = rnd.nextInt(6) + 1;
			price = rnd.nextInt(99) + 1;
			x = rnd.nextInt(799) + 1;
			y = rnd.nextInt(799) + 1;
			list.add(new Home(x, y, nbRooms, price));
		}
		return list;
	}
	private JPanel buildControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
		
		controlPanel.add(new JLabel("Price:"));
		JPanel pricePanel = createRangeSlider(0, 100, true);
		controlPanel.add(pricePanel);
		price.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				map.repaint();
			}
		});
		price.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				map.repaint();
			}
		});


		controlPanel.add(new JLabel("Rooms:"));
		JPanel roomPanel = createRangeSlider(1, 7, false);
		controlPanel.add(roomPanel);

		nbRooms.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				map.repaint();
			}
		});
		nbRooms.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				map.repaint();
			}
		});

		return controlPanel;
	}

	private JPanel buildMap() {

		JPanel mapPanel = new JPanel();
		map = new Map(nbRooms, price, homes);
		mapPanel.add(map);

		return mapPanel;
	}
	
	private JPanel createRangeSlider(int min, int max, Boolean isPrice) {
		JPanel panel = new JPanel(new BorderLayout());
		RangeSliderUI rangeSlider = new RangeSliderUI(min, max);
		if (isPrice) 
			price = rangeSlider;
		else 
			nbRooms = rangeSlider;
		panel.add(rangeSlider,BorderLayout.CENTER);
		panel.add(rangeSlider.getLeftLabel(), BorderLayout.WEST);
		panel.add(rangeSlider.getRightLabel(), BorderLayout.EAST);

		return panel;
	}

}
