package swing.test;

// Event handling class are defined here.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Swing GUI classes are defined here.
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class HelloSwing extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6208364462383736254L;

	public HelloSwing(String name) {
		super(name);

		JButton bttn = new JButton("Click Me!");
		bttn.addActionListener(this);
		getContentPane().add(bttn);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// This method is called when an action event occurs.
		// In this case, the only possible source of the event
		// is the button. So, when this method is called, we know
		// that the button has been clicked. Respond by showing
		// an informational dialog box. The dialog box will
		// contain an "OK" button which the user must click to
		// dismiss the dialog box.
		String title = "Greetings"; // Shown in title bar of dialog box.
		String message = "Hello from the Swing User Interface Library.";
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Main method to start the program
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String... args) {
		final HelloSwing frame = new HelloSwing("Hello Swing");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
