package proyecto2d.modelo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Texto extends JPanel {

    private final String[] palabras = {"Tarea", "Textos", "Graficación"};
    private final int cantidadTextos = 100;

    public JPanel getPanel () {
    	JPanel panel = new JPanel() {
    		@Override
    	    protected void paintComponent(Graphics g) {
    	        super.paintComponent(g);

    	        Random random = new Random();
    	        int width = getWidth();
    	        int height = getHeight();

    	        for (int i = 0; i < cantidadTextos; i++) {
    	            String palabra = palabras[random.nextInt(palabras.length)];
    	            int x = random.nextInt(width); // Posición x aleatoria
    	            int y = random.nextInt(height); // Posición y aleatoria
    	            int fontSize = random.nextInt(30) + 12; // Tamaño de fuente aleatorio (entre 12 y 41)

    	            int red = random.nextInt(256); // Componente roja del color (0-255)
    	            int green = random.nextInt(256); // Componente verde del color (0-255)
    	            int blue = random.nextInt(256); // Componente azul del color (0-255)
    	            Color randomColor = new Color(red, green, blue);

    	            double rotation = Math.toRadians(random.nextInt(360)); // Inclinación aleatoria en radianes

    	            Graphics2D g2d = (Graphics2D) g;
    	            g2d.setColor(randomColor);
    	            g2d.setFont(new Font("Arial", Font.PLAIN, fontSize));

    	            // Aplica la transformación para rotar el texto
    	            g2d.rotate(rotation, x, y);

    	            // Dibuja el texto en la posición aleatoria
    	            g2d.drawString(palabra, x, y);

    	            // Restaura la transformación
    	            g2d.rotate(-rotation, x, y);
    	        }
    	    }
    	};
    	
    	return panel;
    }
}

