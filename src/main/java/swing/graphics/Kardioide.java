package swing.graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 * Class to draw a kardioide curve
 *
 * @author dkaiser
 */
public class Kardioide extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int DIMENSION = 400;
	private static final int LEFT_DIM = 120;
	private static final int LEFT_DIM_L = (int) (LEFT_DIM * 0.06);
	private static final int LEFT_DIM_LEN = LEFT_DIM - LEFT_DIM_L * 2;
	private static int anzahl = 5000;
	static boolean zeichnen = false;
	private static int radius = 1;
	private static Color farbe = Color.RED;

	private final Canvas zeichenFlaeche;
	private final Canvas farbPreview;
	private final JButton startButton;
	private final JButton otherButton;
	private final JSpinner calcSpinner;
	private final JLabel calcLabel;
	private final JPanel leftPanel;
	private final JLabel radiusLabel;
	private final JSpinner radiusSpinner;
	private final JComboBox<String> farbBox;

	private Kardioide() {
		super();

		this.setSize(DIMENSION + LEFT_DIM, DIMENSION);
		final BorderLayout thisLayout = new BorderLayout();
		getContentPane().setLayout(thisLayout);

		zeichenFlaeche = new ZeichenCanvas();
		getContentPane().add(zeichenFlaeche, BorderLayout.EAST);
		zeichenFlaeche.setSize(DIMENSION, DIMENSION);
		zeichenFlaeche.setFocusTraversalKeysEnabled(false);

		leftPanel = new JPanel();
		getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setSize(LEFT_DIM, DIMENSION);
		leftPanel
				.setPreferredSize(new java.awt.Dimension(LEFT_DIM, DIMENSION));
		leftPanel.setLayout(null);
		leftPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		radiusLabel = new JLabel();
		leftPanel.add(radiusLabel);
		radiusLabel.setText("Radius:");
		radiusLabel.setBounds(LEFT_DIM_L, 21, 48, 14);

		radiusSpinner = new JSpinner(
				new SpinnerNumberModel(DIMENSION / 6, 0, DIMENSION / 2, 1));
		leftPanel.add(radiusSpinner);
		radiusSpinner.setBounds(LEFT_DIM_L, 42, 63, 28);
		radiusSpinner.setValue(Integer.valueOf(DIMENSION / 3));

		final ComboBoxModel<String> farbBoxModel = new DefaultComboBoxModel<>(
				new String[] { "Rot", "Grün", "Gelb", "Blau", "Schwarz", "andere" });
		farbBox = new JComboBox<>();
		leftPanel.add(farbBox);
		farbBox.setModel(farbBoxModel);
		farbBox.setBounds(LEFT_DIM_L, 126, LEFT_DIM_LEN, 28);
		farbBox.addItemListener(this::farbBoxItemStateChanged);

		calcLabel = new JLabel();
		leftPanel.add(calcLabel);
		calcLabel.setText("Anzahl Punkte:");
		calcLabel.setBounds(LEFT_DIM_L, 221, 100, 14);

		calcSpinner = new JSpinner(new SpinnerNumberModel(1000, 0, 10000, 10));
		leftPanel.add(calcSpinner);
		calcSpinner.setBounds(LEFT_DIM_L, 242, 100, 28);
		calcSpinner.setValue(Integer.valueOf(getAnzahl()));

		startButton = new JButton();
		leftPanel.add(startButton);
		startButton.setText("Start");
		startButton.setBounds(28, DIMENSION - 50, 63, 28);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				startButtonMouseClicked();
			}
		});
		otherButton = new JButton();
		leftPanel.add(otherButton);
		otherButton.setText("andere...");
		otherButton.setBounds(LEFT_DIM_L, 161, LEFT_DIM_LEN, 28);
		otherButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				otherButtonMouseClicked();
			}
		});

		farbPreview = new Canvas();
		leftPanel.add(farbPreview);
		farbPreview.setBounds(7, 91, 91, 28);
		farbPreview.setBackground(Color.RED);
	}

	private class ZeichenCanvas extends Canvas {
		// zeichnet die Kardioide
		private static final long serialVersionUID = 1L;

		public ZeichenCanvas() {
			super();
		}

		@Override
		public void paint(Graphics g) {
			if (zeichnen) {
				for (int i = 0; i < Kardioide.getAnzahl() + 1; i++) {
					final double winkel = (double) i / Kardioide.getAnzahl() * 2
							* Math.PI;
					final int x = (int) (getRadius() * Math.cos(winkel)
							* (1 + Math.cos(winkel)))
							+ DIMENSION / 4;
					final int y = (int) (getRadius() * Math.sin(winkel)
							* (1 + Math.cos(winkel)))
							+ DIMENSION / 2;
					g.setColor(getFarbe());
					g.drawLine(x, y, x, y);
				}
			}
		}
	}

	public static int getAnzahl() {
		return anzahl;
	}

	/**
	 * the main method to start the kardioide program
	 *
	 * @param args
	 *            not used in this program
	 */
	public static void main(String... args) {
		final Kardioide kardioide = new Kardioide();
		kardioide.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		kardioide.pack();
		kardioide.setVisible(true);
	}

	public static void setAnzahl(int anzahl) {
		Kardioide.anzahl = anzahl;
	}

	void farbBoxItemStateChanged(ItemEvent evt) {

		if (evt.getStateChange() == ItemEvent.SELECTED) {
			final int selectedIndex = farbBox.getSelectedIndex();
			switch (selectedIndex) {
			case 0:
				setFarbe(Color.RED);
				break;
			case 1:
				setFarbe(Color.GREEN);
				break;
			case 2:
				setFarbe(Color.YELLOW);
				break;
			case 3:
				setFarbe(Color.BLUE);
				break;
			case 4:
			default:
				setFarbe(Color.BLACK);
			}
			updateFarbPreview();
		}
	}

	public static Color getFarbe() {
		return farbe;
	}

	public static int getRadius() {
		return radius;
	}

	/**
	 * another mouse button was clicked
	 */
	void otherButtonMouseClicked() {
		setFarbe(JColorChooser.showDialog(Kardioide.this,
				"Bitte wählen Sie die Farbe aus!", Color.WHITE));
		farbBox.setSelectedIndex(5);
		updateFarbPreview();
	}

	public static void setFarbe(Color farbe) {
		Kardioide.farbe = farbe;
	}

	public static void setRadius(int radius) {
		Kardioide.radius = radius;
	}

	/**
	 * @return the zeichnen
	 */
	public static boolean isZeichnen() {
		return zeichnen;
	}

	/**
	 * @param zeichnen the zeichnen to set
	 */
	public static void setZeichnen(boolean zeichnen) {
		Kardioide.zeichnen = zeichnen;
	}

	/**
	 * method to draw the Kardioide
	 */
	void startButtonMouseClicked() {
		setRadius(
				Integer.parseInt((String) radiusSpinner.getValue()));
		setAnzahl(Integer.parseInt((String) calcSpinner.getValue()));
		setZeichnen(true);
		zeichenFlaeche.repaint();
	}

	private void updateFarbPreview() {
		farbPreview.setBackground(getFarbe());
		farbPreview.repaint();
	}
}