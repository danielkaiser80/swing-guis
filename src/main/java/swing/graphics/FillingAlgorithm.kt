package swing.graphics

import javax.swing.JFrame
import java.awt.Canvas
import java.awt.Graphics
import java.awt.BorderLayout

/**
 * Example class to show an algorithm for filling drawn objects.
 *
 * @param name the name of the window
 *
 * @author Daniel Kaiser
 */
class FillingAlgorithm(name: String?) : JFrame(name) {
    private class DrawingCanvas : Canvas() {
        override fun paint(g: Graphics) {
            val x = 100
            val y = 100
            g.drawLine(x, y, x + 100, y)
            g.drawLine(x, y + 100, x + 100, y + 100)
            g.drawLine(x + 100, y, x + 100, y + 100)
            g.drawLine(x, y, x, y + 100)
        }
    }

    /**
     * Create a new `FillingAlgorithm` object
     */
    init {
        setSize(420, 300)
        val canvas: Canvas = DrawingCanvas()
        contentPane.add(canvas, BorderLayout.EAST)
        canvas.setSize(300, 300)
        canvas.focusTraversalKeysEnabled = false
    }
}


/**
 * Main method to start the program.
 */
fun main() =
        FillingAlgorithm("Filling Algorithm").let {
            it.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            it.pack()
            it.isVisible = true
        }
