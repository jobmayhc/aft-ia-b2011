package jessTest;

import jess.*;

public class Ejemplo2 {
	
	static String file = "\"test/prueba2.clp\"";

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws JessException {
		Value v;
		Context c;
		
		Rete r = new Rete();
		r.store("dato1", new Value(10,RU.INTEGER));
		r.store("dato2", new Value(20,RU.INTEGER));
		r.executeCommand("(batch "+file+")");
		r.reset();
		r.run();
		
		v = r.fetch("resultado");
		c = r.getGlobalContext();
		System.out.println("El resultado es :"+v.intValue(c));

	}

}
