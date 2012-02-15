package version3;

import jade.core.Agent;
import jade.core.behaviours.*;

public class MiAgente extends Agent{

	protected void setup(){
		addBehaviour(new MiComportamiento1());
	}

	private class MiComportamiento1 extends Behaviour{
		
		public void action(){
			System.out.println("Mi nombre es: "+getName() );
			System.out.println("Soy el comportamiento del agente");

		}
		
		public boolean done(){
			return true;
		}
	}
}