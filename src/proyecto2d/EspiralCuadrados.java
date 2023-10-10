package proyecto2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EspiralCuadrados extends JPanel {
    private int lado = 10; // Tamaño inicial del cuadrado
    private int separacion = 10; // Separación entre cuadrados
    private int numCuadrados = 20; // Número de cuadrados
    private int velocidad = 50; // Velocidad de dibujo

    public EspiralCuadrados() {
        // Configuración del panel
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.white);

        // Crear un slider para controlar la velocidad
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, velocidad);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Manejar el cambio en el slider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                velocidad = slider.getValue();
            }
        });

        // Crear un temporizador para animar la espiral
        Timer timer = new Timer(velocidad, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numCuadrados > 0) {
                    numCuadrados--;
                    lado += separacion;
                    repaint();
                }
            }
        });
        timer.start();

        // Agregar el slider y el panel al marco
        JFrame frame = new JFrame("Espiral de Cuadrados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.add(slider, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = getWidth() / 2;
        int y = getHeight() / 2;

        for (int i = 0; i < numCuadrados; i++) {
            g.setColor(Color.red);
            g.drawRect(x - lado / 2, y - lado / 2, lado, lado);
            x += separacion;
            y += separacion;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EspiralCuadrados();
            }
        });
    }
}

