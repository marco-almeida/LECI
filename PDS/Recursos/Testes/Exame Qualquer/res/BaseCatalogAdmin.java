import java.util.*;

public class BaseCatalogAdmin implements CatalogAdmin {
    private Map<String, Servico> servicos;
    private OpRegistry logger;

    public BaseCatalogAdmin(OpRegistry logger) {
        this.servicos = new LinkedHashMap<>();
        this.logger = logger;
    }

    @Override
    public boolean registarServico(String codigo, Servico servico) {
        Servico previous = servicos.put(codigo, servico);
        logger.update("Registar servico: " + codigo + " " + servico.toString() + " com status = " + (previous == null));
        return previous == null;
    }

    @Override
    public boolean verificarServico(String codigo) {
        return servicos.containsKey(codigo);
    }

    @Override
    public Servico selecionarServico(String codigo) {
        return servicos.get(codigo);
    }

    @Override
    public Servico removerServico(String codigo) {
        logger.update("Remover servico: " + codigo + " " + servicos.get(codigo).toString());
        return servicos.remove(codigo);
    }

    @Override
    public Map<String, Servico> getServicos() {
        return servicos;
    }

    @Override
    public Iterator<String> iterator() {
        return (this).new ServicoIterator<>();
    }
    private class ServicoIterator<K> implements Iterator<K> {
        private int index;

        ServicoIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < servicos.size();
        }

        @Override
        public K next() {
            int i = 0;
            for (String s : servicos.keySet()) {
                if (i == index) {
                    index++;
                    return (K) servicos.get(s).toString();
                }
                i++;
            }
            throw new NoSuchElementException();
        }
    }
}
