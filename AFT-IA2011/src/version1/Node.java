/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version1;

import java.awt.geom.Point2D;

/*	DEFINE Y MANIPULA LOS DATOS DEL NODO	*/
public class Node {
	
	final private String id;		//	NOMBRE DEL NODO
	final private Point2D ubicacion;//	COORDENADAS DEL NODO
	
	public Node(String id) {
		this.id = id;
		this.ubicacion = null;
	}
	
	/*	CONSTRUCTOR PARAMETRICO	*/
	public Node(String id,Point2D xy) {
		this.id = id;
		this.ubicacion = xy;
	}
	

	/*	OBTIENE EL NOMBRE DEL NODO	*/
	public String getId() {
		return id;
	}
	
	/*	OBTIENE LAS CORRDENADAS DEL NODO	*/
	public Point2D getUbicacion() {
		return ubicacion;
	}
	
	/*	COMPARA IGUALDAD ENTRE DOS NODOS	*/
	public boolean equals(Object obj) {
		Node other = (Node) obj;
		if ( this.getId().equals(other.getId()) )
			return true;
		return false;
	}
		
}