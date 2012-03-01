/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version4;

import jade.core.behaviours.*;

@SuppressWarnings("serial")
public class _ir extends OneShotBehaviour {

	public void action() {
		AgentIA.run = true;
		MapCell.actual = MapCell.inicio;
		while( AgentIA.run && Applet.gas.getValue() > 0 ){
			MapCell proximo = AgentIA.map.getMejorVecino(MapCell.actual);
			if( proximo.equals(MapCell.fin) ) {
				System.out.println("Llegue al Destino");
				AgentIA.AFT.stop();
				MapCell.reset(false);
				break;
			}else
				if( MapCell.actual.equals(AgentIA.map.getMejorVecino(proximo)) ) {
					System.out.println("Obstaculo!!!");
					MapCell.inicio = MapCell.actual;
					AgentIA.AFT.stop();
					AgentIA.AFT.drive();
					break;
				}else
					if( proximo.costo > _buscar.COPIA[proximo.posicion.x][proximo.posicion.y] + 4 ){
						System.out.println("CAMBIO...!!");
						MapCell.inicio = MapCell.actual;
						AgentIA.AFT.stop();
						AgentIA.AFT.drive();
						break;
					}

			MapCell.actual = proximo;
			MapCell.actual.parteRuta = true;
			MapCell.actual.repaint();

			myAgent.doWait((int)MapCell.actual.costo*1000);
/*
			Applet.gas.setValue(Applet.gas.getValue()-(int)MapCell.actual.costo);
			if( Applet.gas.getValue() == 0 ){
				MapCell.inicio = MapCell.actual;
				AgentIA.AFT.stop();
				myAgent.doWait(3000);
				System.out.println("Me he quedado sin gasolina =.(");
				AgentIA.end = false;
				break;
			}
*/
		}
	}

}