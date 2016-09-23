package swing.laf;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LookAndFeelDemo.java is a Java SE 6 example, updated for Java 8
 *
 * @author dkaiser
 */
public class LookAndFeelDemo implements ActionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(LookAndFeelDemo.class);

	private static final String DEFAULT_LAF = "Using the default look and feel.";
	private static String labelPrefix = "Number of button clicks: ";
	private int numClicks = 0;
	final JLabel label = new JLabel(labelPrefix + "0    ");

	// Specify the look and feel to use by defining the LOOKANDFEEL constant
	// Valid values are: null (use the default), "Metal", "System", "Motif",
	// "Nimbus" and "Aqua"

	// Metal javax.swing.plaf.metal.MetalLookAndFeel]
	// Nimbus javax.swing.plaf.nimbus.NimbusLookAndFeel]
	// CDE/Motif com.sun.java.swing.plaf.motif.MotifLookAndFeel]
	// Mac OS X com.apple.laf.AquaLookAndFeel]
	static final String LOOKANDFEEL = "System";

	// If you choose the Metal L&F, you can also choose a theme.
	// Specify the theme to use by defining the THEME constant
	// Valid values are: "DefaultMetal" and "Ocean"
	static final String THEME = "Ocean";

	/**
	 * Create the components for the main content pane
	 *
	 * @return components for the content pane
	 */
	public Component createComponents() {
		final JButton button = new JButton("I'm a Swing button!");
		button.setMnemonic(KeyEvent.VK_I);
		button.addActionListener(this);
		label.setLabelFor(button);

		/*
		 * An easy way to put space between a top-level container and its
		 * contents is to put the contents in a JPanel that has an "empty"
		 * border.
		 */
		final JPanel pane = new JPanel(new GridLayout(0, 1));
		pane.add(button);
		pane.add(label);
		pane.setBorder(BorderFactory.createEmptyBorder(30, // top
				30, // left
				10, // bottom
				30) // right
		);

		return pane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		numClicks++;
		label.setText(labelPrefix + numClicks);
	}

	private static void initLookAndFeel() {
		LOGGER.info("Init...");

		if (LOOKANDFEEL != null) {
			final String lookAndFeel = getLookandFeel(LOOKANDFEEL);

			try {

				LOGGER.info("Setting laf... " + lookAndFeel);

				UIManager.setLookAndFeel(lookAndFeel);

				// If L&F = "Metal", set the theme

				if ("Metal".equals(LOOKANDFEEL)) {
					setTheme();
					UIManager.setLookAndFeel(new MetalLookAndFeel());
				}
			}

			catch (final ClassNotFoundException e) {
				LOGGER.error(
						"Couldn't find class for specified look and feel:" + lookAndFeel);
				LOGGER.error("Did you include the L&F library in the class path?"
						+ DEFAULT_LAF);
				LOGGER.error(e.getMessage(), e);
			}

			catch (final UnsupportedLookAndFeelException e) {
				LOGGER.error("Can't use the specified look and feel (" + lookAndFeel
						+ ") on this platform." + DEFAULT_LAF);
				LOGGER.error(e.getMessage(), e);
			}

			catch (final Exception e) {
				LOGGER.error("Couldn't get specified look and feel (" + lookAndFeel
						+ "), for some reason." + DEFAULT_LAF);
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 *
	 */
	private static void setTheme() {
		if ("DefaultMetal".equals(THEME))
			MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
		else if ("Ocean".equals(THEME))
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
	}

	/**
	 * Gets the class name of the Look and Feel specified
	 *
	 * @return the look and feel as String
	 */
	private static String getLookandFeel(String laf) {
		String lookAndFeel;

		switch (laf) {
		case "System":
			lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			break;

		case "Motif":
			lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			break;

		case "Nimbus":
			lookAndFeel = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
			break;

		case "Metal":
			lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
			break;

		case "Aqua":
			lookAndFeel = "com.apple.laf.AquaLookAndFeel";
			break;

		default:
			LOGGER.error("Unexpected value of LOOKANDFEEL specified: " + laf);
			lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			break;
		}

		return lookAndFeel;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	static void createAndShowGUI() {
		// Set the look and feel.
		initLookAndFeel();

		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		final JFrame frame = new JFrame("SwingApplication");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final LookAndFeelDemo app = new LookAndFeelDemo();
		final Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);

		LOGGER.info("Showing the Frame...");

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Main method to start the program
	 *
	 * @param args
	 *            not used
	 */
	public static void main(String... args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(LookAndFeelDemo::createAndShowGUI);
	}
}