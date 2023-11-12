package swing.laf;

import javax.swing.UIManager;

public class LooksAndFeels {

	public static void main(String... args) {

		UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo laf : lafs) {
			System.out.println(laf);
		}
	}
}
