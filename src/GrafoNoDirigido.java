

public class GrafoNoDirigido<T> extends GrafoDirigido<T> {

	//METHODS
	
	//OVERRIDES
	
	//Complejidad: O(1+1) -> O(1)
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		super.agregarArco(verticeId1, verticeId2, etiqueta);	//O(1)
		super.agregarArco(verticeId2, verticeId1, etiqueta);	//O(1)
	}
	
	//Complejidad: O(1+1) -> O(1)
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		super.borrarArco(verticeId1, verticeId2);	//O(1)
		super.borrarArco(verticeId2, verticeId1);	//O(1)
	}

}
