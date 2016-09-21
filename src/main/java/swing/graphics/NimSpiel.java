package swing.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class NimSpiel extends JFrame {
	private static final long serialVersionUID = -2039933990791771786L;
	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private JMenuItem deleteMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JSplitPane jSplitPane;
	private JPanel jPanelBig;
	private JPanel jPanelUpper;
	private JPanel jPanelLower;
	private JMenu jMenu4;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	private JPanel[] hoelzer;

	/**
	 * Main method to start the program
	 * @param args not used
	 */
	public static void main(String... args) {
		final NimSpiel inst = new NimSpiel("NimSpiel");
		inst.setVisible(true);
		inst.jSplitPane.setDividerLocation(0.5);
	}

	/**
	 * Constructor for a new nim game
	 * @param title the title of the Frame
	 */
	public NimSpiel(String title) {
		super(title);
		try {

			getContentPane().setLayout(null);

			jPanelBig = new JPanel();
			final BorderLayout jPanel1Layout = new BorderLayout();
			jPanelBig.setLayout(jPanel1Layout);
			getContentPane().add(jPanelBig);
			jPanelBig.setBounds(112, 0, 280, 252);

			jPanelUpper = new JPanel();
			jPanelUpper.setPreferredSize(new java.awt.Dimension(250, 125));
			jPanelUpper.setSize(250, 125);
			jPanelLower = new JPanel();
			jPanelLower.setPreferredSize(new java.awt.Dimension(268, 233));
			jPanelLower.setSize(250, 125);
			jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jPanelUpper,
					jPanelLower);
			jPanelBig.add(jSplitPane, BorderLayout.CENTER);
			jSplitPane.setPreferredSize(new java.awt.Dimension(252, 252));
			jSplitPane.setOneTouchExpandable(true);
			jSplitPane.setVisible(true);

			hoelzer = new JPanel[40];
			for (int i = 0; i < 20; i++) {
				hoelzer[i] = new JPanel();
				hoelzer[i].setBackground(Color.yellow);
				hoelzer[i].setBorder(new LineBorder(Color.black));
				hoelzer[i].setPreferredSize(new java.awt.Dimension(8, 100));
				jPanelUpper.add(hoelzer[i]);
			}
			for (int i = 20; i < 40; i++) {
				hoelzer[i] = new JPanel();
				hoelzer[i].setBackground(Color.yellow);
				hoelzer[i].setBorder(new LineBorder(Color.black));
				hoelzer[i].setPreferredSize(new java.awt.Dimension(8, 100));
				jPanelLower.add(hoelzer[i]);
			}

			this.setSize(400, 350);

			jMenuBar1 = new JMenuBar();
			setJMenuBar(jMenuBar1);

			jMenu3 = new JMenu();
			jMenuBar1.add(jMenu3);
			jMenu3.setText("Datei");

			newFileMenuItem = new JMenuItem();
			jMenu3.add(newFileMenuItem);
			newFileMenuItem.setText("Neues Spiel");
			newFileMenuItem.addActionListener(
					NimSpiel::newFileMenuItemActionPerformed);

			openFileMenuItem = new JMenuItem();
			jMenu3.add(openFileMenuItem);
			openFileMenuItem.setText("Open");

			jSeparator2 = new JSeparator();
			jMenu3.add(jSeparator2);

			exitMenuItem = new JMenuItem();
			jMenu3.add(exitMenuItem);
			exitMenuItem.setText("Beenden");
			exitMenuItem.addActionListener(evt -> System.exit(0));

			jMenu4 = new JMenu();
			jMenuBar1.add(jMenu4);
			jMenu4.setText("Bearbeiten");

			pasteMenuItem = new JMenuItem();
			jMenu4.add(pasteMenuItem);
			pasteMenuItem.setText("Paste");

			jSeparator1 = new JSeparator();
			jMenu4.add(jSeparator1);

			deleteMenuItem = new JMenuItem();
			jMenu4.add(deleteMenuItem);
			deleteMenuItem.setText("Delete");

			jMenu5 = new JMenu();
			jMenuBar1.add(jMenu5);
			jMenu5.setText("Hilfe");

			helpMenuItem = new JMenuItem();
			jMenu5.add(helpMenuItem);
			helpMenuItem.setText("Hilfe");
			helpMenuItem.addActionListener(
					evt -> helpMenuItemActionPerformed());
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	static void newFileMenuItemActionPerformed(ActionEvent evt) {
		System.out.println("newFileMenuItem.actionPerformed, event=" + evt);
	}

	/**
	 * Action to perform when the help menu is clicked
	 */
	void helpMenuItemActionPerformed() {
		final JDialog meinDialog = new JDialog(this, "Titel des Dialogs", true);
		meinDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		meinDialog.setVisible(true);
	}
}