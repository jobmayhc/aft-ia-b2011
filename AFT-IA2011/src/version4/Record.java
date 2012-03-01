/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version4;

import java.awt.*;
import java.util.*;

public class Record {
	
	protected Date begin, end;
	protected boolean success;
	protected String event;
	protected Vector<Point> path;

	public Record(){
		end = begin = Calendar.getInstance().getTime();
		success = false;
		event = null;
		path = new Vector<Point>();
	}
	
	public Record(Vector<Point> dat){
		end = begin = Calendar.getInstance().getTime();
		success = false;
		event = null;
		path.copyInto(dat.toArray());
	}
	
	protected void setEnd(){
		end = Calendar.getInstance().getTime();
	}
	
	protected void setSucces(){
		success = true;
	}
	
	protected void setEvent(String dat){
		event = dat;
	}
	
}
