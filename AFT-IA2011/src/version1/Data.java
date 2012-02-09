/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version1;

import java.io.*;
import java.util.List;
import java.awt.geom.Point2D;

/*	DEFINE Y MANIPULA LOS CONTENEDORES DONDE SE ALMACENAN 
 	TODOS LOS NODOS Y ARCOS CON SUS RESPECTIVOS ATRIBUTOS	*/
public class Data {

	protected List<Node> nodos;	//	LISTA DE TODOS LOS NODOS
	protected List<Arc> arcos;		//	LISTA DE TODOS LOS ARCOS

	/*	CONSTRUCTOR PARAMETRICO */
	public Data(List<Node> nodos, List<Arc> arcos) {
		this.nodos = nodos;
		this.arcos = arcos;
	}
	
	/*	OBTIENE LA LISTA COMPLETA DE NODOS	*/
	public List<Node> getNodos() {	return nodos;	}

	/*	OBTIENE LA LISTA COMPLETA DE ARCOS	*/
	public List<Arc> getArcos() {	return arcos;	}

	/*	CARGA TODOS LOS NODOS DESDE UN FICHERO	*/
	public void loadNodos(String fichero){
		try {
			FileReader file = new FileReader(fichero);
			BufferedReader buffer = new BufferedReader(file);
			String linea;		
			while ( (linea = buffer.readLine()) != null ) {
				String [] campo = linea.split("\t");
				Node temp = new Node( campo[0],	
						new Point2D.Float(Float.parseFloat(campo[1]), Float.parseFloat(campo[2]))
				);
				if( !this.nodos.contains(temp) ){
					this.nodos.add(temp);
				}
			} 			 		
		}catch (IOException e){}
	}

	/*	CARGA TODOS LOS ARCOS DESDE UN FICHERO	*/
	public void loadArcos(String fichero){
		try {
			FileReader file = new FileReader(fichero);
			BufferedReader buffer = new BufferedReader(file);
			String linea;
			while ( (linea = buffer.readLine()) != null ) {			
				String [] campo = linea.split("\t");
				Arc temp = new Arc( campo[0], campo[1],campo[2],
						Float.parseFloat(campo[3]),
						Float.parseFloat(campo[4]),
						Float.parseFloat(campo[5])
						);
				if( !this.arcos.contains(temp) ){
					this.arcos.add(temp);
				}
			} 	 
		}catch (IOException e){}

	}

}