package swing.graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FuellAlgorithmus extends JFrame {

	private class zeichenCanvas extends Canvas { // RGBCanvas {
		private static final long serialVersionUID = 1L;

		public zeichenCanvas() {
			super();
		}

		@Override
		public void paint(final Graphics g) {
			int x = 100;
			int y = 100;
			g.drawLine(x, y, x + 100, y);
			g.drawLine(x, y + 100, x + 100, y + 100);
			g.drawLine(x + 100, y, x + 100, y + 100);
			g.drawLine(x, y, x, y + 100);
			x = 117;
			y = 128;
		}
	}

	private static final long serialVersionUID = 1L;
	private Canvas zeichenFlaeche;
	public Color Farbe = Color.red;

	public FuellAlgorithmus(String name) {
		super(name);

		this.setSize(420, 300);
		this.zeichenFlaeche = new zeichenCanvas();
		getContentPane().add(this.zeichenFlaeche, BorderLayout.EAST);
		this.zeichenFlaeche.setSize(300, 300);
		this.zeichenFlaeche.setFocusTraversalKeysEnabled(false);

	}

	public static void main(String... args) {
		FuellAlgorithmus frame = new FuellAlgorithmus("Füllalgorithmus");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}