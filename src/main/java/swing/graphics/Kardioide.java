package swing.graphics;

import swing.graphics.util.ClickedListener;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.io.Serial;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 * Class to draw a kardioide curve
 *
 * @author dkaiser
 */
class Kardioide extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final int DIMENSION = 400;
    private static final int LEFT_DIM = 120;
    private static final int LEFT_DIM_L = (int) (LEFT_DIM * 0.06);
    private static final int LEFT_DIM_LEN = LEFT_DIM - LEFT_DIM_L * 2;
    private static int amountOfPoints = 5000;
    private static boolean draw = false;
    static int radius = 1;
    private static Color color = Color.RED;

    private final Canvas drawingArea;
    private final Canvas colorPreview;
    private final JSpinner calcSpinner;
    private final JSpinner radiusSpinner;
    private final JComboBox<String> colorBox;

    private Kardioide() {
        super();

        this.setSize(DIMENSION + LEFT_DIM, DIMENSION);
        final BorderLayout thisLayout = new BorderLayout();
        getContentPane().setLayout(thisLayout);

        drawingArea = new DrawingCanvas();
        getContentPane().add(drawingArea, BorderLayout.EAST);
        drawingArea.setSize(DIMENSION, DIMENSION);
        drawingArea.setFocusTraversalKeysEnabled(false);

        JPanel leftPanel = new JPanel();
        getContentPane().add(leftPanel, BorderLayout.WEST);
        leftPanel.setSize(LEFT_DIM, DIMENSION);
        leftPanel
                .setPreferredSize(new java.awt.Dimension(LEFT_DIM, DIMENSION));
        leftPanel.setLayout(null);
        leftPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JLabel radiusLabel = new JLabel();
        leftPanel.add(radiusLabel);
        radiusLabel.setText("Radius:");
        radiusLabel.setBounds(LEFT_DIM_L, 21, 48, 14);

        radiusSpinner = new JSpinner(
                new SpinnerNumberModel(DIMENSION / 6, 0, DIMENSION / 2, 1));
        leftPanel.add(radiusSpinner);
        radiusSpinner.setBounds(LEFT_DIM_L, 42, 63, 28);
        radiusSpinner.setValue(DIMENSION / 3);

        final ComboBoxModel<String> colorBoxModel = new DefaultComboBoxModel<>(
                new String[]{"Red", "Green", "Yellow", "Blue", "Black", "Other"});
        colorBox = new JComboBox<>();
        leftPanel.add(colorBox);
        colorBox.setModel(colorBoxModel);
        colorBox.setBounds(LEFT_DIM_L, 126, LEFT_DIM_LEN, 28);
        colorBox.addItemListener(this::colorBoxItemStateChanged);

        final JLabel calcLabel = new JLabel();
        leftPanel.add(calcLabel);
        calcLabel.setText("Amount of Points:");
        calcLabel.setBounds(LEFT_DIM_L, 221, 100, 14);

        calcSpinner = new JSpinner(new SpinnerNumberModel(1000, 0, 10000, 10));
        leftPanel.add(calcSpinner);
        calcSpinner.setBounds(LEFT_DIM_L, 242, 100, 28);
        calcSpinner.setValue(getAmountOfPoints());

        JButton startButton = new JButton();
        leftPanel.add(startButton);
        startButton.setText("Start");
        startButton.setBounds(28, DIMENSION - 50, 63, 28);

        startButton.addMouseListener((ClickedListener) ((evt) -> startButtonMouseClicked()));
        JButton otherButton = new JButton();
        leftPanel.add(otherButton);
        otherButton.setText("other...");
        otherButton.setBounds(LEFT_DIM_L, 161, LEFT_DIM_LEN, 28);
        otherButton.addMouseListener((ClickedListener) ((evt) -> otherButtonMouseClicked()));

        colorPreview = new Canvas();
        leftPanel.add(colorPreview);
        colorPreview.setBounds(7, 91, 91, 28);
        colorPreview.setBackground(Color.RED);
    }

    private static class DrawingCanvas extends Canvas {

        @Serial
        private static final long serialVersionUID = 1L;

        public DrawingCanvas() {
            super();
        }

        private int getRadius() {
            return radius;
        }

        @Override
        public void paint(Graphics g) {
            if (isDraw()) {
                for (int i = 0; i < Kardioide.getAmountOfPoints() + 1; i++) {
                    final double angle = (double) i / Kardioide.getAmountOfPoints() * 2 * Math.PI;
                    final int x = (int) (getRadius() * Math.cos(angle)
                            * (1 + Math.cos(angle)))
                            + DIMENSION / 4;
                    final int y = (int) (getRadius() * Math.sin(angle)
                            * (1 + Math.cos(angle)))
                            + DIMENSION / 2;
                    g.setColor(getColor());
                    g.drawLine(x, y, x, y);
                }
            }
        }
    }

    static int getAmountOfPoints() {
        return amountOfPoints;
    }

    /**
     * the main method to start the kardioide program
     *
     * @param args not used in this program
     */
    public static void main(String... args) {
        final Kardioide kardioide = new Kardioide();
        kardioide.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        kardioide.pack();
        kardioide.setVisible(true);
    }

    private static void setAmountOfPoints(int amountOfPoints) {
        Kardioide.amountOfPoints = amountOfPoints;
    }

    private void colorBoxItemStateChanged(ItemEvent evt) {

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            final int selectedIndex = colorBox.getSelectedIndex();
            switch (selectedIndex) {
                case 0 -> setColor(Color.RED);
                case 1 -> setColor(Color.GREEN);
                case 2 -> setColor(Color.YELLOW);
                case 3 -> setColor(Color.BLUE);
                default -> setColor(Color.BLACK);
            }
            updateColorPreview();
        }
    }

    static Color getColor() {
        return color;
    }

    /**
     * Another mouse button was clicked.
     */
    void otherButtonMouseClicked() {
        setColor(JColorChooser.showDialog(Kardioide.this, "Please choose the color!", Color.WHITE));
        colorBox.setSelectedIndex(5);
        updateColorPreview();
    }

    private static void setColor(Color color) {
        Kardioide.color = color;
    }

    private static void setRadius(int radius) {
        Kardioide.radius = radius;
    }

    static boolean isDraw() {
        return draw;
    }

    private static void setDraw() {
        Kardioide.draw = true;
    }

    /**
     * method to draw the Kardioide
     */
    void startButtonMouseClicked() {
        setRadius((Integer) radiusSpinner.getValue());
        setAmountOfPoints((Integer) calcSpinner.getValue());
        setDraw();
        drawingArea.repaint();
    }

    private void updateColorPreview() {
        colorPreview.setBackground(getColor());
        colorPreview.repaint();
    }
}
