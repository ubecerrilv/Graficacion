package proyecto2d.modelo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ElipseRellena extends JPanel {

    public JPanel getPanel() {
    	JPanel panel = new JPanel() {
    		@Override
    	    protected void paintComponent(Graphics g) {
    	        super.paintComponent(g);

    	        Random random = new Random();
    	        int width = getWidth();
    	        int height = getHeight();

    	        for (int i = 0; i < 100; i++) {
    	            int x = random.nextInt(width); // Posición x aleatoria
    	            int y = random.nextInt(height); // Posición y aleatoria
    	            int ellipseWidth = random.nextInt(100) + 20; // Ancho aleatorio (entre 20 y 119)
    	            int ellipseHeight = random.nextInt(100) + 20; // Alto aleatorio (entre 20 y 119)

    	            int red = random.nextInt(256); // Componente roja del color (0-255)
    	            int green = random.nextInt(256); // Componente verde del color (0-255)
    	            int blue = random.nextInt(256); // Componente azul del color (0-255)
    	            Color randomColor = new Color(red, green, blue);

    	            double rotation = Math.toRadians(random.nextInt(360)); // Inclinación aleatoria en radianes

    	            Graphics2D g2d = (Graphics2D) g;
    	            g2d.setColor(randomColor);

    	            // Dibuja la elipse con la posición, tamaño y rotación aleatorios
    	            g2d.rotate(rotation, x + ellipseWidth / 2, y + ellipseHeight / 2);
    	            g2d.fillOval(x, y, ellipseWidth, ellipseHeight);
    	            g2d.rotate(-rotation, x + ellipseWidth / 2, y + ellipseHeight / 2);
    	        }
    	    }
    	};
    	
    	
    	return panel;
    }
}

