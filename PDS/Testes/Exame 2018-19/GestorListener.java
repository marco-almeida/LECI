import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

//Variation/Mix between Observer and Memento
public class GestorListener{
	private List<String> regists = new ArrayList<>();
    private List<GestorServicos> managers = new ArrayList<>();


    public void addGestor(GestorServicos g){
        this.managers.add(g);
        g.attatch(this);
    }

    public void addMemento(String m) {
        regists.add(m);
    }

    public boolean hasMemento() {
        return !regists.isEmpty();
    }

    public List<String> getAllRegists() {
        List<String> immutablelist = Collections.unmodifiableList(this.regists);
        return immutablelist;
    }
}