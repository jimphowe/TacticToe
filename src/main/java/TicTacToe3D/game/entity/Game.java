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

    private boolean isValidMove(int x, int y) {
        return this.board.pieces[x][0][y] == Piece.EMPTY ||
                this.board.pieces[x][1][y] == Piece.EMPTY ||
                this.board.pieces[x][2][y] == Piece.EMPTY;
    }

    public void move(int x, int y, Piece player) {
        if (!isValidMove(x,y)) {
            throw new IllegalArgumentException("The spot chosen must either be empty or be able to push other " +
                    "balls forward without pushing one out");
        }
        else {
            if (this.board.pieces[x][0][y] == Piece.EMPTY) {
                this.board.pieces[x][0][y] = player;
            }
            else if (this.board.pieces[x][1][y] == Piece.EMPTY) {
                this.board.pieces[x][1][y] = this.board.pieces[x][0][y];
                this.board.pieces[x][0][y] = player;
            }
            else {
                this.board.pieces[x][2][y] = this.board.pieces[x][1][y];
                this.board.pieces[x][1][y] = this.board.pieces[x][0][y];
                this.board.pieces[x][0][y] = player;
            }
        }
    }
}
