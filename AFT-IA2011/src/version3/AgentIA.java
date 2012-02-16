/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

@SuppressWarnings("serial")
public class AgentIA extends jade.core.Agent {
	
	public static Map MAP = new Map();
	public static boolean ruta;
	public static AgentIA AFT;
	public static boolean encendido, conduciendo;
		
	public void setup() {
		AFT = this;
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
		System.out.println("Adios...  Moriii xD");
		doDelete();
	}
	
	public boolean init(){
		if( MapCell.inicio == null || MapCell.fin == null ){
			System.out.println("Error al definir Origen->Destino");
			return false;
		}
		ruta = encendido = conduciendo = false;
		MapCell.reset();
		return true;
	}

	public void on() {
		if( !encendido ) {
			encendido = true;
			System.out.println("Encender vehiculo");
		}else	System.out.println("Ya vehiculo");
	}
	
	public void of() {
		if( encendido ) {
			encendido = true;
			System.out.println("Apagar vehiculo");
		}else	System.out.println("Ya esta apagado");
	}
	
	public void drive() {
		System.out.println("Iniciar manejo");
		doWake();
	}
	
	public void stop() {
		System.out.println("Detenerr vehiculo");
		doSuspend();
	}
	
	public void doWake() {
		if( ruta )
		addBehaviour(new _ir());
	}
	
	public void doSuspend() {
		if( !init() )	return;
		addBehaviour(new _buscar());
	}
	

	

}
