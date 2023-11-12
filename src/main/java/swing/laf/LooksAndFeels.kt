package swing.laf

import javax.swing.UIManager

fun main() = UIManager.getInstalledLookAndFeels().forEach(::println)

