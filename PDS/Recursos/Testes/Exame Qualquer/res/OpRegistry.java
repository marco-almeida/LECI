import java.util.ArrayList;
import java.util.List;

public class OpRegistry {
    private List<String> operations;

    public OpRegistry() {
        this.operations = new ArrayList<>();
    }

    public List<String> getOperations() {
        return operations;
    }

    public void update(String operation) {
        operations.add(operation);
    }

}
