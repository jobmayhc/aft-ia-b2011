/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import jade.core.behaviours.*;

@SuppressWarnings("serial")
public class _ir extends OneShotBehaviour {

	public void action() {
		MapCell.actual = MapCell.inicio;
		while( AgentIA.conduciendo && Applet.gas.getValue() > 0 ){
			MapCell proximo = AgentIA.MAP.getMejorVecino(MapCell.actual);
			if( _buscar.copy.indexOf(proximo) > 0 && proximo.costo > _buscar.copy.elementAt(_buscar.copy.indexOf(proximo)).costo){
				System.out.println("Hubo un cambio en la ruta");
				MapCell.inicio = MapCell.actual;
				AgentIA.AFT.stop();
				AgentIA.AFT.drive();
				break;
			}else
				if( MapCell.actual.equals(AgentIA.MAP.getMejorVecino(proximo)) ) {
					System.out.println("Soy mi mejor vecino, hay un obstaculo");
					MapCell.inicio = MapCell.actual;
					AgentIA.AFT.stop();
					AgentIA.AFT.drive();
					break;
				}else
					if( proximo.equals(MapCell.fin) ) {
						System.out.println("Llegue al Destino");
						AgentIA.AFT.stop();
						MapCell.reset(false);
						break;
					}
			MapCell.actual = proximo;
			MapCell.actual.parteRuta = true;
			MapCell.actual.repaint();
			myAgent.doWait((int)MapCell.actual.costo*1000);
			Applet.gas.setValue(Applet.gas.getValue()-(int)MapCell.actual.costo);
			if( Applet.gas.getValue() == 0 ){
				MapCell.inicio = MapCell.actual;
				AgentIA.AFT.stop();
				myAgent.doWait(3000);
				System.out.println("Me he quedado sin gasolina =.(");
				AgentIA.ruta = false;
				break;
			}
		}
	}

}