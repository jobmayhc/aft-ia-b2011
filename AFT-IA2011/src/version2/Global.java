/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version2;

public class Global {
		
    public static final int NO_PATH = -1, NOT_FOUND = 0, FOUND = 1;
    
	public static final int OBSTACULO = 0, ORIGEN = 1, DESTINO = 2;
	
	public static final double NADA = 1, BAJO = 2, MEDIO = 5, ALTO = 10, TOTAL = Double.MAX_VALUE;
    
    public static final String IMAG = "map_900x725.jpg";
    
    public static final int IMAG_X = Integer.parseInt(Global.IMAG.substring(Global.IMAG.indexOf('_') + 1, Global.IMAG.indexOf('x')) );
    
    public static final int IMAG_Y = Integer.parseInt(Global.IMAG.substring(Global.IMAG.indexOf('x') + 1, Global.IMAG.indexOf('.')) );
    
    public static final int FILA = 10, COLUMNA = 10, DIRECCION = 4;

    public static int getTime(){
    	return (int)(java.util.Calendar.getInstance().getTimeInMillis()/1000)%100;  
    }  
}
