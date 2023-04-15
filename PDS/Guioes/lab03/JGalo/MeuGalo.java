package lab03.JGalo;

public class MeuGalo implements JGaloInterface {
    private final char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
    private int play;
    private final char player1;
    private final char player2;

    public MeuGalo(String[] args) {
        this.play = 0;
        if (args.length == 0) {
            player1 = 'X';
            player2 = 'O';
        } else {
            player1 = args[0].charAt(0);
            player2 = player1 == 'X' ? 'O' : 'X';
        }
    }

    @Override
    public char getActualPlayer() {
        if (play % 2 == 0) {
            return player1;
        }
        return player2;
    }

    @Override
    public boolean setJogada(int lin, int col) {
        if (board[lin - 1][col - 1] == ' ') {
            board[lin - 1][col - 1] = getActualPlayer();
            play++;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFinished() {
        if (play == 9) {
            return true;
        }
        return checkHorizontal() != ' ' || checkVertical() != ' ' || checkDiagonal() != ' ';
    }

    private char checkHorizontal() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != ' ') {
                return board[i][0];
            }
        }
        return ' ';
    }

    private char checkVertical() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != ' ') {
                return board[0][i];
            }
        }
        return ' ';
    }

    private char checkDiagonal() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ')
                || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ')) {
            return board[1][1];
        }
        return ' ';
    }

    @Override
    public char checkResult() {
        char horizontalResult = checkHorizontal();
        char verticalResult = checkVertical();
        if (horizontalResult != ' ')
            return horizontalResult;

        if (verticalResult != ' ')
            return verticalResult;

        return checkDiagonal();
    }
}
