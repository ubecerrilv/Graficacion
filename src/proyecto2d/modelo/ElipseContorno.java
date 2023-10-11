package proyecto2d.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ElipseContorno extends JPanel {

    private static final int NUM_ELIPSES = 100;
    private static final int ANCHO_VENTANA = 800;
    private static final int ALTO_VENTANA = 600;

    
    
    public JPanel getPanel() {
    	JPanel panel = new JPanel() {
    		@Override
    	    protected void paintComponent(Graphics g) {
    	        super.paintComponent(g);

    	        Random random = new Random();

    	        for (int i = 0; i < NUM_ELIPSES; i++) {
    	            int x = random.nextInt(ANCHO_VENTANA);
    	            int y = random.nextInt(ALTO_VENTANA);
    	            int ancho = random.nextInt(100) + 50;
    	            int alto = random.nextInt(100) + 50;
    	            int inclinacion = random.nextInt(360);
    	            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

    	            g.setColor(color);
    	            Graphics2D g2d = (Graphics2D) g;
    	            g2d.rotate(Math.toRadians(inclinacion), x + ancho / 2, y + alto / 2);
    	            g2d.draw(new Ellipse2D.Double(x, y, ancho, alto));
    	            g2d.rotate(-Math.toRadians(inclinacion), x + ancho / 2, y + alto / 2);
    	        }
    	    }
    	};
    	return panel;
    }//FIN GETPANEL

}
