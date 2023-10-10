package proyecto2d.modelo;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Cuadrados {
    private int numCuadrados = 1; // Número de cuadrados concéntricos
    private double[] rotationAngles; // Ángulos de rotación para cada cuadrado

    public Cuadrados(int numCuadrados) {
        this.numCuadrados = numCuadrados;
        this.rotationAngles = new double[numCuadrados];
        for (int i = 0; i < numCuadrados; i++) {
            rotationAngles[i] = i * (360.0 / numCuadrados);
        }
    }

    public JPanel createPanelWithSlider() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel cuadradosPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int sideLength = 300; // Longitud de los lados de los cuadrados concéntricos (constante)

                for (int i = 0; i < numCuadrados; i++) {
                    // Establecer la inclinación usando el ángulo actual
                    double radians = Math.toRadians(rotationAngles[i]);
                    AffineTransform transform = AffineTransform.getRotateInstance(radians, centerX, centerY);
                    g2d.setTransform(transform);

                    // Dibujar el cuadrado
                    int x = centerX - sideLength / 2;
                    int y = centerY - sideLength / 2;
                    g2d.drawRect(x, y, sideLength, sideLength);
                }
            }
        };

        JSlider slider = new JSlider(1, 10, numCuadrados); // Slider para controlar el número de cuadrados (1-10)
        //slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numCuadrados = slider.getValue();
                rotationAngles = new double[numCuadrados];
                for (int i = 0; i < numCuadrados; i++) {
                    rotationAngles[i] = i * (360.0 / numCuadrados);
                }
                cuadradosPanel.repaint();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Número de cuadrados: "));
        controlPanel.add(slider);

        panel.add(cuadradosPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Cuadrados Concéntricos");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                CuadradosConcentricos cuadradosConcentricos = new CuadradosConcentricos(5); // Inicialmente 5 cuadrados
                JPanel panelWithSlider = cuadradosConcentricos.createPanelWithSlider();

                frame.add(panelWithSlider);
                frame.setVisible(true);
            }
        });
    }*/
}
