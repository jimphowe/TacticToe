package TicTacToe3D.game.entity;

public class Game {
    public Board board = new Board();

    public void setBoard() {
        this.board.setRandomPieces();
    }

    public String[][][] getBoard() {
        Piece[][][] pieces = this.board.getPieces();
        String[][][] out = new String[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    out[i][j][k] = pieces[i][j][k].toString();
                }
            }
        }
        return out;
    }
}
