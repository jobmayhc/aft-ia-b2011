package version3;

import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class Contar extends Behaviour{
    private int estado = 0;

    public void action(){
    	myAgent.doWait(1000);
        switch(estado){
            case 0: System.out.println(estado); break;
            case 1: System.out.println(estado); break;
            case 2: System.out.println(estado); break;
            case 3: System.out.println(estado); break;
            case 4: System.out.println(estado); break;
            case 5: System.out.println(estado); break;
            case 6: System.out.println(estado); break;
            case 7: System.out.println(estado);
                    myAgent.doDelete();
                    break;
            }
        estado++;
    }

    public boolean done(){
        return (estado > 7);
    }
}