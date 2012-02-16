package jessTest;

import java.util.*;
import jess.*;


public class Ejemplo3 {

	public static String file;
	public static Vector<String> listHechos;
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws JessException {
		
		file = "\"test/prueba3.clp\"";
		
		listHechos = new Vector<String>();
		listHechos.add(0,"MAIN::par");
		listHechos.add(1,"MAIN::impar");
		listHechos.add(2,"MAIN::numero");
		
		Rete r = new Rete();
		r.executeCommand("(batch "+file+")");
		r.reset();
		
		r.executeCommand("(assert (par (valor "+2+")))");
		r.executeCommand("(assert (impar (valor "+5+")))");
		r.executeCommand("(assert (impar (valor "+7+")))");
		r.executeCommand("(assert (numero (valor "+2.5+")))");
		
		r.run();
		
		procesarHechos(r);
		
	}

	@SuppressWarnings("rawtypes")
	static void procesarHechos(Rete r) throws JessException {
		Fact hecho;
		Value v;
		Context c = r.getGlobalContext();
		Iterator it = r.listFacts();
		while(it.hasNext()) {
			hecho = (Fact) it.next();
			switch ( listHechos.indexOf(hecho.getName()) ) {
			case 0:
				//	?h <- (numero (valor ?v))
				v =  hecho.getSlotValue("valor");
				System.out.println("Numero par: "+v.intValue(c));
			break;
			case 1:
				v =  hecho.getSlotValue("valor");
				System.out.println("Numero impar: "+v.intValue(c));
			break;
			case 2:
				v =  hecho.getSlotValue("valor");
				System.out.println("Numero real: "+v.floatValue(c));
			break;
			default:
				System.out.println("Hecho desconocido :"+hecho.getName());
			}
			
		}
	}

}
