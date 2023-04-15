package lab04.T1;

public class Main implements JGaloInterface {
    private char player;
    private char winner;
    private int num_move;
    private char[][] table = new char[3][3];

    public Main(char player) {
        this.player = player;
        this.num_move = 0;
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                table[lin][col] = ' ';
            }
        }
    }

    public Main() {
        this('X');
    }

    @Override
    public char getActualPlayer() {
        return this.player;
    }

    @Override
    public boolean setJogada(int lin, int col) {
        if (table[lin - 1][col - 1] == ' ') {
            table[lin - 1][col - 1] = this.player;

            if (this.player == 'X') {
                this.player = 'O';
                num_move++;
            } else {
                this.player = 'X';
                num_move++;
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean isFinished() {

        for (int lin = 0; lin < 3; lin++) {
            if ((table[lin][0] == 'X' && table[lin][1] == 'X' && table[lin][2] == 'X')) {
                this.winner = 'X';
                return true;

            } else if ((table[lin][0] == 'O' && table[lin][1] == 'O' && table[lin][2] == 'O')) {
                this.winner = 'O';
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if ((table[0][col] == 'X' && table[1][col] == 'X' && table[2][col] == 'X')) {
                this.winner = 'X';
                return true;

            } else if ((table[0][col] == 'O' && table[1][col] == 'O' && table[2][col] == 'O')) {
                this.winner = 'O';
                return true;
            }
        }

        if ((table[0][0] == 'X' && table[1][1] == 'X' && table[2][2] == 'X')) {
            this.winner = 'X';
            return true;

        } else if ((table[0][0] == 'O' && table[1][1] == 'O' && table[2][2] == 'O')) {
            this.winner = 'O';
            return true;
        }

        if ((table[2][0] == 'X' && table[1][1] == 'X' && table[0][2] == 'X')) {
            this.winner = 'X';
            return true;

        } else if ((table[2][0] == 'O' && table[1][1] == 'O' && table[0][2] == 'O')) {
            this.winner = 'O';
            return true;
        }

        if (num_move == 9) {
            return true;
        }

        this.winner = ' ';
        return false;
    }

    @Override
    public char checkResult() {
        return this.winner;
        // returns winner
    }

}
