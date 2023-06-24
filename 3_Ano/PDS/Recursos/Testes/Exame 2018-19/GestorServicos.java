public interface GestorServicos extends Iterable<String> {

	public boolean registaServico(String key, Servico servico);
	public boolean existeServico(String key);
	public Servico eliminaServico(String key);
	public Servico getServico(String key);
	
	public void attatch(GestorListener g);
}
