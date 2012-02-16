/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import java.awt.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Map extends JPanel implements Serializable{

	public final Image imagen;
	MapCell mapa[][];

	public Map(){
		imagen = new ImageIcon(getClass().getResource(Global.IMAG)).getImage();
	    mapa = new MapCell[Global.COLUMNA][Global.FILA];
		setLayout(new GridLayout(Global.COLUMNA,Global.FILA));
		for( int c = 0 ; c < Global.COLUMNA ; c++ ){
		for( int f = 0 ; f < Global.FILA ; f++ ){
				mapa[c][f] = new MapCell();	
				mapa[c][f].posicion = new Point(c,f);
				add(mapa[c][f]);
			}
		}
	}
	
	//	OBTIENE LOS VECINOS 
	public MapCell[] getVecinos(MapCell actual){
		MapCell vecino[] = new MapCell[Global.DIRECCION];
		Point p = actual.posicion;
		if( p.y > 0 && p.y < Global.FILA-1 ){
			if( p.x > 0 )					vecino[0] = mapa[p.x-1][p.y];
			if( p.x < Global.COLUMNA-1 )	vecino[1] = mapa[p.x+1][p.y];
		}
		if( p.x > 0 && p.x < Global.COLUMNA-1 ){
			if( p.y > 0 )					vecino[2] = mapa[p.x][p.y-1];
			if( p.y < Global.FILA-1 )		vecino[3] = mapa[p.x][p.y+1];			
		}
		return vecino;
	}

	//	OBTIENE EL VECINO MAS PROXIMO
	public MapCell getMejorVecino(MapCell actual){
		MapCell proximo[] = getVecinos(actual);
		MapCell cercano = proximo[0];
		double dist, lejos = Double.MAX_VALUE;
		for( int i = 0 ; i < Global.DIRECCION ; i++ ){
			if ( proximo[i] != null ){
				dist = proximo[i].getDistanciaInicio();
				if( dist < lejos && dist >= 0 ){
					cercano = proximo[i];
					lejos = dist;
				}
			}
		}
		return cercano;
	}
	
	protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, super.getWidth(), super.getHeight(),null);
        
    }
}
