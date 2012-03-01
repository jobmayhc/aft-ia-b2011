/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version4;

public class Global {
		   
	public static final int OBSTACULO = 0, ORIGEN = 1, DESTINO = 2;
	
	public static final double NADA = 1, BAJO = 2, MEDIO = 5, ALTO = 10, TOTAL = Double.MAX_VALUE;
	
	public static double TIPO = TOTAL;
	
	public static int EDIT = OBSTACULO;
    
    public static final String IMAG = "map_900x725.jpg";
    
    public static final int IMAG_X = Integer.parseInt(Global.IMAG.substring(Global.IMAG.indexOf('_') + 1, Global.IMAG.indexOf('x')) );
    
    public static final int IMAG_Y = Integer.parseInt(Global.IMAG.substring(Global.IMAG.indexOf('x') + 1, Global.IMAG.indexOf('.')) );
    
    public static final int FILA = 15, COLUMNA = 15, DIRECCION = 4;
  
}
