package swing.graphics

import java.awt.*
import java.awt.geom.*
import java.io.Serial
import javax.swing.JFrame

class ShapesDemo2D : JFrame() {
    private var fontMetrics: FontMetrics? = null

    override fun paint(g: Graphics) {
        val g2 = g as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val d = size
        val gridWidth = d.width / 6
        val gridHeight = d.height / 2
        fontMetrics = pickFont(g2, "Filled and Stroked GeneralPath", gridWidth)
        val fg3D = Color.lightGray
        g2.paint = fg3D
        g2.draw3DRect(0, 0, d.width - 1, d.height - 1, true)
        g2.draw3DRect(3, 3, d.width - 7, d.height - 7, false)
        g2.paint = fg
        var x = 5
        var y = 7
        val rectWidth = gridWidth - 2 * x
        var stringY = gridHeight - 3 - fontMetrics!!.descent
        val rectHeight = stringY - fontMetrics!!.maxAscent - y - 2

        // draw Line2D.Double
        g2.draw(Line2D.Double(x.toDouble(), (y + rectHeight - 1).toDouble(), (x + rectWidth).toDouble(), y.toDouble()))
        g2.drawString("Line2D", x, stringY)
        x += gridWidth

        // draw Rectangle2D.Double
        g2.stroke = stroke
        g2.draw(Rectangle2D.Double(x.toDouble(), y.toDouble(), rectWidth.toDouble(), rectHeight.toDouble()))
        g2.drawString("Rectangle2D", x, stringY)
        x += gridWidth

        // draw RoundRectangle2D.Double
        g2.stroke = dashed
        g2.draw(
            RoundRectangle2D.Double(
                x.toDouble(),
                y.toDouble(),
                rectWidth.toDouble(),
                rectHeight.toDouble(),
                10.0,
                10.0
            )
        )
        g2.drawString("RoundRectangle2D", x, stringY)
        x += gridWidth

        // draw Arc2D.Double
        g2.stroke = wideStroke
        g2.draw(
            Arc2D.Double(
                x.toDouble(),
                y.toDouble(),
                rectWidth.toDouble(),
                rectHeight.toDouble(),
                90.0,
                135.0,
                Arc2D.OPEN
            )
        )
        g2.drawString("Arc2D", x, stringY)
        x += gridWidth

        // draw Ellipse2D.Double
        g2.stroke = stroke
        g2.draw(Ellipse2D.Double(x.toDouble(), y.toDouble(), rectWidth.toDouble(), rectHeight.toDouble()))
        g2.drawString("Ellipse2D", x, stringY)
        x += gridWidth
        val polygon = createClosedGeneralPath(x, y, rectWidth, rectHeight)
        g2.draw(polygon)
        g2.drawString("GeneralPath", x, stringY)

        // NEW ROW
        x = 5
        y += gridHeight
        stringY += gridHeight

        // draw GeneralPath (polyline)
        val polyline = createGeneralPath(x, y, rectWidth, rectHeight)
        g2.draw(polyline)
        g2.drawString("GeneralPath (open)", x, stringY)
        x += gridWidth

        // fill Rectangle2D.Double (red)
        g2.paint = red
        g2.fill(Rectangle2D.Double(x.toDouble(), y.toDouble(), rectWidth.toDouble(), rectHeight.toDouble()))
        g2.paint = fg
        g2.drawString("Filled Rectangle2D", x, stringY)
        x += gridWidth

        // fill RoundRectangle2D.Double
        var redtowhite = GradientPaint(x.toFloat(), y.toFloat(), red, (x + rectWidth).toFloat(), y.toFloat(), white)
        g2.paint = redtowhite
        g2.fill(
            RoundRectangle2D.Double(
                x.toDouble(),
                y.toDouble(),
                rectWidth.toDouble(),
                rectHeight.toDouble(),
                10.0,
                10.0
            )
        )
        g2.paint = fg
        g2.drawString("Filled RoundRectangle2D", x, stringY)
        x += gridWidth

        // fill Arc2D
        g2.paint = red
        g2.fill(
            Arc2D.Double(
                x.toDouble(),
                y.toDouble(),
                rectWidth.toDouble(),
                rectHeight.toDouble(),
                90.0,
                135.0,
                Arc2D.OPEN
            )
        )
        g2.paint = fg
        g2.drawString("Filled Arc2D", x, stringY)
        x += gridWidth

        // fill Ellipse2D.Double
        redtowhite = GradientPaint(x.toFloat(), y.toFloat(), red, (x + rectWidth).toFloat(), y.toFloat(), white)
        g2.paint = redtowhite
        g2.fill(Ellipse2D.Double(x.toDouble(), y.toDouble(), rectWidth.toDouble(), rectHeight.toDouble()))
        g2.paint = fg
        g2.drawString("Filled Ellipse2D", x, stringY)
        x += gridWidth

        // fill and stroke GeneralPath
        val filledPolygon = createClosedGeneralPath(x, y, rectWidth, rectHeight)
        g2.paint = red
        g2.fill(filledPolygon)
        g2.paint = fg
        g2.draw(filledPolygon)
        g2.drawString("Filled and Stroked GeneralPath", x, stringY)
    }

    private fun createClosedGeneralPath(x: Int, y: Int, rectWidth: Int, rectHeight: Int): GeneralPath {
        val polygon = createGeneralPath(x, y, rectWidth, rectHeight)
        polygon.closePath()
        return polygon
    }

    private fun createGeneralPath(x: Int, y: Int, rectWidth: Int, rectHeight: Int): GeneralPath {
        val xPoints = intArrayOf(x, x + rectWidth, x, x + rectWidth)
        val yPoints = intArrayOf(y, y + rectHeight, y + rectHeight, y)
        val polygon = GeneralPath(Path2D.WIND_EVEN_ODD, xPoints.size)
        polygon.moveTo(xPoints[0].toFloat(), yPoints[0].toFloat())
        for (index in 1 until xPoints.size) {
            polygon.lineTo(xPoints[index].toFloat(), yPoints[index].toFloat())
        }
        return polygon
    }

    companion object {
        @Serial
        private val serialVersionUID = -3283858661450989056L
        private const val maxCharHeight = 15
        private const val minFontSize = 6
        val bg: Color = Color.white
        val fg: Color = Color.black
        val red: Color = Color.red
        val white: Color = Color.white
        val stroke = BasicStroke(2.0f)
        val wideStroke = BasicStroke(8.0f)
        private val dash1 = floatArrayOf(10.0f)
        val dashed = BasicStroke(
            1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
            0.0f
        )

        fun pickFont(g2: Graphics2D, longString: String, xSpace: Int): FontMetrics {
            var fontFits = false
            val font = g2.font
            var fontMetrics = g2.fontMetrics
            var size = font.size
            val name = font.name
            val style = font.style
            while (!fontFits) {
                if (fontMetrics.height <= maxCharHeight && fontMetrics.stringWidth(longString) <= xSpace) {
                    fontFits = true
                } else {
                    if (size <= minFontSize) {
                        fontFits = true
                    } else {
                        g2.font = Font(name, style, --size)
                        fontMetrics = g2.fontMetrics
                    }
                }
            }
            return fontMetrics
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val f: JFrame = ShapesDemo2D()
            f.setDefaultCloseOperation(EXIT_ON_CLOSE)
            f.background = bg
            f.setForeground(fg)
            f.pack()
            f.size = Dimension(500, 500)
            f.isVisible = true
        }
    }
}
