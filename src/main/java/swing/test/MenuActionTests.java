package swing.test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuActionTests extends JFrame {

	private static final long serialVersionUID = 4835173282511924673L;
	private Font font = new Font("serif", Font.ITALIC + Font.BOLD, 36);
	protected Action newAction, openAction, closeAction, saveAction, saveAsAction,
			undoAction, cutAction, copyAction,
			pasteAction, clearAction, selectAllAction, quitAction;
	static final JMenuBar mainMenuBar = new JMenuBar();
	protected JMenu fileMenu, editMenu;
	protected JTextField textField1, textField2;
	protected JTextArea textArea;
	private Container pane;

	public MenuActionTests() {

		super("Frame");

		this.pane = this.getContentPane();
		this.pane.setLayout(null);

		createActions();
		addMenus();

		this.setSize(800, 600);
		this.setVisible(true);
	}

	public static void quit() {
		System.exit(0);
	}

	public void createActions() {
		int shortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

		// Create actions that can be used by menus, buttons, toolbars, etc.
		this.newAction = new newActionClass("Neu...",
				KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcutKeyMask));
		this.openAction = new openActionClass("Öffnen",
				KeyStroke.getKeyStroke(KeyEvent.VK_O, shortcutKeyMask));
		this.closeAction = new closeActionClass("Schließen",
				KeyStroke.getKeyStroke(KeyEvent.VK_W, shortcutKeyMask));
		this.saveAction = new saveActionClass("Speichern",
				KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcutKeyMask));
		this.saveAsAction = new saveAsActionClass("Speichern unter");
		this.quitAction = new quitActionClass("Beenden",
				KeyStroke.getKeyStroke(KeyEvent.VK_Q, shortcutKeyMask));

		this.undoAction = new undoActionClass("Rückgängig",
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, shortcutKeyMask));
		this.cutAction = new cutActionClass("Ausschneiden",
				KeyStroke.getKeyStroke(KeyEvent.VK_X, shortcutKeyMask));
		this.copyAction = new copyActionClass("Kopieren",
				KeyStroke.getKeyStroke(KeyEvent.VK_C, shortcutKeyMask));
		this.pasteAction = new pasteActionClass("Einfügen",
				KeyStroke.getKeyStroke(KeyEvent.VK_V, shortcutKeyMask));
		this.clearAction = new clearActionClass("Löschen");
		this.selectAllAction = new selectAllActionClass("Alles auswählen",
				KeyStroke.getKeyStroke(KeyEvent.VK_A, shortcutKeyMask));
	}

	public void addMenus() {
		this.fileMenu = new JMenu("Ablage");
		this.fileMenu.add(new JMenuItem(this.newAction));
		this.fileMenu.add(new JMenuItem(this.openAction));
		this.fileMenu.add(new JMenuItem(this.closeAction));
		this.fileMenu.add(new JMenuItem(this.saveAction));
		this.fileMenu.add(new JMenuItem(this.saveAsAction));
		this.fileMenu.add(new JMenuItem(this.quitAction));
		mainMenuBar.add(this.fileMenu);

		this.editMenu = new JMenu("Bearbeiten");
		this.editMenu.add(new JMenuItem(this.undoAction));
		this.editMenu.addSeparator();
		this.editMenu.add(new JMenuItem(this.cutAction));
		this.editMenu.add(new JMenuItem(this.copyAction));
		this.editMenu.add(new JMenuItem(this.pasteAction));
		this.editMenu.add(new JMenuItem(this.clearAction));
		this.editMenu.addSeparator();
		this.editMenu.add(new JMenuItem(this.selectAllAction));
		mainMenuBar.add(this.editMenu);

		setJMenuBar(mainMenuBar);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.blue);
		g.setFont(this.font);
		g.drawString("Daniels Testprogramm zum ggT", 40, 80);
	}

	public class newActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8704361278704936719L;

		public newActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Neu...");
		}
	}

	public class openActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7315068572530002856L;

		public openActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Datei Öffnen...");
			newFileOpenMenuItemActionPerformed(e);
		}
	}

	public class closeActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 328915192279069953L;

		public closeActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Datei schließen...");
			MenuActionTests.quit();
		}
	}

	public class saveActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1316796539382290165L;

		public saveActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Speichern...");
		}
	}

	public class saveAsActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 834964760322931877L;

		public saveAsActionClass(String text) {
			super(text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Speichern unter...");
		}
	}

	public class quitActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2886055472361330479L;

		public quitActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Beenden...");
			quit();
		}
	}

	public class undoActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4144887365991345685L;

		public undoActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Rückgängig...");
		}
	}

	public class cutActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1055457616578099915L;

		public cutActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Ausschneiden...");
		}
	}

	public class copyActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8025919085610446677L;

		public copyActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Kopieren...");
		}
	}

	public class pasteActionClass extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6972591105878352374L;

		public pasteActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Einfügen...");
		}
	}

	public class clearActionClass extends AbstractAction {

		private static final long serialVersionUID = -4026614286718718309L;

		public clearActionClass(String text) {
			super(text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Löschen");
		}
	}

	public class selectAllActionClass extends AbstractAction {

		private static final long serialVersionUID = -1288824485803086015L;

		public selectAllActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Alles auswählen...");
		}
	}

	/**
	 * die Klasse zum "Datei-Öffnen"
	 */
	void newFileOpenMenuItemActionPerformed(ActionEvent evt) {
		System.out.println("newFileOpenMenuItem.actionPerformed, event=" + evt);

		this.pane.setLayout(new GridBagLayout());

		this.textField1 = new JTextField(20);
		this.textField2 = new JTextField(20);

		this.textArea = new JTextArea(5, 20);
		this.textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(this.textArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;

		this.pane.add(this.textField1, c);
		this.pane.add(this.textField2, c);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		this.pane.add(scrollPane, c);

		// Display the window.
		this.pack();
		this.setVisible(true);

	}

	public static void main(String... args) {
		MenuActionTests test = new MenuActionTests();
		test.setVisible(true);
	}
}
