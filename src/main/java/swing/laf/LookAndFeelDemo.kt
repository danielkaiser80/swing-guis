package swing.laf

import org.slf4j.LoggerFactory
import java.awt.BorderLayout
import java.awt.Component
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import javax.swing.*
import javax.swing.plaf.metal.DefaultMetalTheme
import javax.swing.plaf.metal.MetalLookAndFeel
import javax.swing.plaf.metal.OceanTheme

private const val DEFAULT_LAF = "Using the default look and feel."
private const val LABEL_PREFIX = "Number of button clicks: "


/**
 * LookAndFeelDemo.java is a Java SE 6 example, updated for Kotlin
 *
 * @author dkaiser
 */
class LookAndFeelDemo {
    private val logger = LoggerFactory.getLogger(LookAndFeelDemo::class.java)
    private var numClicks = 0
    private val label = JLabel("${LABEL_PREFIX}0    ")
    private val frame: JFrame

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event dispatch thread.
     */
    init {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true)
        frame = JFrame("SwingApplication")
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
        val contents = createComponents()
        frame.contentPane.add(contents, BorderLayout.CENTER)
        logger.info("Showing the Frame...")
        frame.pack()
        frame.isVisible = true
    }

    /**
     * Create the components for the main content pane
     *
     * @return components for the content pane
     */
    private fun createComponents(): Component {
        val button = JButton("I'm a Swing button!")
        button.setMnemonic(KeyEvent.VK_I)
        button.addActionListener {
            numClicks++
            label.setText(LABEL_PREFIX + numClicks)
        }
        label.setLabelFor(button)

        /*
         * An easy way to put space between a top-level container and its
         * contents is to put the contents in a JPanel that has an "empty"
         * border.
         */
        val pane = JPanel(GridLayout(0, 1))
        pane.add(button)
        pane.add(label)
        createLafRadioButtons(pane)
        pane.setBorder(
            BorderFactory.createEmptyBorder(
                30,  // top
                30,  // left
                10,  // bottom
                30
            ) // right
        )
        return pane
    }

    private fun createLafRadioButtons(pane: JPanel) {
        // Create a button group for radio buttons
        val buttonGroup = ButtonGroup()

        // Create radio buttons
        val defaultButton = JRadioButton("Default")
        val metalButton = JRadioButton("Metal")
        val nimbusButton = JRadioButton("Nimbus")
        val motifButton = JRadioButton("Motif")
        val macOsButton = JRadioButton("macOS")
        val oceanButton = JRadioButton("Ocean")

        // Set the default selection
        defaultButton.setSelected(true)

        // Add radio buttons to the button group
        buttonGroup.add(defaultButton)
        buttonGroup.add(metalButton)
        buttonGroup.add(nimbusButton)
        buttonGroup.add(motifButton)
        buttonGroup.add(macOsButton)
        buttonGroup.add(oceanButton)

        // Add action listeners to the radio buttons
        val radioListener = ActionListener { e: ActionEvent ->
            val selectedOption = (e.source as JRadioButton).text
            changeLookAndFeel(selectedOption)
        }
        defaultButton.addActionListener(radioListener)
        metalButton.addActionListener(radioListener)
        nimbusButton.addActionListener(radioListener)
        motifButton.addActionListener(radioListener)
        macOsButton.addActionListener(radioListener)
        oceanButton.addActionListener(radioListener)

        // Add radio buttons to the frame
        pane.add(defaultButton)
        pane.add(metalButton)
        pane.add(nimbusButton)
        pane.add(motifButton)
        pane.add(macOsButton)
        pane.add(oceanButton)
    }

    private fun changeLookAndFeel(selectedOption: String?) {
        if (selectedOption != null) {
            val lookAndFeel = getLookAndFeel(selectedOption)
            try {
                logger.info("Setting laf... $lookAndFeel")
                UIManager.setLookAndFeel(lookAndFeel)

                // If L&F = "Metal" / "Ocean", set the theme
                if ("Metal" == selectedOption || "Ocean" == selectedOption) {
                    setTheme(selectedOption)
                    UIManager.setLookAndFeel(MetalLookAndFeel())
                }
                SwingUtilities.updateComponentTreeUI(frame)
                frame.pack()

            } catch (e: ClassNotFoundException) {
                logger.error(
                    "Couldn't find class for specified look and feel:$lookAndFeel"
                )
                logger.error(
                    "Did you include the L&F library in the class path?$DEFAULT_LAF"
                )
                logger.error(e.message, e)
            } catch (e: UnsupportedLookAndFeelException) {
                logger.error(
                    "Can't use the specified look and feel ($lookAndFeel) on this platform.$DEFAULT_LAF"
                )
                logger.error(e.message, e)
            } catch (e: Exception) {
                logger.error(
                    "Couldn't get specified look and feel ($lookAndFeel), for some reason.$DEFAULT_LAF"
                )
                logger.error(e.message, e)
            }
        }
    }

    private fun setTheme(theme: String) {
        when (theme) {
            "Metal" -> MetalLookAndFeel.setCurrentTheme(DefaultMetalTheme())
            "Ocean" -> MetalLookAndFeel.setCurrentTheme(
                OceanTheme()
            )
        }
    }

    /**
     * Gets the class name of the Look and Feel specified
     *
     * @return the look and feel as String
     */
    private fun getLookAndFeel(laf: String): String = when (laf) {
        "Default" -> UIManager.getSystemLookAndFeelClassName()
        "Motif" -> "com.sun.java.swing.plaf.motif.MotifLookAndFeel"
        "Nimbus" -> "javax.swing.plaf.nimbus.NimbusLookAndFeel"
        "Metal", "Ocean" -> "javax.swing.plaf.metal.MetalLookAndFeel"
        "macOS" -> "com.apple.laf.AquaLookAndFeel"
        else -> {
            logger.error("Unexpected value of LOOKANDFEEL specified: $laf")
            UIManager.getCrossPlatformLookAndFeelClassName()
        }
    }
}

/**
 * Main method to start the program
 */
fun main() {
    // Schedule a job for the event dispatch thread:
    // creating and showing this application's GUI.
    SwingUtilities.invokeLater { LookAndFeelDemo() }
}
