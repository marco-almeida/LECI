import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

public class GestorServicosStandard implements GestorServicos {
	private Map<String,Servico> servicosReg = new TreeMap<>(); //A tree map with all of the reigstered services
	private int numElems = 0;

	private List<GestorListener> listeners = new ArrayList<>();

	@Override
	public Iterator<String> iterator() {
		return (this).new ServicoIterator<String>();
	}

	//Tries to put the key,service pair into the registered services map
	@Override
	public boolean registaServico(String key, Servico servico) {
		try{ 
			this.servicosReg.put(key, servico);
			this.numElems++;

			for(GestorListener listener : this.listeners){
				listener.addMemento("Registered service: " + servico);
			}

			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	//Checks if given key exists in the registered services map
	@Override
	public boolean existeServico(String key) {
		if(this.servicosReg.containsKey(key))
			return true;
		return false;
	}

	//Remove a service from the registered map and return it
	@Override
	public Servico eliminaServico(String key) {
		Servico serv;
		if(!existeServico(key))
			serv = null;
		else{
			serv = this.servicosReg.get(key);
			this.servicosReg.remove(key);
			this.numElems--;
		}
		 

		for(GestorListener listener : this.listeners){
			listener.addMemento("Eliminated service: " + serv);
		}

		return serv;
	}

	//Get a specific service with given key
	@Override
	public Servico getServico(String key) {
		if(!existeServico(key))
			return new NullService(); 

		return this.servicosReg.get(key);
	}

	//Attatch new listener
	public void attatch(GestorListener g){
		this.listeners.add(g);
	}


	//Iterator class
	private class ServicoIterator<T> implements Iterator<T>{
        private int index;

        ServicoIterator(){
            index = 0;
        }

        //Function checks if still have more elements
        public boolean hasNext(){
            return (index < numElems);
        }

        //Function returns the next value in the Vector OR an error message saying we're out of bounds
        public T next(){
            if (hasNext()){
				StringBuilder stBuilder = new StringBuilder();

				String key = servicosReg.keySet().toArray()[index++].toString();
				stBuilder.append("Ref: ");
				stBuilder.append(key);
				stBuilder.append(" - ");
				stBuilder.append(servicosReg.get(key));

				return (T)stBuilder.toString();

			}
            throw new NoSuchElementException("Index out of bounds!");
            
        }

        //Optional remove function
        public void remove(){
            throw new UnsupportedOperationException("Operation not supported!");
        }

    }

}
