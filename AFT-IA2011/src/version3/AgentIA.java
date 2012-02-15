/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import comportamientos.*;

@SuppressWarnings("serial")
public class AgentIA extends jade.core.Agent {

	public static Map MAP = new Map();
	public static AgentIA AFT = new AgentIA();
	public static Huristic BUS = new Huristic();
	public static Thread HILO = new Thread();

	public void setup() {
		Application.main(null);
		System.out.println("Bienvenido... Soy el Agente "+getLocalName());
		/*
		Object [] entrada = getArguments();
		if ( entrada != null ) {
			String [] args = entrada[0].toString().split(":");
			if ( args != null && args.length == 3 ) {
				System.out.println("Origen: "+args[0]);
				System.out.println("Destino: "+args[1]);
				System.out.println("Gasolina: "+Integer.valueOf(args[2])+"lts.");
			}
			else {
				System.out.println("Error en la entrada de datos");
				doDelete();
			}
		}
		*/
	}
				
	protected void takeDown() {
		System.out.println("Adios...");
		doDelete();
		//		Cierra la interfaz del agente
		//		dispose();
	}


	public void doWait() {
		System.out.println("doWait");
		//		HILO.destroy();
	}

	public void doWake() {	
		Huristic.actual = MapCell.fin;
		addBehaviour(new goPath());
		/*
		MapCell.fin = Huristic.actual;
		BUS.findRuta();
		 */

	}
	
	


	public void doSuspend() {
//		AgentIA.BUS.findRuta();
		System.out.println("***doSuspend*");
	}


}
