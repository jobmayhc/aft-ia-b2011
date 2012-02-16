/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import jade.core.*;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

@SuppressWarnings("serial")
public class AgentIA extends Agent {

	public static Map MAP = new Map();
	public static boolean ruta;
	public static AgentIA AFT;
	public static boolean encendido, conduciendo;
	public static Behaviour compor;
	ThreadedBehaviourFactory hilo = new ThreadedBehaviourFactory();

	public void setup() {
		AFT = this;
		ruta = encendido = conduciendo = false;
		Application.main(null);
		System.out.println("Bienvenido... Soy el Agente "+getLocalName());
		Object [] entrada = getArguments();
		if ( entrada != null ) {
			String [] args = entrada[0].toString().split(":");
			if ( args != null && args.length == 3 ) {
				System.out.println("Origen: "+args[0]);
				System.out.println("Destino: "+args[1]);
				System.out.println("Gasolina: "+Integer.valueOf(args[2])+"lts.");
				Applet.gas.setValue(Integer.valueOf(args[2]));
			}
			else {
				System.out.println("Error en la entrada de datos");
				doDelete();
			}
		}
	}

	protected void takeDown() {
		System.out.println("Adios...  Moriii xD");
		doDelete();
	}

	public boolean path(){
		if( MapCell.inicio == null || MapCell.fin == null ){
			System.out.println("Error al definir Origen->Destino");
			return false;
		}
		return true;
	}

	public void on() {
		System.out.println(encendido?"Ya estaba encendido":"Vehiculo encendido");
		encendido = true;
	}

	public void off() {
		if( conduciendo ){
			System.out.println("Vehiculo en marcha, apagar primero");
			return;
		}
		if( Applet.gas.getValue() <= 0 ){
			Applet.gas.setValue(80);
			System.out.println("He recargado el tanque, no lo malgastes");
		}
		System.out.println(encendido?"Vehiculo apagado":"Ya estaba apagado");
		encendido = false;
	}

	public void drive() {
		if( !encendido ){
			System.out.println("Los vehiculos apagados no se mueven, enciendelo primero =)...");
			return;
		}else
			if( conduciendo ){
				System.out.println("Estoy manejando no molestes =@");
				return;
			}
		if( !path() )	return;
		MapCell.reset(true);
		ruta = false;
		addBehaviour(new _buscar());
		if( !ruta ){
			conduciendo = true;
			System.out.println("Iniciar manejo");
			addBehaviour(compor = new _ir());
		}
	}

	public void stop() {
		System.out.println(conduciendo||!encendido?"Vehiculo detenido":"El vehiculo estaba detenido");
		conduciendo = false;
		if( MapCell.fin == MapCell.actual )
			MapCell.reset(false);
	}
	
	public void doWake(){
		drive();
	}
	
	public void doSuspend(){
		stop();
	}

}
