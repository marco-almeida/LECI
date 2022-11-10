import static java.lang.System.*;
import java.util.Scanner;
import jogos.*;
    
public class JogaJogoDoGalo {
  private static Scanner sin = new Scanner(in);
  public static void main(String[] args) {
    char jogador1 = 'X';
    char jogador2 = 'O';
    JogoDoGalo jogo = new JogoDoGalo(jogador1, jogador2);
    jogo.mostraTabuleiro();
    while (!jogo.terminado()) {
      out.print("(lin col): ");
      int lin = sin.nextInt();
      int col = sin.nextInt();
      jogo.jogada(lin, col);
      jogo.mostraTabuleiro();
    }
    out.println();
    if (!jogo.ultimoJogadorGanhou()) {
      out.println("Jogador "+jogo.ultimoJogador()+" ganhou!");
    } else {
      out.println("Jogo empatado!");
    }
  }
}

