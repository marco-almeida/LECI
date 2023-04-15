package lab04.T2; // you may change this package

public interface JGaloInterface {
	public char getActualPlayer(); // 'O' or 'X'
	public boolean setJogada(int lin, int col);
	public boolean isFinished();   // someone wons, or no empty positions
	public char checkResult();	// who wons?
}