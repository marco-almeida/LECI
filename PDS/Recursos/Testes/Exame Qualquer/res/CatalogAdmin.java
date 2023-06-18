import java.util.Map;

public interface CatalogAdmin extends Iterable<String> {

	public boolean registarServico(String codigo, Servico servico);
	public boolean verificarServico(String codigo);
	public Servico selecionarServico(String codigo);
	public Servico removerServico(String codigo);
	public Map<String, Servico> getServicos();
	
}
