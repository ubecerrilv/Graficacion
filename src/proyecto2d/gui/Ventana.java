package proyecto2d.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import proyecto2d.controlador.Comandos;
import proyecto2d.modelo.Cuadrados;


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	
	
	private JPanel principal, plano, opciones;//AGREGAR PANELES SEGUN LO NECESITADO
	private JButton limpiar, guardar, cuadradosBtn;
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
		opciones = new JPanel(new GridLayout(10,1));
		opciones.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"FUNCIONES",TitledBorder.CENTER,TitledBorder.TOP));
		
		//IR AGRAGEGANDO LOS BOTONES PARA LAS FUNCIONES
		cuadradosBtn = new JButton("Espiral de cuadrados");
		cuadradosBtn.setActionCommand(Comandos.CUADRADOS);
		cuadradosBtn.addActionListener(this);
		opciones.add(cuadradosBtn);
		
		
		
		//AGREGAR LOS PANELES Y LAS FUNCIONALIDADES
		/*
		 * EJEMPLO DE COMO USAR LAS RESTRICCIONES DEL LAYOUT
		 * rest.gridx = 1;
		rest.gridy = 4;
		rest.weightx =1.0;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		
		panel.add(autor, rest);
		rest.weightx =0
		 * 
		 * 
		 *  * */
		
		
		//CREAR PANELES
		//PANEL CENTRAL
		principal = new JPanel();
		principal.setLayout(new GridBagLayout());
		
		//CREAR PANEL DE DIBUJO
		plano = new JPanel();
		
		
		
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
		
		//AGREAGAR LOS ELEMENTOS AL PANEL PRICIPAL
		rest.fill= GridBagConstraints.BOTH;
		rest.weightx =1.0;
		rest.gridx = 2;
		rest.gridy = 0;
		rest.weighty =1.0;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		principal.add(new Cuadrados(1).createPanelWithSlider(), rest);
		
		rest.weightx =1.0;
		rest.gridx = 0;
		rest.gridy = 0;
		rest.weighty =1.0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		principal.add(opciones, rest);
		
		rest.weighty =0;
			
		rest.fill= GridBagConstraints.CENTER;
		rest.gridx = 2;
		rest.gridy = 1;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		principal.add(autor, rest);
			
		rest.gridx = 0;
		rest.gridy = 1;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		principal.add(limpiar, rest);
			
		rest.gridx = 1;
		rest.gridy = 1;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		principal.add(guardar,rest);
		
		//AGREGAR LOS PANELES A LA VENTANA
		this.add(principal);
	
		//this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//FIN CONSTRUCTOR DE LA VENTANA

@Override
public void actionPerformed(ActionEvent e) {
		
	switch (e.getActionCommand()) {//CASO DE LOS COMANDOS (BOTONES)
	case Comandos.CUADRADOS:
		/*Cuadrados x = new Cuadrados(1);
		cambia(x.createPanelWithSlider());
		this.repaint();*/
		break;		
		}//FIN SWITCH
	}//FIN ACTION


public void cambia (JPanel nuevo) {
	principal.remove(0);
	rest.fill= GridBagConstraints.BOTH;
	rest.weightx =1.0;
	rest.gridx = 2;
	rest.gridy = 0;
	rest.weighty =1.0;
	rest.gridwidth = 2;
	rest.gridheight = 1;
	principal.add(nuevo, rest);
	principal.repaint();
	
}
	
}//FIN CLASE VENTANA
