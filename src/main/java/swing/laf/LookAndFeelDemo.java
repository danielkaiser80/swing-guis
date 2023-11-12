package swing.laf;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
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
public class LookAndFeelDemo  {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookAndFeelDemo.class);

    private static final String DEFAULT_LAF = "Using the default look and feel.";
    private static final String labelPrefix = "Number of button clicks: ";
    private int numClicks = 0;
    final JLabel label = new JLabel(labelPrefix + "0    ");

    static JFrame frame;

    /**
     * Create the components for the main content pane
     *
     * @return components for the content pane
     */
    public Component createComponents() {
        final JButton button = new JButton("I'm a Swing button!");
        button.setMnemonic(KeyEvent.VK_I);
        button.addActionListener(e -> {
            numClicks++;
            label.setText(labelPrefix + numClicks);
        });
        label.setLabelFor(button);

        /*
         * An easy way to put space between a top-level container and its
         * contents is to put the contents in a JPanel that has an "empty"
         * border.
         */
        final JPanel pane = new JPanel(new GridLayout(0, 1));
        pane.add(button);
        pane.add(label);

        createLafRadioButtons(pane);

        pane.setBorder(BorderFactory.createEmptyBorder(30, // top
                30, // left
                10, // bottom
                30) // right
        );

        return pane;
    }

    public void createLafRadioButtons(JPanel pane) {
        // Create a button group for radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();

        // Create radio buttons
        JRadioButton defaultButton = new JRadioButton("Default");
        JRadioButton metalButton = new JRadioButton("Metal");
        JRadioButton nimbusButton = new JRadioButton("Nimbus");
        JRadioButton motifButton = new JRadioButton("Motif");
        JRadioButton macOsButton = new JRadioButton("macOS");
        JRadioButton oceanButton = new JRadioButton("Ocean");

        // Set the default selection
        defaultButton.setSelected(true);

        // Add radio buttons to the button group
        buttonGroup.add(defaultButton);
        buttonGroup.add(metalButton);
        buttonGroup.add(nimbusButton);
        buttonGroup.add(motifButton);
        buttonGroup.add(macOsButton);
        buttonGroup.add(oceanButton);

        // Add action listeners to the radio buttons
        ActionListener radioListener = e -> {
            String selectedOption = ((JRadioButton) e.getSource()).getText();

            changeLookAndFeel(selectedOption);
        };

        defaultButton.addActionListener(radioListener);
        metalButton.addActionListener(radioListener);
        nimbusButton.addActionListener(radioListener);
        motifButton.addActionListener(radioListener);
        macOsButton.addActionListener(radioListener);
        oceanButton.addActionListener(radioListener);

        // Add radio buttons to the frame
        pane.add(defaultButton);
        pane.add(metalButton);
        pane.add(nimbusButton);
        pane.add(motifButton);
        pane.add(macOsButton);
        pane.add(oceanButton);
    }

    private static void changeLookAndFeel(String selectedOption) {
        if (selectedOption != null) {
            final String lookAndFeel = getLookAndFeel(selectedOption);

            try {
                LOGGER.info("Setting laf... " + lookAndFeel);
                UIManager.setLookAndFeel(lookAndFeel);

                // If L&F = "Metal" / "Ocean", set the theme
                if ("Metal".equals(selectedOption) || "Ocean".equals(selectedOption)) {
                    setTheme(selectedOption);
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                }

                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();

            } catch (final ClassNotFoundException e) {
                LOGGER.error(
                        "Couldn't find class for specified look and feel:" + lookAndFeel);
                LOGGER.error("Did you include the L&F library in the class path?"
                        + DEFAULT_LAF);
                LOGGER.error(e.getMessage(), e);
            } catch (final UnsupportedLookAndFeelException e) {
                LOGGER.error("Can't use the specified look and feel (" + lookAndFeel
                        + ") on this platform." + DEFAULT_LAF);
                LOGGER.error(e.getMessage(), e);
            } catch (final Exception e) {
                LOGGER.error("Couldn't get specified look and feel (" + lookAndFeel
                        + "), for some reason." + DEFAULT_LAF);
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     *
     */
    private static void setTheme(String theme) {
        if ("Metal".equals(theme))
            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
        else if ("Ocean".equals(theme))
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
    }

    /**
     * Gets the class name of the Look and Feel specified
     *
     * @return the look and feel as String
     */
    private static String getLookAndFeel(String laf) {
        return switch (laf) {
            case "Default" -> UIManager.getSystemLookAndFeelClassName();
            case "Motif" -> "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            case "Nimbus" -> "javax.swing.plaf.nimbus.NimbusLookAndFeel";
            case "Metal", "Ocean" -> "javax.swing.plaf.metal.MetalLookAndFeel";
            case "macOS" -> "com.apple.laf.AquaLookAndFeel";
            default -> {
                LOGGER.error("Unexpected value of LOOKANDFEEL specified: " + laf);
                yield UIManager.getCrossPlatformLookAndFeelClassName();
            }
        };
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event dispatch thread.
     */
    static void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        frame = new JFrame("SwingApplication");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
     * @param args not used
     */
    public static void main(String... args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(LookAndFeelDemo::createAndShowGUI);
    }
}