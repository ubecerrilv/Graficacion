package proyecto2d.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

public class Degradados extends JFrame {
    private JPanel drawingPanel;
    private JComboBox<String> figuraComboBox;
    private JSlider repeticionesSlider;
    private JButton color1Button;
    private JButton color2Button;
    private Color color1 = Color.RED; // Color por defecto
    private Color color2 = Color.BLUE; // Color por defecto
    private int repeticiones = 10; // Valor por defecto

    public Degradados() {
        setTitle("Dibujar Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para dibujar
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                // Dibuja la figura seleccionada con el degradado
                if (figuraComboBox.getSelectedItem().equals("Cuadrado")) {
                    drawSquare(g2d, width, height);
                } else if (figuraComboBox.getSelectedItem().equals("Rectángulo")) {
                    drawRectangle(g2d, width, height);
                } else if (figuraComboBox.getSelectedItem().equals("Círculo")) {
                    drawCircle(g2d, width, height);
                }
            }
        };
        add(drawingPanel, BorderLayout.CENTER);

        // ComboBox para seleccionar la figura
        figuraComboBox = new JComboBox<>(new String[]{"Cuadrado", "Rectángulo", "Círculo"});
        figuraComboBox.addActionListener(e -> drawingPanel.repaint());
        add(figuraComboBox, BorderLayout.NORTH);

        // Botones para seleccionar colores
        color1Button = new JButton("Color 1");
        color1Button.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Seleccionar Color 1", color1);
            if (selectedColor != null) {
                color1 = selectedColor;
                drawingPanel.repaint();
            }
        });

        color2Button = new JButton("Color 2");
        color2Button.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Seleccionar Color 2", color2);
            if (selectedColor != null) {
                color2 = selectedColor;
                drawingPanel.repaint();
            }
        });

        JPanel colorPanel = new JPanel();
        colorPanel.add(color1Button);
        colorPanel.add(color2Button);
        add(colorPanel, BorderLayout.SOUTH);

        // Slider para controlar el número de repeticiones del degradado
        repeticionesSlider = new JSlider(1, 20, 10);
        repeticionesSlider.setMajorTickSpacing(5);
        repeticionesSlider.setPaintTicks(true);
        repeticionesSlider.addChangeListener(e -> {
            repeticiones = repeticionesSlider.getValue();
            drawingPanel.repaint();
        });
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(new JLabel("Repeticiones: "));
        sliderPanel.add(repeticionesSlider);
        add(sliderPanel, BorderLayout.WEST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void drawSquare(Graphics2D g2d, int width, int height) {
        int size = Math.min(width, height) - 20;
        int sectionWidth = size / repeticiones;

        for (int i = 0; i < repeticiones; i++) {
            GradientPaint gradient = new GradientPaint(i * sectionWidth, 0, color1, (i + 1) * sectionWidth, height, color2);
            g2d.setPaint(gradient);
            g2d.fillRect(i * sectionWidth, 0, sectionWidth, height);
        }
    }

    private void drawRectangle(Graphics2D g2d, int width, int height) {
        int rectWidth = width - 40;
        int rectHeight = height / 2;
        int sectionHeight = rectHeight / repeticiones;

        for (int i = 0; i < repeticiones; i++) {
            GradientPaint gradient = new GradientPaint(0, i * sectionHeight, color1, width, (i + 1) * sectionHeight, color2);
            g2d.setPaint(gradient);
            g2d.fillRect(20, i * sectionHeight, rectWidth, sectionHeight);
        }
    }

    private void drawCircle(Graphics2D g2d, int width, int height) {
        int diameter = Math.min(width, height) - 20;
        int sectionHeight = diameter / repeticiones;

        for (int i = 0; i < repeticiones; i++) {
            GradientPaint gradient = new GradientPaint(0, i * sectionHeight, color1, width, (i + 1) * sectionHeight, color2);
            g2d.setPaint(gradient);
            g2d.fill(new Ellipse2D.Double((width - diameter) / 2, i * sectionHeight, diameter, sectionHeight));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Degradados::new);
    }
}
