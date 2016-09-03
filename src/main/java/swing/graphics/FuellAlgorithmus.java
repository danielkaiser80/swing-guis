package swing.graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Example class to show an algorithm for filling draw objects
 * 
 * @author dkaiser
 *
 */
public class FuellAlgorithmus extends JFrame {

	private class ZeichenCanvas extends Canvas {
		private static final long serialVersionUID = 1L;

		public ZeichenCanvas() {
			super();
		}

		@Override
		public void paint(final Graphics g) {
			final int x = 100;
			final int y = 100;
			g.drawLine(x, y, x + 100, y);
			g.drawLine(x, y + 100, x + 100, y + 100);
			g.drawLine(x + 100, y, x + 100, y + 100);
			g.drawLine(x, y, x, y + 100);
		}
	}

	private static final long serialVersionUID = 1L;
	private final Canvas zeichenFlaeche;

	/**
	 * Create a new <code>FuellAlgorithmus</code> object
	 * 
	 * @param name
	 */
	public FuellAlgorithmus(String name) {
		super(name);

		this.setSize(420, 300);
		zeichenFlaeche = new ZeichenCanvas();
		getContentPane().add(zeichenFlaeche, BorderLayout.EAST);
		zeichenFlaeche.setSize(300, 300);
		zeichenFlaeche.setFocusTraversalKeysEnabled(false);

	}

	/**
	 * Main method to start the program
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String... args) {
		final FuellAlgorithmus frame = new FuellAlgorithmus("Füllalgorithmus");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}