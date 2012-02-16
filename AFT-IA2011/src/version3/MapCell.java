/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings("serial")
public class MapCell extends Component implements Serializable{

	public static MapCell inicio, fin, actual;
	public static Vector<MapCell> celda = new Vector<MapCell>();
	public Point posicion;
	public double costo, distanciaFin;
	public boolean usado, parteRuta;
	
	public MapCell(){
		costo = 1;
		distanciaFin = -1;
		usado = parteRuta = false;
		celda.addElement(this);
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
	}
	
	public void processMouseEvent(MouseEvent e){
		super.processMouseEvent(e);
		if( e.getID() == MouseEvent.MOUSE_PRESSED ){
			switch( Global.EDIT ){
				case( Global.OBSTACULO ):
					costo = costo != Global.TIPO ? Global.TIPO : Global.TOTAL;
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
	}

	public void addRuta(double dist){
		usado = true;
		if( distanciaFin == -1 ){
			distanciaFin = dist + costo;
			return;
		}
		if( dist + costo < distanciaFin )
			distanciaFin = dist + costo;
	}

	public void setFin(boolean flag){
		if(flag){
			MapCell temp = this;
			if( fin != null ){
				temp = fin; temp.setFin(false);
				}
			fin = this;
			repaint();
			temp.repaint();
		}
	}

	public void setInicio(boolean flag){
		if(flag){
			MapCell temp = this;
			if( inicio != null ){
				temp = inicio; temp.setInicio(false);
			}
			inicio = this;
			repaint();
			temp.repaint();
		}
	}

	public void setTotalBlock(boolean flag){
		if(flag){costo = Global.TOTAL;}
		else{costo = Global.NADA;}
	}

	private void resetCell(boolean flag){
		usado = false;
		parteRuta = flag?parteRuta:false;
		distanciaFin = -1;
	}

	public static void reset(boolean flag){
		for( int i = 0 ; i < celda.size() ; i++ )
			 celda.elementAt(i).resetCell(flag);
	}
	
	public double getDistanciaFin(){
		if( MapCell.fin == this )	return 0;
		return costo == Global.TOTAL?-1:distanciaFin;
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
//		if ( distanciaFin > 0 )	g.drawString(""+distanciaFin,1,(int)(size.height*0.75));
		g.drawRect(0,0,size.width-1,size.height-1);    
	}
	
}