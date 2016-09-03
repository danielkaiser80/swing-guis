/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package swing.laf;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/*
 * LookAndFeelDemo.java is a Java SE 6 example.
 */
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

public class LookAndFeelDemo implements ActionListener {
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
		System.out.println("Init...");

		if (LOOKANDFEEL != null) {
			final String lookAndFeel = getLookandFeel(LOOKANDFEEL);

			try {

				System.out.println("Setting laf... " + lookAndFeel);

				UIManager.setLookAndFeel(lookAndFeel);

				// If L&F = "Metal", set the theme

				if (LOOKANDFEEL.equals("Metal")) {
					if (THEME.equals("DefaultMetal"))
						MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
					else if (THEME.equals("Ocean"))
						MetalLookAndFeel.setCurrentTheme(new OceanTheme());

					UIManager.setLookAndFeel(new MetalLookAndFeel());
				}
			}

			catch (final ClassNotFoundException e) {
				System.err.println(
						"Couldn't find class for specified look and feel:" + lookAndFeel);
				System.err.println("Did you include the L&F library in the class path?");
				System.err.println(DEFAULT_LAF);
				System.err.println(e.getMessage());
			}

			catch (final UnsupportedLookAndFeelException e) {
				System.err.println("Can't use the specified look and feel (" + lookAndFeel
						+ ") on this platform.");
				System.err.println(DEFAULT_LAF);
				System.err.println(e.getMessage());
			}

			catch (final Exception e) {
				System.err.println("Couldn't get specified look and feel (" + lookAndFeel
						+ "), for some reason.");
				System.err.println(DEFAULT_LAF);
				System.err.println(e.getMessage());
			}
		}
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
			System.err.println(
					"Unexpected value of LOOKANDFEEL specified: " + laf);
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

		System.out.println("Showing the Frame...");

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Main method to start the program
	 * @param args not used
	 */
	public static void main(String... args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(LookAndFeelDemo::createAndShowGUI);
	}
}