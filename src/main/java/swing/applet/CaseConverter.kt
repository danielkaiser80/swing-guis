package swing.applet

import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.*
import javax.swing.*

private const val UPPERCASE = "UPPERCASE"
private const val LOWERCASE = "lowercase"
private const val CLEAR = "Clear"

/**
 * Class CaseConverter - A simple applet that takes input from a text field and
 * converts to upper or lower case in response to user button selection. Works
 * well with a width of 300 and height of 120.
 *
 * Aug 2004: Updated from Applet to JApplet (mik)
 * Jul 2018: removed JApplet stuff
 * sometime later: made Kotlin version
 *
 * @author Bruce Quig
 * @author Michael Kölling
 * @author Daniel Kaiser
 *
 */
class CaseConverter(name: String) : JFrame(name), ActionListener {

    private val inputField: JTextField

    init {
        // set a layout with some spacing
        contentPane.layout = BorderLayout(12, 12)

        // add the title label
        val title = JLabel("Case Converter")
        contentPane.add(title, BorderLayout.NORTH)

        // create the center part with prompt and text field and add it
        val centerPanel = JPanel()
        val prompt = JLabel("Enter a string:")
        centerPanel.add(prompt)
        inputField = JTextField(16)
        centerPanel.add(inputField)

        contentPane.add(centerPanel, BorderLayout.CENTER)
        val buttonPanel = buttonPanel()


        // add the buttons panel to the content pane
        contentPane.add(buttonPanel, BorderLayout.SOUTH)
    }

    /**
     * Make a panel for the buttons.
     */
    private fun buttonPanel(): JPanel {
        val buttonPanel = JPanel()
        val that = this

        listOf(UPPERCASE, LOWERCASE, CLEAR).map { JButton(it).apply { addActionListener(that) } }
            .forEach(buttonPanel::add)
        return buttonPanel
    }

    /**
     * ActionListener Interface method. Called when action events occur with
     * registered components that can fire action events.
     *
     * @param evt
     * the ActionEvent object created by the event
     */
    override fun actionPerformed(evt: ActionEvent) {
        val locale = Locale.getDefault()
        evt.actionCommand.let {
            inputField.text = when (it) {
                // if clear button pressed
                CLEAR -> ""
                // uppercase button pressed
                UPPERCASE -> inputField.text.uppercase(locale)
                // lowercase button pressed
                LOWERCASE -> inputField.text.lowercase(locale)
                else -> inputField.text
            }
        }
    }
}

fun main() = CaseConverter("CaseConverter").let {
    it.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    it.pack()
    it.isVisible = true
}
