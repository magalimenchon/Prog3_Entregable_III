import java.util.HashMap;
import java.util.Iterator;

public class Mapa {

	
	//FIELDS
	
	private Grafo<Integer> grafo;
	private HashMap<Integer,Ciudad> ciudades;
	private HashMap<Integer, Boolean> visitadas;	//clave: id de la ciudad, valor: visitada/no visitada.
	private int cantidadKm;
	
	//CONSTRUCTOR

	public Mapa() {
		this.grafo = new GrafoNoDirigido<Integer>();
		this.ciudades = new HashMap<>();
		this.visitadas = new HashMap<>();
	}

	
	//METHODS
	
	public void addCiudad(Ciudad ciudad) {	
		this.ciudades.put(ciudad.getId(), ciudad);
		this.grafo.agregarVertice(ciudad.getId());
	}
	
	public void borrarCiudad(Ciudad ciudad) {
		this.grafo.borrarVertice(ciudad.getId());
		this.ciudades.remove(ciudad.getId());
	}
	
	public void addRuta(Ciudad origen, Ciudad destino, int kilometros) {
		this.grafo.agregarArco(origen.getId(), destino.getId(), kilometros);
	}

	public void borrarRuta(Ciudad origen, Ciudad destino) {
		this.grafo.borrarArco(origen.getId(), destino.getId());
	}
	
	
	//EXTRA
	
	 /*Busca el camino de forma recursiva, teniendo en cuenta que considera siempre pasar por una
	 * sola balanza. Si origen tiene balanza entonces considero que ya pasa por una en el recorrido. */
	 public Recorrido encontrarCamino(Ciudad origen, Ciudad destino) {

		this.cantidadKm = 0;
		 
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
				int verticeId = it.next();
				visitadas.put(verticeId, false);
		}
		return encontrarMejorCamino(origen.getId(), destino.getId(), 0, 0);
	}
	 
	 /*Busca el camino de forma recursiva, teniendo en cuenta que considera siempre pasar por una
	  * sola balanza. Si origen tiene balanza entonces considero que ya pasa por una en el recorrido.
	  */
	 private Recorrido encontrarMejorCamino(int idCiudadActual, int idCiudadDestino, Integer kmActuales, int conteoBalanzas) {
		
		 Recorrido recorrido = new Recorrido();
		 
		 Ciudad ciudadActual = this.ciudades.get(idCiudadActual);
		 Ciudad ciudadDestino = this.ciudades.get(idCiudadDestino);
		 
		 if(idCiudadActual == idCiudadDestino && conteoBalanzas <= 1){	//llegué a la ciudad destino y no superé las balanzas
			 
				 recorrido.addCiudad(ciudadActual);
				 recorrido.setDistancia(kmActuales);

				 this.cantidadKm = kmActuales;	//llevo la cantidad de km actuales acumulados 
		 }
		 else{	//todavía no llegué al destino
			 
			 if(this.ciudades.get(idCiudadActual).isTieneBalanza())	//Checkeo si tiene balanza la ciudad actual
				 conteoBalanzas++;
			 
			 visitadas.put(idCiudadActual, true); //se marca como visitada
			 
			 Iterator<Integer> iter = this.grafo.obtenerAdyacentes(idCiudadActual);
			 
			 while(iter.hasNext() && conteoBalanzas <= 1 ){ //mientras no supere la cant balanzas ni sea una ciudad ya visitada
				
				 int idCiudadAdyacente = iter.next();
				
				 if(visitadas.get(idCiudadAdyacente) == false){
					 
					 //me agrego al recorrido incrementando km
					 int distanciaActual = grafo.obtenerArco(idCiudadActual, idCiudadAdyacente).getEtiqueta();
					 kmActuales += distanciaActual;
					 
					 if(this.cantidadKm == 0 || //situación inicial
						kmActuales < this.cantidadKm) { //la cantidad de kilometros actuales es menor a la distancia del mejor camino actual
						
						 //me agrego al recorrido y busco el mejor camino en mis adyascentes
						 Recorrido recorridoParcial = this.encontrarMejorCamino(idCiudadAdyacente, idCiudadDestino, kmActuales, conteoBalanzas);
						 recorridoParcial.addCiudad(ciudadActual);
						 
						 //me elimino ya que puedo ir hacia vertices anteriores para seguir otros caminos del que soy adyacente.
						 kmActuales -= distanciaActual;
						 if(this.ciudades.get(idCiudadActual).isTieneBalanza())	//Checkeo si tiene balanza la ciudad actual
							 conteoBalanzas--;
						
						 //decido si mi actual o el parcial es mejor
						 if(recorridoParcial.existeCiudad(ciudadDestino) &&	
							(recorrido.getDistancia() == 0 ||	//situación inicial
							 recorridoParcial.getDistancia() < recorrido.getDistancia())){	//el parcial es mejor que el mejor actual.
							 recorrido = recorridoParcial;
						 }
					 }
				 }
			 }
			 //la desmarco por si tengo que volver a pasar por ella
			 visitadas.put(idCiudadActual, false);
		 }
		return recorrido;
	}

}
