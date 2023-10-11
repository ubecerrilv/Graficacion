package proyecto2d.modelo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class Tablero {
    private int numFilas = 8;
    private int numColumnas = 8;
    private JPanel board;

    public Tablero(int numFilas, int numColumnas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
    }

    public JPanel getPanel() {
        JPanel container = new JPanel(new BorderLayout());

        board = new JPanel(new GridLayout(numFilas, numColumnas));

        for (int row = 0; row < numFilas; row++) {
            for (int col = 0; col < numColumnas; col++) {
                JPanel square = new JPanel();
                if ((row + col) % 2 == 0) {
                    square.setBackground(Color.ORANGE);
                } else {
                    square.setBackground(Color.BLUE);
                }
                board.add(square);
            }
        }

        container.add(board, BorderLayout.CENTER);

        JSlider slider = new JSlider(1, 16, numFilas); // Slider para controlar el número de filas y columnas (1-16)
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                numFilas = value;
                numColumnas = value;
                container.remove(board);
                board = new JPanel(new GridLayout(numFilas, numColumnas));
                for (int row = 0; row < numFilas; row++) {
                    for (int col = 0; col < numColumnas; col++) {
                        JPanel square = new JPanel();
                        if ((row + col) % 2 == 0) {
                            square.setBackground(Color.ORANGE);
                        } else {
                            square.setBackground(Color.BLUE);
                        }
                        board.add(square);
                    }
                }
                container.add(board, BorderLayout.CENTER);
                container.revalidate();
                container.repaint();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Número de filas y columnas: "));
        controlPanel.add(slider);
        container.add(controlPanel, BorderLayout.SOUTH);

        return container;
    }//FIN GET PANEL

}//FIN CLASE

