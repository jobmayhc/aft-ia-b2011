package version1;

import org.apache.commons.collections15.Transformer;

class CostoTiempo<I extends Arc, O extends Number> implements Transformer<Arc, Float> {
	public Float transform(Arc arco) {
		float peso = arco.getCosto(05);
		return Float.valueOf(peso);
	}
}

class Distancia<I extends Arc, O extends Number> implements Transformer<Arc, Float> {
	public Float transform(Arc arco) {
		float peso = arco.getDistancia();
		return Float.valueOf(peso);
	}
}

class CostoSimple<I extends Arc, O extends Number> implements Transformer<Arc, Float> {
	public Float transform(Arc arco) {
		float peso = arco.getCosto();
		return Float.valueOf(peso);
	}
}