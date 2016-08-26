package swing.graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class Kardioide extends JFrame {

	private class zeichenCanvas extends Canvas {
		// zeichnet die Kardioide
		private static final long serialVersionUID = 1L;

		public zeichenCanvas() {
			super();
		}

		@Override
		public void paint(Graphics g) {
			if (Kardioide.this.zeichnen) {
				for (int i = 0; i < Kardioide.getAnzahl() + 1; i++) {
					double winkel = ((double) i) / Kardioide.getAnzahl() * 2 * Math.PI;
					int x = (int) (Kardioide.this.getRadius() * Math.cos(winkel) * (1 + Math.cos(winkel)))
							+ dimension / 4;
					int y = (int) (Kardioide.this.getRadius() * Math.sin(winkel) * (1 + Math.cos(winkel)))
							+ dimension / 2;
					g.setColor(Kardioide.this.getFarbe());
					g.drawLine(x, y, x, y);
				}
			}
		}
	}

	private static final long serialVersionUID = 1L;
	private static final int dimension = 400;
	private static final int leftDimension = 120;
	private static final int leftDimleft = (int) (leftDimension * 0.06);
	private static final int leftDimlen = leftDimension - leftDimleft * 2;
	private static int anzahl = 5000;

	public static int getAnzahl() {
		return anzahl;
	}

	public static void setAnzahl(int anzahl) {
		Kardioide.anzahl = anzahl;
	}

	private Canvas zeichenFlaeche;
	private Canvas farbPreview;
	private JButton startButton;
	private JButton otherButton;
	private JSpinner calcSpinner;
	private JLabel calcLabel;
	private JPanel leftPanel;
	private JLabel radiusLabel;
	private JSpinner radiusSpinner;

	private JComboBox<String> farbBox;

	public boolean zeichnen = false;

	private int radius = 1;

	private Color farbe = Color.RED;

	public Kardioide() {
		super();
		try {
			this.setSize(dimension + leftDimension, dimension);
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);

			this.zeichenFlaeche = new zeichenCanvas();
			getContentPane().add(this.zeichenFlaeche, BorderLayout.EAST);
			this.zeichenFlaeche.setSize(dimension, dimension);
			this.zeichenFlaeche.setFocusTraversalKeysEnabled(false);

			this.leftPanel = new JPanel();
			getContentPane().add(this.leftPanel, BorderLayout.WEST);
			this.leftPanel.setSize(leftDimension, dimension);
			this.leftPanel.setPreferredSize(new java.awt.Dimension(leftDimension, dimension));
			this.leftPanel.setLayout(null);
			this.leftPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

			this.radiusLabel = new JLabel();
			this.leftPanel.add(this.radiusLabel);
			this.radiusLabel.setText("Radius:");
			this.radiusLabel.setBounds(leftDimleft, 21, 48, 14);

			this.radiusSpinner = new JSpinner(new SpinnerNumberModel(dimension / 6, 0, dimension / 2, 1));
			this.leftPanel.add(this.radiusSpinner);
			this.radiusSpinner.setBounds(leftDimleft, 42, 63, 28);
			this.radiusSpinner.setValue(Integer.valueOf(dimension / 3));

			ComboBoxModel<String> farbBoxModel = new DefaultComboBoxModel<>(
					new String[] { "Rot", "Grün", "Gelb", "Blau", "Schwarz", "andere" });
			this.farbBox = new JComboBox<>();
			this.leftPanel.add(this.farbBox);
			this.farbBox.setModel(farbBoxModel);
			this.farbBox.setBounds(leftDimleft, 126, leftDimlen, 28);
			this.farbBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent evt) {
					farbBoxItemStateChanged(evt);
				}
			});

			this.calcLabel = new JLabel();
			this.leftPanel.add(this.calcLabel);
			this.calcLabel.setText("Anzahl Punkte:");
			this.calcLabel.setBounds(leftDimleft, 221, 100, 14);

			this.calcSpinner = new JSpinner(new SpinnerNumberModel(1000, 0, 10000, 10));
			this.leftPanel.add(this.calcSpinner);
			this.calcSpinner.setBounds(leftDimleft, 242, 100, 28);
			this.calcSpinner.setValue(Integer.valueOf(getAnzahl()));

			this.startButton = new JButton();
			this.leftPanel.add(this.startButton);
			this.startButton.setText("Start");
			this.startButton.setBounds(28, dimension - 50, 63, 28);
			this.startButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent evt) {
					startButtonMouseClicked(evt);
				}
			});
			this.otherButton = new JButton();
			this.leftPanel.add(this.otherButton);
			this.otherButton.setText("andere...");
			this.otherButton.setBounds(leftDimleft, 161, leftDimlen, 28);
			this.otherButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent evt) {
					otherButtonMouseClicked(evt);
				}
			});

			this.farbPreview = new Canvas();
			this.leftPanel.add(this.farbPreview);
			this.farbPreview.setBounds(7, 91, 91, 28);
			this.farbPreview.setBackground(Color.RED);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void farbBoxItemStateChanged(ItemEvent evt) {

		if (evt.getStateChange() == ItemEvent.SELECTED) {
			int selectedIndex = this.farbBox.getSelectedIndex();
			switch (selectedIndex) {
			case 0:
				this.setFarbe(Color.RED);
				break;
			case 1:
				this.setFarbe(Color.GREEN);
				break;
			case 2:
				this.setFarbe(Color.YELLOW);
				break;
			case 3:
				this.setFarbe(Color.BLUE);
				break;
			case 4:
			default:
				this.setFarbe(Color.BLACK);
			}
			updateFarbPreview();
		}
	}

	public Color getFarbe() {
		return this.farbe;
	}

	public int getRadius() {
		return this.radius;
	}

	/**
	 * another mouse button was clicked
	 * 
	 * @param evt
	 *            Event-Parameter is not used in this program
	 */
	void otherButtonMouseClicked(MouseEvent evt) {
		this.setFarbe(JColorChooser.showDialog(Kardioide.this, "Bitte wählen Sie die Farbe aus!", Color.WHITE));
		this.farbBox.setSelectedIndex(5);
		updateFarbPreview();
	}

	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * method to draw the Kardioide
	 * 
	 * @param evt
	 *            Event-Parameter is not used in this program
	 */
	void startButtonMouseClicked(MouseEvent evt) {
		this.setRadius((Integer.valueOf(this.radiusSpinner.getValue().toString())).intValue());
		setAnzahl((Integer.valueOf(this.calcSpinner.getValue().toString())).intValue());
		this.zeichnen = true;
		this.zeichenFlaeche.repaint();
	}

	private void updateFarbPreview() {
		this.farbPreview.setBackground(this.getFarbe());
		this.farbPreview.repaint();
	}

	/**
	 * the main method to start the kardioide program
	 * 
	 * @param args
	 *            not used in this program
	 */
	public static void main(String... args) {
		Kardioide kardioide = new Kardioide();
		kardioide.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		kardioide.pack();
		kardioide.setVisible(true);
	}
}