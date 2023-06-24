
package lab03.JGalo; // you may change this package

public interface JGaloInterface {
	char getActualPlayer(); // 'O' or 'X'
	boolean setJogada(int lin, int col);
	boolean isFinished();   // someone wons, or no empty positions
	char checkResult();	// who wons?
}