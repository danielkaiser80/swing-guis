package swing.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
	private JPanel[] Hoelzer;

	public static void main(String[] args) {
		NimSpiel inst = new NimSpiel("NimSpiel");
		inst.setVisible(true);
		inst.jSplitPane.setDividerLocation(0.5);
	}

	public NimSpiel(String s) {
		super(s);
		try {
			{
				getContentPane().setLayout(null);
				{
					this.jPanelBig = new JPanel();
					BorderLayout jPanel1Layout = new BorderLayout();
					this.jPanelBig.setLayout(jPanel1Layout);
					getContentPane().add(this.jPanelBig);
					this.jPanelBig.setBounds(112, 0, 280, 252);
					{
						this.jPanelUpper = new JPanel();
						this.jPanelUpper.setPreferredSize(new java.awt.Dimension(250, 125));
						this.jPanelUpper.setSize(250, 125);
						this.jPanelLower = new JPanel();
						this.jPanelLower.setPreferredSize(new java.awt.Dimension(268, 233));
						this.jPanelLower.setSize(250, 125);
						this.jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.jPanelUpper, this.jPanelLower);
						this.jPanelBig.add(this.jSplitPane, BorderLayout.CENTER);
						this.jSplitPane.setPreferredSize(new java.awt.Dimension(252, 252));
						this.jSplitPane.setOneTouchExpandable(true);
						this.jSplitPane.setVisible(true);

						this.Hoelzer = new JPanel[40];
						for (int i = 0; i < 20; i++) {
							this.Hoelzer[i] = new JPanel();
							this.Hoelzer[i].setBackground(Color.yellow);
							this.Hoelzer[i].setBorder(new LineBorder(Color.black));
							this.Hoelzer[i].setPreferredSize(new java.awt.Dimension(8, 100));
							this.jPanelUpper.add(this.Hoelzer[i]);
						}
						for (int i = 20; i < 40; i++) {
							this.Hoelzer[i] = new JPanel();
							this.Hoelzer[i].setBackground(Color.yellow);
							this.Hoelzer[i].setBorder(new LineBorder(Color.black));
							this.Hoelzer[i].setPreferredSize(new java.awt.Dimension(8, 100));
							this.jPanelLower.add(this.Hoelzer[i]);
						}
					}
				}
			}
			this.setSize(400, 350);
			{
				this.jMenuBar1 = new JMenuBar();
				setJMenuBar(this.jMenuBar1);
				{
					this.jMenu3 = new JMenu();
					this.jMenuBar1.add(this.jMenu3);
					this.jMenu3.setText("Datei");
					{
						this.newFileMenuItem = new JMenuItem();
						this.jMenu3.add(this.newFileMenuItem);
						this.newFileMenuItem.setText("Neues Spiel");
						this.newFileMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent evt) {
								newFileMenuItemActionPerformed(evt);
							}
						});
					}
					{
						this.openFileMenuItem = new JMenuItem();
						this.jMenu3.add(this.openFileMenuItem);
						this.openFileMenuItem.setText("Open");
					}
					{
						this.jSeparator2 = new JSeparator();
						this.jMenu3.add(this.jSeparator2);
					}
					{
						this.exitMenuItem = new JMenuItem();
						this.jMenu3.add(this.exitMenuItem);
						this.exitMenuItem.setText("Beenden");
						this.exitMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent evt) {
								System.exit(0);
							}
						});
					}
				}
				{
					this.jMenu4 = new JMenu();
					this.jMenuBar1.add(this.jMenu4);
					this.jMenu4.setText("Bearbeiten");
					{
						this.pasteMenuItem = new JMenuItem();
						this.jMenu4.add(this.pasteMenuItem);
						this.pasteMenuItem.setText("Paste");
					}
					{
						this.jSeparator1 = new JSeparator();
						this.jMenu4.add(this.jSeparator1);
					}
					{
						this.deleteMenuItem = new JMenuItem();
						this.jMenu4.add(this.deleteMenuItem);
						this.deleteMenuItem.setText("Delete");
					}
				}
				{
					this.jMenu5 = new JMenu();
					this.jMenuBar1.add(this.jMenu5);
					this.jMenu5.setText("Hilfe");
					{
						this.helpMenuItem = new JMenuItem();
						this.jMenu5.add(this.helpMenuItem);
						this.helpMenuItem.setText("Hilfe");
						this.helpMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent evt) {
								helpMenuItemActionPerformed(evt);
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void newFileMenuItemActionPerformed(ActionEvent evt) {
		System.out.println("newFileMenuItem.actionPerformed, event=" + evt);
	}

	/**
	 * @param evt
	 *            Parameter event is not used currently
	 */
	void helpMenuItemActionPerformed(ActionEvent evt) {
		JDialog meinDialog = new JDialog(this, "Titel des Dialogs", true);
		meinDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		meinDialog.setVisible(true);
	}
}
