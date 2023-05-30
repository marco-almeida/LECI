import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Iterator;

public class GestorServicosMinimo implements GestorServicos {
	private GestorServicos gestor;

	GestorServicosMinimo(GestorServicos gt){
		this.gestor = gt;
	}

	GestorServicosMinimo(){
		this.gestor = null;
	}

	//Returns true if the key corresponds to the given service
	@Override
	public boolean registaServico(String key, Servico servico) {
		if(getServico(key).equals(servico))
			return true;
		return false;
	}


	//Remove a service from the registered map and return it
	@Override
	public Servico eliminaServico(String key) {
		return new NullService();
	}



	@Override
	public Iterator<String> iterator() {
		return this.gestor.iterator();
	}

	//Checks if given key exists in the registered services map
	@Override
	public boolean existeServico(String key) {
		return this.gestor.existeServico(key);
	}

	//Get a specific service with given key
	@Override
	public Servico getServico(String key) {
		return this.gestor.getServico(key);
	}

	//Attatch new listener
	public void attatch(GestorListener g){
		this.gestor.attatch(g);
	}
}
