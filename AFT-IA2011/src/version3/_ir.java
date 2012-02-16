/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import jade.core.behaviours.*;

@SuppressWarnings("serial")
public class _ir extends Behaviour {
	
	public void action() {
		System.out.println("_ir");
		MapCell.actual = MapCell.fin;
		while( true ){
			MapCell proximo = AgentIA.MAP.getMejorVecino(MapCell.actual);
			if( proximo.equals(MapCell.inicio) ) {
				System.out.println("Llegue al Destino");
				break;
			}else
				if( MapCell.actual.equals(AgentIA.MAP.getMejorVecino(proximo)) ) {
					System.out.println("Soy mi mejor vecino, hay un obstaculo");
					MapCell.fin = MapCell.actual;
					myAgent.addBehaviour(new _buscar());
					myAgent.addBehaviour(new _ir());
					break;
				}
			MapCell.actual = proximo;
			MapCell.actual.parteRuta = true;
			MapCell.actual.repaint();
			myAgent.doWait((int)MapCell.actual.costo*1000);
		}
	}

	public boolean done() {
		myAgent.removeBehaviour(this);
		System.out.println("Comportamiento _setRuta finalizado");
		return true;
	}            
}