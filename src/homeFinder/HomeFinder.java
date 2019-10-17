package homeFinder;

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

import rangeSlider.RangeSliderUI;


@SuppressWarnings("serial")
public class HomeFinder extends JFrame {

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;
	
	private final int NBHOUSES = 70;
	
	private static final int MINPRICE = 0;
	private static final int MAXPRICE = 1000;

	private static final int MINROOM = 1;
	private static final int MAXROOM = 7;
	
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
		
		homes = createHome(NBHOUSES);

		Container mainContainer = this.getContentPane();

		JPanel controlPanel = buildControlPanel();
		controlPanel.setPreferredSize(new Dimension(WIDTH/4, HEIGHT));

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
			nbRooms = rnd.nextInt(MAXROOM) + 1;
			price = rnd.nextInt(MAXPRICE) + 1;
			x = rnd.nextInt(799) + 1;
			y = rnd.nextInt(799) + 1;
			list.add(new Home(x, y, nbRooms, price));
		}
		return list;
	}
	
	private JPanel buildControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
		
		JPanel pricePanel = new JPanel();
		pricePanel.setLayout(new BorderLayout());
		JLabel priceLabel = new JLabel("Price:", JLabel.CENTER);
		priceLabel.setVerticalAlignment(JLabel.CENTER);
		pricePanel.add(priceLabel, BorderLayout.NORTH);
		price = new RangeSliderUI(MINPRICE, MAXPRICE);
		pricePanel.add(price, BorderLayout.CENTER);
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

		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new BorderLayout());
		JLabel roomLabel = new JLabel("Rooms:", JLabel.CENTER);
		roomLabel.setVerticalAlignment(JLabel.CENTER);		
		roomPanel.add(roomLabel, BorderLayout.NORTH);
		nbRooms = new RangeSliderUI(MINROOM, MAXROOM);
		roomPanel.add(nbRooms, BorderLayout.CENTER);
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
}
