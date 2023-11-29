package proyecto2d.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Figura {
    private Shape shape;
    private Color color;
    private boolean selected;

    public Figura(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.selected = false;
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

class DibujarPanel extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList<Figura> figuras = new ArrayList<>();
    private Figura figuraActual;
    private int figuraSeleccionadaIndex = -1;
    private Point inicioArrastre;

    public DibujarPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Figura figura : figuras) {
            g2.setColor(figura.getColor());
            g2.draw(figura.getShape());
        }
    }

    public void agregarFigura(Shape shape, Color color) {
        figuras.add(new Figura(shape, color));
        repaint();
    }

    public void seleccionarFigura(int x, int y) {
        figuraSeleccionadaIndex = -1;
        for (int i = figuras.size() - 1; i >= 0; i--) {
            if (figuras.get(i).getShape().contains(x, y)) {
                figuraSeleccionadaIndex = i;
                figuraActual = figuras.get(i);
                figuraActual.setSelected(true);
                break;
            }
        }

        if (figuraSeleccionadaIndex != -1) {
            figuras.remove(figuraSeleccionadaIndex);
            figuras.add(figuraActual);
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        inicioArrastre = e.getPoint();
        seleccionarFigura(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (figuraSeleccionadaIndex != -1) {
            int dx = e.getX() - inicioArrastre.x;
            int dy = e.getY() - inicioArrastre.y;

            Shape figura = figuraActual.getShape();
            figura = AffineTransform.getTranslateInstance(dx, dy).createTransformedShape(figura);
            figuraActual = new Figura(figura, figuraActual.getColor());
            figuras.set(figuraSeleccionadaIndex, figuraActual);

            inicioArrastre = e.getPoint();
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // No se utiliza en este ejemplo
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (figuraSeleccionadaIndex != -1) {
            figuraActual.setSelected(false);
            figuraSeleccionadaIndex = -1;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No se utiliza en este ejemplo
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No se utiliza en este ejemplo
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // No se utiliza en este ejemplo
    }

    // Resto de métodos de MouseMotionListener
    @Override
    public void mouseDragged1(MouseEvent e) {
        if (figuraSeleccionadaIndex != -1) {
            int dx = e.getX() - inicioArrastre.x;
            int dy = e.getY() - inicioArrastre.y;

            Shape figura = figuraActual.getShape();
            figura = AffineTransform.getTranslateInstance(dx, dy).createTransformedShape(figura);
            figuraActual = new Figura(figura, figuraActual.getColor());
            figuras.set(figuraSeleccionadaIndex, figuraActual);

            inicioArrastre = e.getPoint();
            repaint();
        }
    }
}

public class Transformaciones {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dibujar Figuras");
        DibujarPanel panel = new DibujarPanel();
        frame.add(panel);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

