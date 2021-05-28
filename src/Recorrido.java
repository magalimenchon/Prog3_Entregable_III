import java.util.ArrayList;


public class Recorrido {

	
	//FIELDS

	private ArrayList<Ciudad> camino;
	private Integer distancia;
	
	
	//CONSTRUCTOR

	public Recorrido() {
		this.camino = new ArrayList<>();
		this.distancia = 0;
	}
	
	
	//METHODS
	
	public void addCiudad(Ciudad ciudad){
		this.camino.add(0, ciudad);
	}
	
	public boolean existeCiudad(Ciudad ciudad){
		return this.camino.contains(ciudad);
	}
	
	
	//GETTERS & SETTERS
	
	public Integer getDistancia() {
		return distancia;
	}


	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}


	public ArrayList<Ciudad> getCamino() {
		return camino;
	}
	
	//OVERRIDES
	
	@Override
	public String toString(){
		if(!this.camino.isEmpty()){
			return "Recorrido: " + this.getCamino() + " con distancia total: " + this.getDistancia() + "km";
		}
		else return "No hay camino entre " + this.getCamino();
	}
	
}
