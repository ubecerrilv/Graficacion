package proyecto2d.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OpBooleanas extends JPanel {
    private Color colorDibujo = Color.BLACK;
    private Figura figuraActual = Figura.CUADRADO;
    private int startX, startY, endX, endY;
    private List<FiguraDibujada> figurasDibujadas = new ArrayList<>();
    private int grosorLinea = 1;
    private FiguraDibujada figuraTemporal;
    private ColorPanel colorPanel;
    private JButton deshacerButton;
    private JButton rehacerButton;
    private Stack<List<FiguraDibujada>> undoStack;
    private Stack<List<FiguraDibujada>> redoStack;
    private List<FiguraDibujada> figurasSeleccionadas = new ArrayList<>();
    private boolean dragging = false;

    public OpBooleanas() {
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
                        case CUADRADO:
                            g.drawRect(x, y, width, height);
                            break;
                        case CIRCULO:
                            g.drawOval(x, y, width, height);
                            break;
                        case LINEA:
                            g.drawLine(figura.getStartX(), figura.getStartY(), figura.getEndX(), figura.getEndY());
                            break;
                        case ELIPSE:
                            g.drawOval(x, y, width, height);
                            break;
                        case RECTANGULO:
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
                        case CUADRADO:
                            g.drawRect(x, y, width, height);
                            break;
                        case CIRCULO:
                            g.drawOval(x, y, width, height);
                            break;
                        case LINEA:
                            g.drawLine(figuraTemporal.getStartX(), figuraTemporal.getStartY(), figuraTemporal.getEndX(), figuraTemporal.getEndY());
                            break;
                        case ELIPSE:
                            g.drawOval(x, y, width, height);
                            break;
                        case RECTANGULO:
                            g.drawRect(x, y, width, height);
                            break;
                    }
                }
                // Dibuja un borde alrededor de las figuras seleccionadas
                for (FiguraDibujada figura : figurasSeleccionadas) {
                    g.setColor(Color.BLUE);
                    ((Graphics2D) g).setStroke(new BasicStroke(2));
                    int width = Math.abs(figura.getEndX() - figura.getStartX());
                    int height = Math.abs(figura.getEndY() - figura.getStartY());
                    int x = Math.min(figura.getStartX(), figura.getEndX());
                    int y = Math.min(figura.getStartY(), figura.getEndY());
                    g.drawRect(x, y, width, height);
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
                dragging = true;
                figuraTemporal = new FiguraDibujada(figuraActual, startX, startY, startX, startY, colorDibujo, grosorLinea);
                
                // Verifica si se hizo clic en una figura existente para seleccionarla
                FiguraDibujada figuraSeleccionada = getClickedFigure(startX, startY);
                if (figuraSeleccionada != null) {
                    if (!figurasSeleccionadas.contains(figuraSeleccionada)) {
                        figurasSeleccionadas.add(figuraSeleccionada);
                    } else {
                        figurasSeleccionadas.remove(figuraSeleccionada);
                    }
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                figuraTemporal.setEndX(endX);
                figuraTemporal.setEndY(endY);
                figurasDibujadas.add(figuraTemporal);
                figuraTemporal = null;
                dragging = false;
                undoStack.push(new ArrayList<>(figurasDibujadas));
                redoStack.clear();
                repaint();
                deshacerButton.setEnabled(true);
                rehacerButton.setEnabled(false);
            }
        });
        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    endX = e.getX();
                    endY = e.getY();
                    figuraTemporal.setEndX(endX);
                    figuraTemporal.setEndY(endY);
                    repaint();
                }
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
            colorDibujo = JColorChooser.showDialog(OpBooleanas.this, "Seleccionar Color", colorDibujo);
            colorPanel.repaint();
        });
        controlPanel.add(colorButton);

        // Color Indicator
        colorPanel = new ColorPanel(colorDibujo);

        // Operaciones booleanas
        JButton interseccionButton = new JButton("Intersección");
        interseccionButton.addActionListener(e -> {
            realizarOperacion(BooleanOperation.INTERSECCION);
        });
        controlPanel.add(interseccionButton);

        JButton disyuncionButton = new JButton("Disyunción");
        disyuncionButton.addActionListener(e -> {
            realizarOperacion(BooleanOperation.DISYUNCION);
        });
        controlPanel.add(disyuncionButton);

        // Deshacer y Rehacer
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        deshacerButton = new JButton("Deshacer");
        deshacerButton.setEnabled(false);
        deshacerButton.addActionListener(e -> {
            if (!undoStack.isEmpty()) {
                redoStack.push(new ArrayList<>(figurasDibujadas));
                if (undoStack.isEmpty()) {
                    deshacerButton.setEnabled(false);
                }
                rehacerButton.setEnabled(true);
                figurasDibujadas.clear();
                figurasDibujadas.addAll(undoStack.pop());
                repaint();
            }
        });
        controlPanel.add(deshacerButton);

        rehacerButton = new JButton("Rehacer");
        rehacerButton.setEnabled(false);
        rehacerButton.addActionListener(e -> {
            if (!redoStack.isEmpty()) {
                undoStack.push(new ArrayList<>(figurasDibujadas));
                if (redoStack.isEmpty()) {
                    rehacerButton.setEnabled(false);
                }
                deshacerButton.setEnabled(true);
                figurasDibujadas.clear();
                figurasDibujadas.addAll(redoStack.pop());
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

    private FiguraDibujada getClickedFigure(int x, int y) {
        for (FiguraDibujada figura : figurasDibujadas) {
            int minX = Math.min(figura.getStartX(), figura.getEndX());
            int maxX = Math.max(figura.getStartX(), figura.getEndX());
            int minY = Math.min(figura.getStartY(), figura.getEndY());
            int maxY = Math.max(figura.getStartY(), figura.getEndY());
            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                return figura;
            }
        }
        return null;
    }

    private void realizarOperacion(BooleanOperation operacion) {
        if (figurasSeleccionadas.size() >= 2) {
            FiguraDibujada resultado = operacion.calcular(figurasSeleccionadas);
            if (resultado != null) {
                figurasDibujadas.add(resultado);
                undoStack.push(new ArrayList<>(figurasDibujadas));
                redoStack.clear();
                repaint();
                deshacerButton.setEnabled(true);
                rehacerButton.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dibujar Figuras");
            OpBooleanas panel = new OpBooleanas();
            frame.add(panel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

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

enum Figura {
    CUADRADO,
    CIRCULO,
    LINEA,
    ELIPSE,
    RECTANGULO
}

enum BooleanOperation {
    INTERSECCION {
        @Override
        FiguraDibujada calcular(List<FiguraDibujada> figuras) {
            // Lógica para calcular la intersección
            // Devuelve la intersección de las figuras seleccionadas
            // Aquí se debe implementar la lógica adecuada para la intersección
            return null;
        }
    },
    DISYUNCION {
        @Override
        FiguraDibujada calcular(List<FiguraDibujada> figuras) {
            // Lógica para calcular la unión/disyunción
            // Devuelve la unión/disyunción de las figuras seleccionadas
            // Aquí se debe implementar la lógica adecuada para la unión/disyunción
            return null;
        }
    };

    abstract FiguraDibujada calcular(List<FiguraDibujada> figuras);
}

class FiguraDibujada {
    private OpBooleanas.Figura tipo;
    private int startX, startY, endX, endY;
    private Color color;
    private int grosorLinea;

    public FiguraDibujada(OpBooleanas.Figura tipo, int startX, int startY, int endX, int endY, Color color, int grosorLinea) {
        this.tipo = tipo;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
        this.grosorLinea = grosorLinea;
    }

    public OpBooleanas.Figura getTipo() {
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
