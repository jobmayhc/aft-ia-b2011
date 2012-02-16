/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version1;

import javax.swing.JFrame;

public class AgentAFT{
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Mapa del Agent");
		
		GraphStatic agente = new GraphStatic();
		agente.load("./nodos.txt","./arcos.txt");
		agente.generate("mapa_2901x1192.jpg");
		
		Dijkstra solucion = new Dijkstra(agente.grafo);
		
		String origen = "C. C. Alto Chama";
		String destino = "Centro Ciudad";
		
    	GraphDinamic dinamico = new GraphDinamic(solucion.execute(origen,destino),agente.datos.getNodos());
    	dinamico.generate("mapa_2901x1192.jpg");
    	dinamico.start();

//   		frame.getContentPane().add(agente);
   		frame.getContentPane().add(dinamico);
    	
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.pack();
		frame.setVisible(true);		
	}
	
}