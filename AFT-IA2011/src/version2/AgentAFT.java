/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version2;

import jade.core.*;

@SuppressWarnings("serial")
public class AgentAFT extends Agent {

	public static Map MAP = new Map();
	public static AgentAFT AFT = new AgentAFT();
	public static Huristic BUS = new Huristic();
	public static Thread HILO;
	
	protected void setup() {
		System.out.println("Bienvenido... Me Llamo "+getLocalName());
	}
	
	@SuppressWarnings("deprecation")
	public void doWait() {
		System.out.println("Esperando");
		HILO.destroy();
	}
	public void doWake() {
		MapCell.fin = Huristic.actual;
		BUS.findRuta();
	}

	public void doSuspend() {
		System.out.println("Suspendido*");
	}
	public void doActivate() {
		System.out.println("Activado");
	}
	
	protected void takeDown() {
        System.out.println("Adios...");
        doDelete();
    }
}
