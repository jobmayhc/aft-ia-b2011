/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version1;

public class Arc  {
	
	private String id;
	private String fuente;
	private String destino;
	private float distancia;
	private float velocidad;
	private float trafico;	
	
	public Arc(String id, String fuente, String destino, float d, float v, float t) {
		this.setId(id);
		this.setFuente(fuente);
		this.setDestino(destino);
		this.setDistancia(d);
		this.setVelocidad(v);
		this.setTrafico(t);
	}
	
	protected String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
	}

	protected String getFuente() {
		return fuente;
	}

	protected void setFuente(String fuente) {
		this.fuente = fuente;
	}

	protected String getDestino() {
		return destino;
	}

	protected void setDestino(String destino) {
		this.destino = destino;
	}

	protected float getDistancia() {
		return distancia;
	}

	protected void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	protected float getVelocidad() {
		return velocidad;
	}

	protected void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
	
	protected float getTrafico() {
		return trafico;
	}

	protected void setTrafico(float trafico) {
		this.trafico = trafico;
	}

	public float getCosto() {	
		return (float) ((distancia/velocidad)/(1.05-trafico));
	}
	
	public float getCosto( float time ){
		float longitud = (float) 360;
		float maximo = (float) 200;
		return (float) (this.getTrafico()* Math.exp( Math.pow(time-maximo,2)/(2*longitud) ) );
	}
	
	public boolean equals(Object obj) {
		Arc other = (Arc) obj;
		if ( this.getId().equals(other.getId()) )
			return true;
		return false;
	}
		
}
