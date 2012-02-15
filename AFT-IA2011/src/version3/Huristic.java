/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import java.awt.*;
import java.util.*;

public class Huristic{

	public static Vector<MapCell> pendientes, examinados;
	public static MapCell actual, aux;
	public static double DistanciaInicio, costoMin;


	public Huristic(){
		DistanciaInicio = 0;
		costoMin = Double.MAX_VALUE;
		actual = new MapCell();
		aux = new MapCell();        
	}

	public static double findDistancia(Point actual,Point destino,double minimo){
		Point origen = MapCell.inicio.posicion;
		double dx1 = origen.x - destino.x;
		double dy1 = origen.y - destino.y;
		double dx2 = actual.x - destino.x;
		double dy2 = actual.y - destino.y;
		return  minimo *(Math.abs(dy1*dx2-dx1*dy2)*0.002+Math.abs(dx2)+Math.abs(dy2));
	}

	public boolean findRuta(){
		pendientes = new Vector<MapCell>();
		examinados = new Vector<MapCell>();
		for( int c = 0 ; c < Global.COLUMNA ; c++ )
			for( int f= 0 ; f < Global.FILA ; f++)
				costoMin = Math.min(AgentIA.MAP.mapa[c][f].costo,costoMin);
		MapCell.reset();
		if( MapCell.inicio == null || MapCell.fin == null ){
			System.out.println("Error al definir Origen->Destino");
			return false;
		}
		AgentIA.HILO.start();
		return true;
	}

	public void run() {
		pendientes.addElement(MapCell.inicio);
		int state = Global.NOT_FOUND;     
		for( int pass = 0; (state == Global.NOT_FOUND && pass < Integer.MAX_VALUE) ; pass++ ) {
			state = step();
		}
		if( state == Global.FOUND ) {
			System.out.println("setRuta -> Debe conectarse a un comportamiento");

			//			setRuta();
		}
		else	System.out.println("No hay camino entre Origen->Destino");		
	}

	@SuppressWarnings("unchecked")
	public int step(){
		boolean found = false;
		Vector<MapCell> temp = (Vector<MapCell>) pendientes.clone();
		double min = Double.MAX_VALUE;
		double score;
		MapCell best = temp.elementAt(temp.size()-1);
		MapCell now;
		for( int i = 0 ; i < temp.size() ; i++ ){
			now = temp.elementAt(i);
			if( !examinados.contains(now) ){
				score = now.getDistanciaInicio();
				score += findDistancia(now.posicion,MapCell.fin.posicion,costoMin);
				if( score < min ){min = score;	best = now;}
			}
		}
		now = best;
		pendientes.removeElement(now);
		examinados.addElement(now);
		MapCell next[] = AgentIA.MAP.getVecinos(now);
		for( int i = 0 ; i < Global.DIRECCION ; i++ ){
			if( next[i] != null ){
				if( next[i] == MapCell.fin )	found = true;
				if( !next[i].isTotal() ){
					next[i].addRutaInicio(now.getDistanciaInicio());
					if( !pendientes.contains(next[i]) && !examinados.contains(next[i]) )
						pendientes.addElement(next[i]);
				}
			}if(found)	return Global.FOUND;
		}
		AgentIA.MAP.repaint();
		return pendientes.size()==0?Global.NO_PATH:Global.NOT_FOUND;
	}
	/*
	public void setRuta(){
		actual = MapCell.fin;
		while(true){
			MapCell proximo = AgentIA.MAP.getMejorVecino(actual);
			if( proximo.equals(MapCell.inicio)) {
				Applet.go.setLabel("Iniciar");
				Applet.finish.enable();
				Applet.reset.enable();
				if( AgentIA.HILO.isAlive() )
					AgentIA.HILO.stop();
				AgentIA.BUS = new Huristic();
				return;
			}else
				if( actual.equals(AgentIA.MAP.getMejorVecino(proximo)) ) {
					AgentIA.AFT.doWait(2000);
					AgentIA.AFT.doWake();
				}
			actual = proximo;
			actual.parteRuta = true;
			try{Thread.sleep((int)actual.costo*1000);
			}catch(	InterruptedException e){}
			actual.repaint();
		}            
	}
	 */
}