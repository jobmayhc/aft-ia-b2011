package jessTest;

import jess.*;

public class Ejemplo1 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws JessException  {
		Rete r = new Rete();
		r.executeCommand("(batch test/prueba.clp)");
		r.reset();
		r.run();
	}
}
