package swing.applet

import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

/**
 * Class CaseConverter - A simple applet that takes input from a text field and
 * converts to upper or lower case in response to user button selection. Works
 * well with a width of 300 and height of 120.
 *
 * Aug 2004: Updated from Applet to JApplet (mik)
 * Jul 2018: removed JApplet stuff
 *
 * @author Bruce Quig
 * @author Michael Kölling
 *
 * @version 2004-08-04
 */
class CaseConverter(name: String) : JFrame(name), ActionListener {

    private val UPPERCASE = "UPPERCASE"
    private val LOWERCASE = "lowercase"
    private val CLEAR = "Clear"

    private val inputField: JTextField

    init {
        // GUI elements are added to the applet's content pane, so get it for
        // us.
        val contentPane = contentPane

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

        // make a panel for the buttons
        val buttonPanel = JPanel()

        // add the buttons to the button panel
        val uppercase = JButton(UPPERCASE)
        uppercase.addActionListener(this)
        buttonPanel.add(uppercase)

        val lowercase = JButton(LOWERCASE)
        lowercase.addActionListener(this)
        buttonPanel.add(lowercase)

        val clear = JButton(CLEAR)
        clear.addActionListener(this)
        buttonPanel.add(clear)

        // add the buttons panel to the content pane
        contentPane.add(buttonPanel, BorderLayout.SOUTH)
    }

    /**
     * ActionListener Interface method. Called when action events occur with
     * registered components that can fire action events.
     *
     * @param evt
     * the ActionEvent object created by the event
     */
    override fun actionPerformed(evt: ActionEvent) {
        val command = evt.actionCommand
        // if clear button pressed
        if (CLEAR == command)
            inputField.text = ""
        else if (UPPERCASE == command)
            inputField.text = inputField.text.toUpperCase()
        else if (LOWERCASE == command)
            inputField.text = inputField.text.toLowerCase()// lowercase button pressed
        // uppercase button pressed
    }


}

fun main(args: Array<String>) {
    val frame = CaseConverter("CaseConverter")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
}
