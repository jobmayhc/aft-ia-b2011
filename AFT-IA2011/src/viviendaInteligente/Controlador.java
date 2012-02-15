// AGENTE QUE CONTROLA EL FUNCIONAMIENTO DE LA CASA SEGÚN LA INFORMACIÓN
// QUE RECIBE DEL AGENTE SENSORES

package viviendaInteligente;

import jade.core.Agent;
import jade.core.behaviours.*;

import java.io.*;

import jade.lang.acl.ACLMessage;

import java.util.StringTokenizer;

import java.lang.String;

import jess.*;

@SuppressWarnings("serial")
public class Controlador extends Agent{
	
	protected void setup(){	
		addBehaviour(new controlJess());
	}
	
	class controlJess extends OneShotBehaviour {
    
    	// Declaración de los hechos actuales.
    	
    	private String vivienda = "vivienda ocupada";			
		private String puerta = "puerta abierta";				
		private String alarma = "alarma desconectada";											
		private String luz = "luz encendida";		
		private String horno = "horno apagado";
		private String puerta_horno = "puerta-horno cerrada";					
		private String puerta_nevera = "puerta-nevera cerrada";		
		private String nevera = "nevera encendida";
    
    	
    	public void action() {
    		    	
    		// Declara el motor de JESS.
	    	Rete jess = new Rete();
	    	
	    	// Analiza que el archivo JESS sea correcto.
	    	try{
		    	FileReader file = new FileReader("viviendaInteligente.clp");
		    	try {
		    	     Jesp parser = new Jesp(file, jess);
				     parser.parse(false);
				 } catch (jess.JessException je) {
		                 	je.printStackTrace();
		                }
				  finally {
				     file.close();
				 }
				 }catch(IOException e) {
	             	System.err.println("Error!! No se ha podido acceder al archivo.");
             }
             
            // Carga el archivo JESS.
	    	try {
        		jess.reset();
//        		jess.batch("viviendaInteligente.clp");
        		
        		// Inicializa los hechos.
        		
        		jess.eval("(assert (vivienda ocupada))");
        		jess.eval("(assert (puerta abierta))");
        		jess.eval("(assert (alarma desconectada))");    	    
        		jess.eval("(assert (luz encendida))");
        		jess.eval("(assert (horno apagado))");
        		jess.eval("(assert (puerta-horno cerrada))");
        		jess.eval("(assert (nevera encendida))");
        		jess.eval("(assert (puerta-nevera cerrada))");

				// Muestra los hechos iniciales.

				
				System.out.println("\nHechos iniciales:                   20 grados. ");
				System.out.println("-------------------------------------------------");
				System.out.println("| vivienda ocupada    -    puerta abierta       |");
				System.out.println("| alarma desconectada -    luz encendida        |");
				System.out.println("| horno apagado       -    puerta-horno cerrada |");
				System.out.println("| nevera encendida    -    puerta-nevera cerrada|");
				System.out.println("-------------------------------------------------");	
				
		    } catch (JessException ex) {
		        System.err.println(ex);
		    }
		    
		    
    		// Sensores envía constantemente la información que recibe.
    		// Este bucle comprueba si trae información distinta a la que se 
    		// tiene actualmente para aplicarla sobre JESS.
			while(true){
				block(5000);
				
				try{
					// La información recibida del agente Sensores viene a través
					// de un mensaje ACL.
					ACLMessage msg=myAgent.receive();
	            	if(msg!=null) {
			        	String contenido=msg.getContent();
			        	
			        	// Si la temperatura aumenta o disminuye se llama a la función correspondiente.
			            if (contenido.equals("bajar temperatura")){ 
			            	jess.eval("(-- ?*temperatura*)");
			            	jess.eval("(bajando)");
			            }
			        	else if (contenido.equals("subir temperatura")){ 
			        			jess.eval("(++ ?*temperatura*)");
			        			jess.eval("(subiendo)");
			        		}
			        		// Si no es la temperatura compara la nueva información con los hechos actuales.
			        		// Si es información nueva, la añade y borra el hecho correspondiente. 
			        		else {
			        			
					    		StringTokenizer st = new StringTokenizer(contenido);
					    		String var = st.nextToken();
					    		
					    		StringTokenizer st2 = new StringTokenizer(vivienda);
					    		String var2 = st2.nextToken();
					    		
					    		if(var2.equals(var)){
					    			if(!vivienda.equals(contenido)){
					    				jess.eval("(retract-string \"(" + vivienda + ")\")");
					    				jess.eval("(assert (" + contenido + "))");
					    				vivienda = contenido;	
					    			}		
					    		}
					    		
					    		st2 = new StringTokenizer(puerta);
					    		var2 = st2.nextToken();
					    		if(var2.equals(var)){
					    			if(!puerta.equals(contenido)){
					    				jess.eval("(retract-string \"(" + puerta + ")\")");
					    				jess.eval("(assert (" + contenido + "))");
					    				puerta = contenido;	
					    			}		
					    		}
					    		
					    		st2 = new StringTokenizer(alarma);
					    		var2 = st2.nextToken();
					    		if(var2.equals(var)){
					    			if(!alarma.equals(contenido)){
					    				jess.eval("(retract-string \"(" + alarma + ")\")");
					    				jess.eval("(assert (" + contenido + "))");
					    				alarma = contenido;	
					    			}		
					    		}
					    		
					    		st2 = new StringTokenizer(luz);
					    		var2 = st2.nextToken();
					    		if(var2.equals(var)){
					    			if(!luz.equals(contenido)){
					    				jess.eval("(retract-string \"(" + luz + ")\")");
					    				jess.eval("(assert (" + contenido + "))");
					    				luz = contenido;	
					    			}		
					    		}
					    		
					    		st2 = new StringTokenizer(horno);
					    		var2 = st2.nextToken();
					    		if(var2.equals(var)){
					    			if(!horno.equals(contenido)){
					    				jess.eval("(retract-string \"(" + horno + ")\")");
					    				jess.eval("(assert (" + contenido + "))");
					    				horno = contenido;	
					    			}		
					    		}
					    		
					    		st2 = new StringTokenizer(puerta_horno);
					    		var2 = st2.nextToken();
					    		if(var2.equals(var)){
					    			if(!puerta_horno.equals(contenido)){
					    				jess.eval("(retract-string \"(" + puerta_horno + ")\")");
					    				jess.eval("(assert (" + contenido + "))");
					    				puerta_horno = contenido;	
					    			}		
					    		}
					    		
					    		st2 = new StringTokenizer(puerta_nevera);
					    		var2 = st2.nextToken();					    		
					    		if(var2.equals(var)){
					    			if(!puerta_nevera.equals(contenido)){
					    				jess.eval("(retract-string \"(" + puerta_nevera + ")\")");
					    				jess.eval("(assert (" + contenido + "))");
					    				puerta_nevera = contenido;	
					    			}		
					    		}
					    		
					    		st2 = new StringTokenizer(nevera);
					    		var2 = st2.nextToken();
					    		if(var2.equals(var)){
					    			if(!nevera.equals(contenido)){
					    				jess.eval("(retract-string \"(" + nevera + ")\")");
					    				jess.eval("(assert (" + contenido + "))");
					    				nevera = contenido;	
					    			}		
					    		}	
			        		}
			        	jess.run();
			        				        	
			        }
		        } catch (JessException ex) {
		        System.err.println(ex);
		    }	
			}
		
	
    	}
    	
    }

}