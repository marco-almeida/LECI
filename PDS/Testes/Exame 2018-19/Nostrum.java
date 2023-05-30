import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

// Nao alterar esta classe
// Mas pode (deve!) comentar linhas com erros para poder testar o codigo que for desenvolvendo

public class Nostrum {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream fl = new PrintStream(new File("solucao.txt")); 	
		test(System.out);  // executa e escreve na consola
		test(fl);		   // executa e escreve no ficheiro
		fl.close(); 
	}

	private static void test(PrintStream out) {
		
		GestorServicos gest1 = new GestorServicosStandard();
		
		// alinea a)
		out.println("\nAlinea a) ----------------------------------\n");

		gest1.registaServico("101", new TV("Basico", "Canais essenciais", 14.50, 20));
		gest1.registaServico("102", new TV("Mix", "Oferta variada de canais", 23.00, 80));
		gest1.registaServico("103", new TV("Premium", "Tudo o que possa imaginar. Inclui canais TV cine", 45.00, 120));
		gest1.registaServico("104", new Net("Fast", "Para uso domestico", 20.00, "100Mb"));
		gest1.registaServico("105", new Net("Fast++", "Para uso empresarial", 38.00, "1Gb"));
		gest1.registaServico("106", new Voz("Nacional", "Chamadas gratis para todas as redes nacionais", 12.00));
		gest1.registaServico("107", new Voz("Europa+", "Chamadas gratis na uniao europeia", 22.00));

		Iterator<String> it = gest1.iterator();
		while (it.hasNext()) {
			out.println(it.next());
		}
		
		// alinea b)
		out.println("\nAlinea b) ----------------------------------\n");
		Pacote c1 = new Pacote("TV+Net+Voz", "Pacote standard", 12);
		c1.add(gest1.getServico("102"));
		c1.add(gest1.getServico("104"));
		c1.add(gest1.getServico("106"));
		
		Pacote c2 = new Pacote("TV+Voz", "Pacote economico", 12);
		c2.add(gest1.getServico("101"));
		c2.add(new Voz("Nacional+", "Nacionais p/ todas as redes + Europa rede fixa", 10));
		
		gest1.registaServico("110", c1);
		gest1.registaServico("111", c2);

		for (String s : gest1) { 
			out.println(s);
		}
		
		// alinea c)
		out.println("\nAlinea c) ----------------------------------\n");
		Servico s1 = gest1.eliminaServico("101"); // existe
		Servico s2 = gest1.eliminaServico("007"); // nao existe
		out.println("Servicos retirados no GestorServicosStandard: \n\t" + s1 + "\n\t" + s2);

		GestorServicos gest2 = new GestorServicosMinimo(gest1); // deve permitir tambem o construtor GestorServicosMinimo()
		s1 = gest2.eliminaServico("110"); // existe
		s2 = gest2.eliminaServico("007"); // nao existe
		out.println("Servicos retirados no GestorServicosMinimo: \n\t" + s1 + "\n\t" + s2);
		for (String s : gest2) {
			out.println(s);
		}	
		
		alineaD(out);  // descomentar apos concluir alinea D
	}
	
	// remover em dist
	private static void alineaD(PrintStream out) {
		// alinea d)	
		out.println("\nAlinea d) ----------------------------------\n");

		GestorListener listener = new GestorListener();
		GestorServicos gest1 = new GestorServicosStandard();

		gest1.attatch(listener);

		gest1.registaServico("101", new TV("Basico", "Canais essenciais", 14.50, 20));
		gest1.registaServico("102", new TV("Mix", "Oferta variada de canais", 23.00, 80));
		gest1.registaServico("103", new TV("Premium", "Tudo o que possa imaginar. Inclui canais TV cine", 45.00, 120));
		
		gest1.eliminaServico("101"); // existe
		gest1.eliminaServico("007"); // nao existe

		List allSaves = listener.getAllRegists();
		for(int i = 0; i < allSaves.size() ; i++){
			out.println(allSaves.get(i));
		}
	}
	
}
