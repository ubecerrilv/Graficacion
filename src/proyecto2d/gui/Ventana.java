package proyecto2d.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	
	
	JPanel panelCent;
	JPanel panel;
	JLabel paginas;
	JTextArea pag;
	JButton bBuscar;
	JScrollPane sP;
	
	GridBagConstraints rest;
	
	
	public Ventana() {
		super("Proyecto graficación JAVA2D");
		
		//ESTILO DE LA VENTANA
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		rest= new GridBagConstraints();
		
		//PANEL CENTRAL
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
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
		panelCent = new JPanel(new GridLayout(1,2));
		panelCent.setBorder(new EmptyBorder(5,5,5,5));
		panelCent.setBackground(Color.BLACK);

		//CREAR ETIQUETAS
		paginas = new JLabel("PÁGINAS:");
		
		//CREAR TEXTAREAS
		pag = new JTextArea("");
			
		//CREAR BOTON DE MAPEAR
		bBuscar = new JButton("Mapear página");
		bBuscar.setActionCommand(proyecto2d.controlador.Comandos.INICIA);//CREAR Y CAMBIAR COMANDO
		bBuscar.addActionListener(this);
		
		//CREAR SCROLLS
		//sP = new JScrollPane(pag, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	      //      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//CAMBIAR EL SETEO DEL TEXT AREA
		
		
		//AGREAGAR LOS ELEMENTOS A LOS PANELES
		//PANEL CENTRAL
			//panelCent.add(sP);
		
		//AGREGAR LOS PANELES A LA VENTANA
		this.add(panelCent, BorderLayout.CENTER);
		
	
		//this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//FIN CONSTRUCTOR DE LA VENTANA

@Override
public void actionPerformed(ActionEvent e) {
		
	switch (e.getActionCommand()) {//CASO DE LOS COMANDOS (BOTONES)
	case "":
		
		break;		
		}//FIN SWITCH
	}//FIN ACTION
	
}//FIN CLASE VENTANA
