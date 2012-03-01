/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version4;

import jade.core.*;
import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class AgentIA extends Agent {

	public static Map map = new Map();
	public static boolean end;
	public static AgentIA AFT;
	public static boolean on, run;
	public static Behaviour beha;
	public static Record log;

	public void setup() {
		AFT = this;
		end = on = run = false;
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
	}

	public boolean path(){
		if( MapCell.inicio != null && MapCell.fin != null )	return true;
		System.out.println("No existe:" + (MapCell.inicio==null?" Origen":"") + (MapCell.fin==null?" Destino":""));
		return false;	
	}

	public void on(){
		System.out.println(!AgentIA.on?"Encender":"Estaba encendido");
		AgentIA.on = true;
	}

	public void off(){
		System.out.println(AgentIA.run?"Vehiculo no esta detenido":AgentIA.on?"Apagar":"Estaba apagado");
		if( AgentIA.run )	return;
		AgentIA.on = false;
	}

	public void find(){
		MapCell.reset(true);
		AgentIA.AFT.addBehaviour(AgentIA.beha = new _buscar());
		if( !end )	addBehaviour(beha = new _ir());
	}

	public void drive(){
		if( !path() )	return;
		System.out.println(!AgentIA.on?"Vehiculo no esta encendido":!AgentIA.run?"Manejar":"Estaba manejando");
		if( AgentIA.on  && !AgentIA.run )	find();
		
	}

	public void stop(){
		System.out.println(AgentIA.run?"Detener":"Estaba detenido");
		AgentIA.run = false;
		AgentIA.end = MapCell.actual.equals(MapCell.fin)?true:false;
	}
}
