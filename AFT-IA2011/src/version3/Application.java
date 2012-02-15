/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Application extends JFrame {

	public Application() {
		setTitle("AFT \"Agente Viajero Rapido\" - Inteligencia Artificial (B2011) ULA,VE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(640,480));
	}
	
	public static void main(String[] args) {
		Application exe = new Application();
		Applet app = new Applet();
		exe.add(app);
		app.init();
		exe.setVisible(true);
	}
}