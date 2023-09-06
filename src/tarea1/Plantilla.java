package tarea1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class Plantilla extends JApplet implements ActionListener {

	/*
	 * METODO PRINCIPAL
	 * */
	public static void main(String s[]) {
	    JFrame frame = new JFrame();
	    frame.setTitle("XXPLANTILLAXX");//CAMBIAR TITULO
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JApplet applet = new Plantilla();//CAMBIAR CLASE
	    applet.init();
	    frame.getContentPane().add(applet);
	    frame.pack();
	    frame.setVisible(true);
	  }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
