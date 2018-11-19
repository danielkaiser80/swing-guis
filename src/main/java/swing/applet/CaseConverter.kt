package swing.applet;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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
public class CaseConverter extends JFrame implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 176518414553115330L;
	private JTextField inputField;
	private static final String UPPERCASE = "UPPERCASE";
	private static final String LOWERCASE = "lowercase";
	private static final String CLEAR = "Clear";

	/**
	 * Create a new <code>FuellAlgorithmus</code> object
	 * 
	 * @param name
	 */
	public CaseConverter(String name) {
		super(name);
		// GUI elements are added to the applet's content pane, so get it for
		// us.
		final Container contentPane = getContentPane();

		// set a layout with some spacing
		contentPane.setLayout(new BorderLayout(12, 12));

		// add the title label
		final JLabel title = new JLabel("Case Converter");
		contentPane.add(title, BorderLayout.NORTH);

		// create the center part with prompt and text field and add it
		final JPanel centerPanel = new JPanel();
		final JLabel prompt = new JLabel("Enter a string:");
		centerPanel.add(prompt);
		inputField = new JTextField(16);
		centerPanel.add(inputField);

		contentPane.add(centerPanel, BorderLayout.CENTER);

		// make a panel for the buttons
		final JPanel buttonPanel = new JPanel();

		// add the buttons to the button panel
		final JButton uppercase = new JButton(UPPERCASE);
		uppercase.addActionListener(this);
		buttonPanel.add(uppercase);

		final JButton lowercase = new JButton(LOWERCASE);
		lowercase.addActionListener(this);
		buttonPanel.add(lowercase);

		final JButton clear = new JButton(CLEAR);
		clear.addActionListener(this);
		buttonPanel.add(clear);

		// add the buttons panel to the content pane
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * ActionListener Interface method. Called when action events occur with
	 * registered components that can fire action events.
	 *
	 * @param evt
	 *            the ActionEvent object created by the event
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		final String command = evt.getActionCommand();
		// if clear button pressed
		if (CLEAR.equals(command))
			inputField.setText("");
		// uppercase button pressed
		else if (UPPERCASE.equals(command))
			inputField.setText(inputField.getText().toUpperCase());
		// lowercase button pressed
		else if (LOWERCASE.equals(command))
			inputField.setText(inputField.getText().toLowerCase());
	}

	/**
	 * Main method to start the program
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String... args) {
		final CaseConverter frame = new CaseConverter("CaseConverter");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
