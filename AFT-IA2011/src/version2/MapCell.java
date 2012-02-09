/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version2;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings("serial")
public class MapCell extends Component implements Serializable{

	static double pesoObstaculo = Global.TOTAL;
	static int editMode = Global.OBSTACULO;
	static Vector<MapCell> celda = new Vector<MapCell>();
	static MapCell inicio, fin;
	Point posicion;
	double costo, distanciaInicio;
	boolean uso, parteRuta;
	
	public MapCell(){
		costo = 1;
		distanciaInicio = -1;
		uso = parteRuta = false;
		celda.addElement(this);
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
	}

	public void processMouseEvent(MouseEvent e){
		super.processMouseEvent(e);
		if( e.getID() == MouseEvent.MOUSE_PRESSED ){
//			Global.AFT.doSuspend();
			switch( editMode ){
				case( Global.OBSTACULO ):
					costo = costo != pesoObstaculo ? pesoObstaculo : Global.TOTAL;
					repaint();
				break;
				case( Global.ORIGEN ):
					setInicio(true);
				break;
				case( Global.DESTINO ):
					setFin(true);
				break;
			}
		}
		if( e.getID() == MouseEvent.MOUSE_RELEASED ){
//			Global.AFT.doActivate();
		}
	}

	public void addRutaInicio(double dist){
		uso = true;
		if( distanciaInicio == -1 ){
			distanciaInicio = dist + costo;
			return;
		}
		if( dist + costo < distanciaInicio ){
			distanciaInicio = dist + costo;
		}
	}

	public void setInicio(boolean flag){
		if(flag){
			MapCell temp = this;
			if( inicio != null ){
				temp = inicio;
				temp.setInicio(false);
				}
			inicio = this;
			repaint();
			temp.repaint();
		}
	}

	public void setFin(boolean flag){
		if(flag){
			MapCell temp = this;
			if( fin != null ){
				temp = fin;
				temp.setFin(false);
			}
			fin = this;
			repaint();
			temp.repaint();
		}
	}

	public void setTotalBlock(boolean flag){
		if(flag){costo = Global.TOTAL;}
		else{costo = Global.NADA;}
	}

	private void resetCell(){
		uso = false;
		parteRuta = false;
		distanciaInicio = -1;
	}

	public static void reset(){
		for( int i = 0 ; i < celda.size() ; i++ ){
			 celda.elementAt(i).resetCell();
		}
	}
	
	public double getDistanciaInicio(){
		if( MapCell.inicio == this )	return 0;
		return isTotal()?-1:distanciaInicio;
	}

	public void paint(Graphics g){
		Dimension size = getSize();
		g.setColor(new Color(255,255,255,0)); 
		if( costo != Global.NADA ){
			if( costo == Global.BAJO )	{g.setColor(new Color(000,100,000,075));}
			if( costo == Global.MEDIO )	{g.setColor(new Color(000,100,000,150));}
			if( costo == Global.ALTO )	{g.setColor(new Color(000,100,000,225));}
			if( costo == Global.TOTAL )	{g.setColor(new Color(100,100,100,200));}
		}
		if( parteRuta ) 			{g.setColor(new Color(255,000,255,200));}
		if( inicio == this )		{g.setColor(new Color(000,000,255,250));}
		if( fin == this )			{g.setColor(new Color(255,000,000,250));}
		g.fillRect(0,0,size.width,size.height);
		g.setColor(Color.GRAY);
		g.drawRect(0,0,size.width-1,size.height-1);    
	}
			
	public boolean isTotal(){return costo == Global.TOTAL;}
	
}