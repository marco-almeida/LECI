import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;

import pds2020_ap1_v2d.Alojamento;
import pds2020_ap1_v2d.Aventura;
import pds2020_ap1_v2d.BaseCatalogAdmin;
import pds2020_ap1_v2d.CatalogAdmin;
import pds2020_ap1_v2d.CatalogStats;
import pds2020_ap1_v2d.MundoRural;
import pds2020_ap1_v2d.PacoteServicos;
import pds2020_ap1_v2d.Passeio;
import pds2020_ap1_v2d.TipoServico;
import pds2020_ap1_v2d.Transporte;

// Não alterar esta classe
// Mas pode (Deve!) comentar linhas com erros para poder testar o código que for desenvolvendo

public class SenteNatureza { 

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream fl = new PrintStream(new File("SenteNatureza.txt")); 
		test(System.out);  // executa e escreve na consola
		test(fl);		   // executa e escreve no ficheiro
		fl.close();
	}

	private static void test(PrintStream out) {
		
		CatalogAdmin ctgAdmin = new BaseCatalogAdmin();
		
		// alínea a)
		out.println("\nAlínea a) ----------------------------------\n");

		ctgAdmin.registarServico("1001", new Alojamento("Camping Serr&Sol - Tenda 1", "Local para tenda, 2 m2", 40, 1));
		ctgAdmin.registarServico("1002", new Alojamento("Camping Serr&Sol - Tenda 2", "Local para tenda, 3 m2", 50, 2));
		ctgAdmin.registarServico("1003", new Alojamento("Camping Serr&Sol - Tenda 3", "Local para tenda, 5 m2", 70, 3));
		ctgAdmin.registarServico("1004", new Alojamento("Camping Serr&Sol - Bungalow 1", "Bungalow T0", 90, 2));
		ctgAdmin.registarServico("1005", new Alojamento("Camping Serr&Sol - Bungalow 2", "Bungalow T1", 120, 3));
		ctgAdmin.registarServico("2001", new Passeio("Lousã a cavalo", "Passeio a cavalo pelas aldeias de xisto", 100));
		ctgAdmin.registarServico("2002", new Passeio("Lousã TT", "Passeio na serra em 4x4", 320));
		ctgAdmin.registarServico("3001", new Aventura("Escalada", "Escalada em Góis", 80));
		ctgAdmin.registarServico("3002", new Aventura("Descida do rio", "Descida em kayak", 100));
		ctgAdmin.registarServico("3003", new Aventura("Canyoning Ribeira da Pena", "Descida a pé da Ribeira da Pena", 80));
		ctgAdmin.registarServico("4001", new MundoRural("Pastoreio", "Pastor por um dia", 60));
		ctgAdmin.registarServico("4002", new MundoRural("Queijos & Doces", "Faça o seu queijo e doce para o lanche", 80));

		Iterator<String> it = ctgAdmin.iterator();
		while (it.hasNext()) {
			out.println(it.next());
		}
		
		// alínea b)
		out.println("\nAlínea b) ----------------------------------\n");
		PacoteServicos p1 = new PacoteServicos("Lousã Aventura", "Alojamento e aventura na serra");
		p1.add(ctgAdmin.selecionarServico("1005"));
		p1.add(ctgAdmin.selecionarServico("2002"));
		p1.add(ctgAdmin.selecionarServico("3001"));
		p1.add(new Transporte("Serviço de transporte", "Deslocação entre atividades", 40));

		PacoteServicos p2 = new PacoteServicos("Lousã Rural", "Atividades rurais na serra");
		p2.add(ctgAdmin.selecionarServico("2001"));
		p2.add(ctgAdmin.selecionarServico("4001"));
		p2.add(ctgAdmin.selecionarServico("4002"));
		p2.add(new Transporte("Serviço de transporte", "Deslocação entre atividades", 20));

		ctgAdmin.registarServico("8001", p1);
		ctgAdmin.registarServico("8002", p2);

		for (String s : ctgAdmin) { 
			out.println(s);
		}
		
		// alínea c)
		out.println("\nAlínea c) ----------------------------------\n");

		//  CatalogStats ctgStats ... // .. ToDo
		
		out.println("\nPreço médio das atividades (exclui alojamento e pacotes)");
		out.println(ctgStats.getAveragePriveActivities());

		out.println("\nPreço mínimo das atividades de aventura");
		out.println(ctgStats.getMinimumPrice(TipoServico.AVENTURA)); 

		// alineaD(out);  // descomentar após concluir alinea D
		
	}
	
	private static void alineaD(PrintStream out) {
		out.println("\nAlínea d) ----------------------------------\n");
		// .. ToDo
	}

}
