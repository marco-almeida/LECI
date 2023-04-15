package lab04.T2;

public class JGaloGame implements JGaloInterface {

    private char actualPlayer;
    private char[][] board;
    private boolean finished;


    public JGaloGame(){//Construtor 
        actualPlayer = 'X';
        board = new char[3][3];
        finished = false;

    }
    @Override
    public char getActualPlayer() {
        return actualPlayer;
    }

    @Override
    public boolean setJogada(int lin, int col) {
        if (lin < 1 || lin > 3 || col < 1 || col > 3) {//A posição não pode estar além das dimenões do tabuleiro
            return false;
        }
        //Verificar se a posição do tabuleiro está vazia
        if (board[lin-1][col-1] != 0) {//Se a posição não está vazia retorna false
            return false;
        }
        board[lin-1][col-1] = actualPlayer; //Agora que fez a jogada é necessário alterar para o próximo jogador
        actualPlayer = (actualPlayer == 'X')? 'O' : 'X';
        checkFinished();;

        return true;
    }

    @Override
    public boolean isFinished() { //Verificar se o tabuleiro tem todas as casas preenchidas
        return finished;
    }

    public void checkFinished(){
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                finished = true;
                return;
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                finished = true;
                return;
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            finished = true;
            return;
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            finished = true;
            return;
        }
    }

    @Override
    public char checkResult() {
        if(!finished) return ' ';
        
        for (int i = 0; i < board.length; i++) {
            //Verificar na mesma linha
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                finished = true;
                return board[i][0]; 
            }
            //Verificar na mesma coluna
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                finished = true;
                return board[0][i];
            }
        }
        //Verificar Diagonais
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            finished = true;
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            finished = true;
            return board[0][2];
        }
        return ' ';
    }
    
}
