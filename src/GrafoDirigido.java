import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class GrafoDirigido<T> implements Grafo<T> {

	
	//FIELDS
	
	//Clave: vertice, valor: lista de arcos que salientes de ese vertice.
	private HashMap< Integer, ArrayList<Arco<T>> > vertices;	//todos los vértices del grafo
	private int cantidadArcos;
	
	//CONSTRUCTOR

	public GrafoDirigido() {	//O(1)
		
		this.vertices = new HashMap< Integer, ArrayList<Arco<T>> >();
		this.cantidadArcos = 0;
	}
	
	
	//METHODS
	
	//Agrega el vertice al hashmap
	@Override
	public void agregarVertice(int verticeId) {	//O(1)
		if(!this.contieneVertice(verticeId)){	//O(1)
			this.vertices.put(verticeId, new ArrayList<Arco<T>>());	//O(1)
		}

	}

	//Complejidad: O(v*a) donde v es la cantidad de vertices del grafo y
	//a es la cantidad de arcos de cada vértice
	@Override
	public void borrarVertice(int verticeId) {
		if(this.contieneVertice(verticeId)){	//O(1)
			
			Iterator<Integer> iter = this.obtenerVertices();	//O(1)
			
			//por cada vértice que no sea el vértice a eliminar, busco si puedo borrar un arco dirigido al mismo,
			//o bien actualizo el valor precalculado de cantidadArcos en caso de serlo
			while (iter.hasNext()) {	//O(v)
				
				int verticeActual = iter.next();
				
				if(verticeActual != verticeId){	//si no estoy en el vértice a eliminar
					this.borrarArco(verticeActual, verticeId);	//O(a)
				}
				else{	//si es el vértice a borrar, obtengo cuántos adyascentes tiene para restarlos a cantidadArcos
					int cantidadAdyacentes = this.vertices.get(verticeId).size();	//O(1)
					cantidadArcos = cantidadArcos - cantidadAdyacentes;
				}
	        }
			
			//elimino el vértice del hashmap
			this.vertices.remove(verticeId);	//O(1)
			
		}

	}
	
	//Agrega un arco
	//Complejidad: O(a) donde a es la cantidad de arcos de el vértice origen.
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		
		Arco<T> arco = new Arco<T>(verticeId1, verticeId2, etiqueta);
		
		//si existe el vértice destino y no existe el arco que quiere añadirse, lo agrego 
		if( this.contieneVertice(verticeId2) && !this.existeArco(verticeId1, verticeId2) ){//O(1 + a)
			
			this.vertices.get(verticeId1).add(arco);	//O(1)
			cantidadArcos++;
		
		}
	}

	//Complejidad: O(a) donde la cantidad de arcos de ese vértice.
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		
		Arco<T> arco = new Arco<T>(verticeId1, verticeId2, null);
		
		if(this.vertices.get(verticeId1).remove(arco)){ //O(a)	//borra el arco
			cantidadArcos--;
		}

	}

	//Retorna si el hashmap contiene la clave con el vertice dado.
	@Override
	public boolean contieneVertice(int verticeId) {	//O(1)
	
		return vertices.containsKey(verticeId);	//O(1)
	
	}
	
	//Busca la clave de vértice origen y checkea si el posible arco es contenido en su ArrayList.
	//Complejidad: O(a) donde a es la cantidad de arcos de el vértice origen.
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		
		Arco<T> arco = new Arco<T>(verticeId1, verticeId2, null);
		return this.vertices.get(verticeId1).contains(arco);	//O(a)
		
	}

	//Complejidad: O(a) donde a es la cantidad de arcos de el vértice origen.
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		
		Arco<T> arcoBuscado = new Arco<T>(verticeId1, verticeId2, null);
		Iterator<Arco<T>> iter = this.obtenerArcos(verticeId1);	//O(1)
	
		while (iter.hasNext()) {	//O(a)
			
			Arco<T> arcoActual = iter.next();
			
			if(arcoActual.equals(arcoBuscado)){
				return arcoActual;
			}
			
        }
		return null;
	
	}

	//Retorna la cantidad de vertices totales.
	@Override
	public int cantidadVertices() {	//O(1)
		
		return vertices.keySet().size();	//O(1)
		
	}
	
	//Retorna la cantidad de arcos totales
	@Override
	public int cantidadArcos() {	//O(1)
		
		return cantidadArcos;
		
	}
	
	
	//Retorna un iterador que da acceso a todos los vértices del grafo.
	@Override
	public Iterator<Integer> obtenerVertices() {	//O(1)
		
		return this.vertices.keySet().iterator();	//O(1)
		
	}

	//Dado un vertice obtener sus adyascentes
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {	//O(1)
		
		Iterator<Arco<T>> it = this.vertices.get(verticeId).iterator();	//O(1)
		
		return new IteratorArco<T>(it);	//O(1)
	}

	//Complejidad: o(v*a) donde v es la cantidad de vértices del grafo y la a es la cant de arcos de ese vertice.
	@Override
	public Iterator<Arco<T>> obtenerArcos() {	//O(1)
		
		ArrayList<Arco<T>> totalArcos = new ArrayList<Arco<T>>();
		
		for(int i = 0; i < this.cantidadVertices(); i++) {	//O(v)
			
			if(this.vertices.get(i) != null){	//por cada vertice que tenga al menos 1 arco
				totalArcos.addAll(this.vertices.get(i));	//O(a)	//agrego sus arcos a la lista de todos los arcos
			}
			
		}
		
		return totalArcos.iterator();
			
	}

	//Devuelve un iterador para recorrer todos los arcos de un determinado 
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {	//O(1)
		
		return this.vertices.get(verticeId).iterator();	//O(1)
		
	}

}
