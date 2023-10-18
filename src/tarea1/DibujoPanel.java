package tarea1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@SuppressWarnings("serial")
public class DibujoPanel extends JPanel {
    private Color colorDibujo = Color.BLACK;
    private Figura figuraActual = Figura.Cuadrado;
    private int startX, startY, endX, endY;
    private List<FiguraDibujada> figurasDibujadas = new ArrayList<>();
    private int grosorLinea = 1;
    private FiguraDibujada figuraTemporal;
    private ColorPanel colorPanel;
    private JButton deshacerButton;
    private JButton rehacerButton;
    private Stack<FiguraDibujada> undoStack;
    private Stack<FiguraDibujada> redoStack;

    public DibujoPanel() {
        setLayout(new BorderLayout());

        // Panel de Dibujo
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja las figuras
                for (FiguraDibujada figura : figurasDibujadas) {
                    g.setColor(figura.getColor());
                    ((Graphics2D) g).setStroke(new BasicStroke(figura.getGrosorLinea()));
                    int width = Math.abs(figura.getEndX() - figura.getStartX());
                    int height = Math.abs(figura.getEndY() - figura.getStartY());
                    int x = Math.min(figura.getStartX(), figura.getEndX());
                    int y = Math.min(figura.getStartY(), figura.getEndY());
                    switch (figura.getTipo()) {
                        case Cuadrado:
                            g.drawRect(x, y, width, height);
                            break;
                        case Circulo:
                            g.drawOval(x, y, width, height);
                            break;
                        case Linea:
                            g.drawLine(figura.getStartX(), figura.getStartY(), figura.getEndX(), figura.getEndY());
                            break;
                        case Elipse:
                            g.drawOval(x, y, width, height);
                            break;
                        case Rectangulo:
                            g.drawRect(x, y, width, height);
                            break;
                    }
                }
                // Dibuja la figura temporal
                if (figuraTemporal != null) {
                    g.setColor(figuraTemporal.getColor());
                    ((Graphics2D) g).setStroke(new BasicStroke(figuraTemporal.getGrosorLinea()));
                    int width = Math.abs(figuraTemporal.getEndX() - figuraTemporal.getStartX());
                    int height = Math.abs(figuraTemporal.getEndY() - figuraTemporal.getStartY());
                    int x = Math.min(figuraTemporal.getStartX(), figuraTemporal.getEndX());
                    int y = Math.min(figuraTemporal.getStartY(), figuraTemporal.getEndY());
                    switch (figuraTemporal.getTipo()) {
                        case Cuadrado:
                            g.drawRect(x, y, width, height);
                            break;
                        case Circulo:
                            g.drawOval(x, y, width, height);
                            break;
                        case Linea:
                            g.drawLine(figuraTemporal.getStartX(), figuraTemporal.getStartY(), figuraTemporal.getEndX(), figuraTemporal.getEndY());
                            break;
                        case Elipse:
                            g.drawOval(x, y, width, height);
                            break;
                        case Rectangulo:
                            g.drawRect(x, y, width, height);
                            break;
                    }
                }
            }
        };
        drawingPanel.setBackground(Color.WHITE);
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                figuraTemporal = new FiguraDibujada(figuraActual, startX, startY, startX, startY, colorDibujo, grosorLinea);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                figuraTemporal.setEndX(endX);
                figuraTemporal.setEndY(endY);
                figurasDibujadas.add(figuraTemporal);
                figuraTemporal = null;
                undoStack.push(figurasDibujadas.get(figurasDibujadas.size() - 1));
                redoStack.clear();
                repaint();
                deshacerButton.setEnabled(true);
                rehacerButton.setEnabled(false);
            }
        });
        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                figuraTemporal.setEndX(endX);
                figuraTemporal.setEndY(endY);
                repaint();
            }
        });

        // Panel de Control
        JPanel controlPanel = new JPanel();
        JComboBox<Figura> figuraComboBox = new JComboBox<>(Figura.values());
        figuraComboBox.addActionListener(e -> figuraActual = (Figura) figuraComboBox.getSelectedItem());
        controlPanel.add(new JLabel("Figura: "));
        controlPanel.add(figuraComboBox);

        JSlider grosorSlider = new JSlider(1, 10, 1);
        grosorSlider.addChangeListener(e -> grosorLinea = grosorSlider.getValue());
        controlPanel.add(new JLabel("Grosor: "));
        controlPanel.add(grosorSlider);

        JButton colorButton = new JButton("Seleccionar Color");
        colorButton.addActionListener(e -> {
            colorDibujo = JColorChooser.showDialog(DibujoPanel.this, "Seleccionar Color", colorDibujo);
            colorPanel.repaint();
        });
        controlPanel.add(colorButton);

        // Color Indicator
        colorPanel = new ColorPanel(colorDibujo);

        // Deshacer y Rehacer
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        deshacerButton = new JButton("Deshacer");
        deshacerButton.setEnabled(false);
        deshacerButton.addActionListener(e -> {
            if (!undoStack.isEmpty()) {
                redoStack.push(undoStack.pop());
                if (undoStack.isEmpty()) {
                    deshacerButton.setEnabled(false);
                }
                rehacerButton.setEnabled(true);
                figurasDibujadas.clear();
                figurasDibujadas.addAll(undoStack);
                repaint();
            }
        });
        controlPanel.add(deshacerButton);

        rehacerButton = new JButton("Rehacer");
        rehacerButton.setEnabled(false);
        rehacerButton.addActionListener(e -> {
            if (!redoStack.isEmpty()) {
                undoStack.push(redoStack.pop());
                if (redoStack.isEmpty()) {
                    rehacerButton.setEnabled(false);
                }
                deshacerButton.setEnabled(true);
                figurasDibujadas.clear();
                figurasDibujadas.addAll(undoStack);
                repaint();
            }
        });
        controlPanel.add(rehacerButton);

        // Agregar componentes al panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(drawingPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(colorPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}

@SuppressWarnings("serial")
class ColorPanel extends JPanel {
    private Color color;

    public ColorPanel(Color color) {
        this.color = color;
        setPreferredSize(new Dimension(30, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

class FiguraDibujada {
    private Figura tipo;
    private int startX, startY, endX, endY;
    private Color color;
    private int grosorLinea;

    public FiguraDibujada(Figura tipo, int startX, int startY, int endX, int endY, Color color, int grosorLinea) {
        this.tipo = tipo;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
        this.grosorLinea = grosorLinea;
    }

    public Figura getTipo() {
        return tipo;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public Color getColor() {
        return color;
    }

    public int getGrosorLinea() {
        return grosorLinea;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
}

enum Figura {
    Cuadrado,
    Circulo,
    Linea,
    Elipse,
    Rectangulo
}
