/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version1;

import java.util.List;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;

public class Dijkstra{

	private Graph<String, Arc> solucion;

	public Dijkstra( Graph<String,Arc> grafo ) {
		solucion = grafo;
	}
	
	public List<Arc> execute(String fuente,String destino) {
		DijkstraShortestPath<String, Arc> dijkstra = 
				new DijkstraShortestPath<String, Arc>(solucion, new CostoTiempo<Arc, Float>());
		return dijkstra.getPath(fuente,destino);
	}

}
