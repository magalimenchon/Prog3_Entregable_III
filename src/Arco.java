


/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T> {

	
	//FIELDS
	
	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	
	//CONSTRUCTOR

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {	//O(1)
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}

	
	//METHODS
	
	public int getVerticeOrigen() {	//O(1)
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {	//O(1)
		return verticeDestino;
	}

	public T getEtiqueta() {	//O(1)
		return etiqueta;
	}
	
	
	//EXTRA
	
	//OVERRIDES
	
	@Override
	public String toString(){	//O(1)
		
		return "Vértice origen: " + this.getVerticeOrigen() +
				" - Vértice destino: "+ this.getVerticeDestino() +
				" - Etiqueta: " + this.getEtiqueta();
		
	}
	
	@Override
	public boolean equals(Object obj) {	//O(1)
	
		try {
			
			Arco<T> arco = (Arco<T>) obj;
			
			return this.getVerticeOrigen() == arco.getVerticeOrigen() &&
				   this.getVerticeDestino() == arco.getVerticeDestino();
			
		}
		catch(Exception e){
			
			return false;
			
		}
	
	}
}
