package proyecto2d.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import proyecto2d.controlador.Comandos;
import proyecto2d.modelo.Cuadrados;
import proyecto2d.modelo.ElipseContorno;
import proyecto2d.modelo.ElipseRellena;
import proyecto2d.modelo.Tablero;
import proyecto2d.modelo.Texto;
import tarea1.DibujoPanel;


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	
	
	private JPanel abajo;
	private JTabbedPane opciones;//AGREGAR PANELES SEGUN LO NECESITADO
	private JButton limpiar, guardar;
	private JLabel autor;
	
	private GridBagConstraints rest;
	
	//VARIABLES DE PANEL DE CUADRADOS
	
	
	public Ventana() {
		super("Proyecto graficación JAVA2D");
		
		//ESTILO DE LA VENTANA
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		rest= new GridBagConstraints();
		//PANEL DE LAS DIFERENTES OPCIONES
		opciones = new JTabbedPane();
		opciones.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"FUNCIONES",TitledBorder.CENTER,TitledBorder.TOP));
		
		//IR AGRAGEGANDO LAS PESTAÑAS CON LAS FUNCIONES CORRESPONDIENTES
		opciones.add(new Cuadrados(1).createPanelWithSlider(),"Espiral de cuadrados");
		opciones.add(new ElipseContorno().getPanel(), "Elipses en contorno");
		opciones.add(new ElipseRellena().getPanel(), "Elipses rellenas");
		opciones.add(new Texto().getPanel(), "Textos aleatorios");
		opciones.add(new Tablero(8,8).getPanel(), "Tablero de ajedrez");
		opciones.add(new DibujoPanel(), "Panel de dibujo");


		//CREAR PANELES
		//PANEL CENTRAL
		abajo = new JPanel();
		abajo.setLayout(new GridBagLayout());

		//CREAR ETIQUETAS
		autor = new JLabel("Creado por Ulises Becerril Valdés");
			
		//CREAR BOTON DE LIMPIAR
		limpiar = new JButton("Limpiar panel de trabajo");
		limpiar.setActionCommand(Comandos.LIMPIA);//CREAR Y CAMBIAR COMANDO
		limpiar.addActionListener(this);
		
		//CREAR BOTON DE GUARDAR
		guardar = new JButton("Guardar panel");
		guardar.setActionCommand(Comandos.GUARDAR);//CREAR Y CAMBIAR COMANDO
		guardar.addActionListener(this);
		
		//CREAR MENU TABBED PANE Y AGREAGAR PANELES
		
		//AGREAGAR LOS ELEMENTOS AL PANEL DE ABAJO
		rest.weightx =1.0;

		rest.fill= GridBagConstraints.CENTER;
		rest.gridx = 0;
		rest.gridy = 0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		abajo.add(limpiar, rest);
		
		rest.gridx = 1;
		rest.gridy = 0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		abajo.add(guardar,rest);
		
		rest.gridx = 2;
		rest.gridy = 0;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		abajo.add(autor, rest);
			
		
		//AGREGAR LOS PANELES A LA VENTANA
		this.add(opciones, BorderLayout.CENTER);
		this.add(abajo, BorderLayout.SOUTH);
	
		//this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//FIN CONSTRUCTOR DE LA VENTANA

@Override
public void actionPerformed(ActionEvent e) {
		
	switch (e.getActionCommand()) {//CASO DE LOS COMANDOS (BOTONES)
	case Comandos.CUADRADOS:

		break;		
		}//FIN SWITCH
	}//FIN ACTION

	
}//FIN CLASE VENTANA
