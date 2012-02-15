package version3;

import java.util.Vector;

import jade.core.behaviours.*;

@SuppressWarnings("serial")
public class findPath extends Behaviour{
static boolean bandera = false; 
	
	@SuppressWarnings("deprecation")
	public void action(){
		
		boolean found = false;
		double minima = Double.MAX_VALUE;
		double distancia;
		MapCell actual, mejor = Huristic.pendientes.elementAt(Huristic.pendientes.size()-1);
		
		// Recorro mi lista de pendientes
		for( int i = 0 ; i < Huristic.pendientes.size() ; i++ ){
			// Selecciono el siguiente en lista
			actual = Huristic.pendientes.elementAt(i);
			// Verifico no este en los exmainados
			if( !Huristic.examinados.contains(actual) ){
				// Distancia hasta el inicio
				distancia = actual.getDistanciaInicio();
				// Distancia restante hasta el final
				distancia += Huristic.findDistancia(actual.posicion,MapCell.fin.posicion,Huristic.costoMin);
				// Modifico mi menor distancia y mejor nodo
				if( distancia < minima ){minima = distancia;	mejor = actual;}
			}
		}
		actual = mejor;
		// Saco mi mejor elemento de los pendientes para examinarlo
		if ( Huristic.pendientes.removeElement(actual) )
			// Lo agrego a examinados
			Huristic.examinados.addElement(actual);
		// Obtengo los vecinos de mi actual
		MapCell next[] = AgentIA.MAP.getVecinos(actual);
		for( int i = 0 ; i < Global.DIRECCION ; i++ ){
			// Me aseguro no sea nulo el de la direccion correspondiente
			if( next[i] != null ){
				if( next[i] == MapCell.fin )	found = true;
				if( !next[i].isTotal() ){
					next[i].addRutaInicio(actual.getDistanciaInicio());
					if( !Huristic.pendientes.contains(next[i]) && !Huristic.examinados.contains(next[i]) )
						Huristic.pendientes.addElement(next[i]);
				}
			}if(found)	return Global.FOUND;
		}
		AgentIA.MAP.repaint();
		return Huristic.pendientes.size()==0?Global.NO_PATH:Global.NOT_FOUND;
	}
	
	public boolean done(){
		return bandera;
		}

}
