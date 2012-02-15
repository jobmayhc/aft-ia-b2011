package version3;

import jade.core.behaviours.*;

@SuppressWarnings("serial")
public class goPath extends Behaviour{
	
	static boolean bandera = false; 
	
	@SuppressWarnings("deprecation")
	public void action(){
		Huristic.actual = MapCell.fin;
		while(true){
			MapCell proximo = AgentIA.MAP.getMejorVecino(Huristic.actual);
			if( proximo.equals(MapCell.inicio)) {
				bandera = true;
				Applet.go.setLabel("Iniciar");
				Applet.finish.enable();
				Applet.reset.enable();
				if( AgentIA.HILO.isAlive() )
					AgentIA.HILO.stop();
				AgentIA.BUS = new Huristic();
			}else
				if( Huristic.actual.equals(AgentIA.MAP.getMejorVecino(proximo)) ) {
					bandera = true;
					System.out.println("Soy mi mejor vecino");
					AgentIA.AFT.doWait();
					AgentIA.AFT.doWake();
				}
			Huristic.actual = proximo;
			Huristic.actual.parteRuta = true;
			myAgent.doWait((int)Huristic.actual.costo*1000);
			Huristic.actual.repaint();
			if( bandera ) return;
		}         
	}
	
	public boolean done(){
		System.out.println("Sali con exito del comportamiento");
		return bandera;
		}
}