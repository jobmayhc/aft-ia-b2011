// AGENTE QUE REPRESENTA A LOS SENSORES QUE CONTROLAN LA CASA

package viviendaInteligente;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;

import jade.lang.acl.ACLMessage;

import java.util.Random;

@SuppressWarnings("serial")
public class Sensores extends Agent{
	
	//SENSORES DE LA CASA
	
	protected void setup(){
		
		addBehaviour(new controlSensores());
		
	}
	
	// Este comportamiento es el que recibe los cambios en los sensores.
	// Está simulado por un Random que selecciona el sensor y otro Random 
	// que selecciona el nuevo valor del sensor.
	class controlSensores extends CyclicBehaviour {
			
		public void action() {
			
						
			String hecho = "";
			Random r = new Random();
				
			// Selecciona el sensor y la opción.
			int sensor = r.nextInt(9);
			int opcion = r.nextInt(2);
				
			switch(sensor){
				case 0: 
					if(opcion == 0)  hecho = "vivienda vacia";	
					if(opcion == 1)  hecho = "vivienda ocupada";		
					System.out.println(hecho);
					break;
					
				case 1: 
					if(opcion == 0)  hecho = "puerta cerrada";					 
					if(opcion == 1)  hecho = "puerta abierta";						
					System.out.println(hecho);
					break;
					
				case 2: 
					if(opcion == 0)  hecho = "alarma desconectada";	
					if(opcion == 1)  hecho = "alarma conectada";	
					System.out.println(hecho);
					break;
					
				case 3: 
					if(opcion == 0)  hecho = "bajar temperatura";		
					if(opcion == 1)  hecho = "subir temperatura";
					System.out.println(hecho);
					break;
					
				case 4: 
					if(opcion == 0)  hecho = "luz apagada";	
					if(opcion == 1)  hecho = "luz encendida";	
					System.out.println(hecho);
					break;
					
				case 5: 
					if(opcion == 0)  hecho = "horno apagado";	
					if(opcion == 1)  hecho = "horno encendido";	
					System.out.println(hecho);
					break;
					
				case 6: 
					if(opcion == 0)  hecho = "puerta-horno cerrada";	
					if(opcion == 1)  hecho = "puerta-horno abierta";	
					System.out.println(hecho);
					break;
					
				case 7: 
					if(opcion == 0)  hecho = "nevera apagada";	
					if(opcion == 1)  hecho = "nevera encendida";	
					System.out.println(hecho);
					break;
					
				case 8: 
					if(opcion == 0)  hecho = "puerta-nevera cerrada";	
					if(opcion == 1)  hecho = "puerta-nevera abierta";	
					System.out.println(hecho);
					break;	
			}
			
			// Envía la información al controlador mediante un mensaje ACL.
			if(!hecho.equals("")){
				ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
	            msg.addReceiver(new AID("controlador",AID.ISLOCALNAME));
	            msg.setContent(hecho);
	            myAgent.send(msg);
           }  
           
           // La información de los sensores se comprueba cada 5 segundos.
			block(5000);
		}
	}


}