import java.util.Iterator;


public class IteratorArco<T> implements Iterator<Integer>{

	private Iterator<Arco<T>> it;
	
	public IteratorArco(Iterator<Arco<T>> it){ //O(1)
		this.it = it;
	}
	@Override
	public boolean hasNext() { //O(1)
		
		return it.hasNext();
	}

	@Override
	public Integer next() {	//O(1)
		return it.next().getVerticeDestino();
	}

}
