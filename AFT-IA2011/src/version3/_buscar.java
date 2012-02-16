/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import java.awt.*;
import java.util.*;

import jade.core.behaviours.*;

@SuppressWarnings("serial")
public class _buscar extends OneShotBehaviour{
	
	public static Vector<MapCell> copy = new Vector<MapCell>();
	
	public void action(){
		Vector<MapCell> pendientes = new Vector<MapCell>();
		Vector<MapCell> revisados = new Vector<MapCell>();
		pendientes.addElement(MapCell.fin);
		for( int pass = 0; !AgentIA.ruta && pass < Integer.MAX_VALUE ; pass++ ){		
			double distancia, minima = Double.MAX_VALUE;
			MapCell actual, mejor = pendientes.elementAt(pendientes.size()-1);	
			// Recorro mi lista de pendientes
			for( int i = 0 ; i < pendientes.size() ; i++ ){
				// Selecciono el siguiente en lista
				actual = pendientes.elementAt(i);
				// Verifico no este en los revisados
				if( !revisados.contains(actual) ){
					// Distancia hasta el inicio
					distancia = actual.getDistanciaFin();
					// Distancia restante hasta el final
					distancia += distanceCruzada(actual.posicion,MapCell.inicio.posicion);					
					// Modifico mi menor distancia y mejor nodo
					if( distancia < minima ){minima = distancia;	mejor = actual;}
				}
			}
			actual = mejor;
			// Saco mi mejor elemento de los pendientes para examinarlo
			if ( pendientes.removeElement(actual) )
				// Lo agrego a examinados
				revisados.addElement(actual);
			copy.addElement(actual);			
			// Obtengo los vecinos de mi actual
			MapCell next[] = AgentIA.MAP.getVecinos(actual);
			for( int i = 0 ; i < Global.DIRECCION ; i++ ){
				// Me aseguro no sea nulo o un obstaculo total
				if( next[i] != null && next[i].costo != Global.TOTAL ){
					// Agrega el nodo a la posible ruta y lo activa como usado
					next[i].addRuta(actual.getDistanciaFin());
					// Verifico no sea el ultimo nodo
					if( next[i] == MapCell.inicio )
						AgentIA.ruta = true;
					// Verifico si es un nodo nuevo a fin de agregarlo a la lista de pendientes en caso de serlo
					else if( !pendientes.contains(next[i]) && !revisados.contains(next[i]) )
						pendientes.addElement(next[i]);					
				}
			}
			// Pinto el mapa con el nodo
			AgentIA.MAP.repaint();
			if ( AgentIA.ruta ) break;
		}
		if( !AgentIA.ruta )
			System.out.println("No hay camino entre Origen->Destino");
	}
				
	public double distanceCruzada(Point actual,Point destino){
		Point origen = MapCell.fin.posicion;
		double cruz = Math.abs( Math.abs((actual.x - destino.x)*(origen.y - destino.y)) - Math.abs((origen.x - destino.x)*(actual.y - destino.y)));
		double rect = Math.abs(actual.x - destino.x) + Math.abs(actual.y - destino.y);
		return rect + cruz*0.002;
	}
	
}
