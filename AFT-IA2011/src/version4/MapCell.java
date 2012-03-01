/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version4;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings("serial")
public class MapCell extends Component implements Serializable{

	public static MapCell inicio, fin, actual;
	public static Vector<MapCell> celda = new Vector<MapCell>();
	protected Point posicion;
	protected double costo, distanciaFin;
	protected boolean usado, parteRuta;

	protected MapCell(){
		costo = 1;
		distanciaFin = -1;
		usado = parteRuta = false;
		celda.addElement(this);
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
	}

	protected void processMouseEvent(MouseEvent e){
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

	protected void addRuta(double dist){
		usado = true;
		if( distanciaFin == -1 ){
			distanciaFin = dist + costo;
			return;
		}
		if( dist + costo < distanciaFin )
			distanciaFin = dist + costo;
	}

	protected void setFin(boolean flag){
		if(!flag)	return;
		if( fin != null ){
			fin.setFin(false);
			fin.repaint();
		}
		fin = this;
		repaint();

	}

	protected void setInicio(boolean flag){
		if(!flag)	return;
		if( inicio != null ){
			inicio.setInicio(false);
			inicio.repaint();
		}
		inicio = this;
		repaint();
	}

	protected void setTotalBlock(boolean flag){
		if(flag){costo = Global.TOTAL;}
		else{costo = Global.NADA;}
	}

	protected void resetCell(boolean flag){
		usado = false;
		parteRuta = flag?parteRuta:false;
		distanciaFin = -1;
	}

	protected static void reset(boolean flag){
		for( int i = 0 ; i < celda.size() ; i++ )
			celda.elementAt(i).resetCell(flag);
	}

	protected double getDistanciaFin(){
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