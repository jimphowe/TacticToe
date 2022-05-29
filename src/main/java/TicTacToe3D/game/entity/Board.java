package TicTacToe3D.game.entity;

public class Board {
    // [left-right][front-back][top-bottom]
    public Piece[][][] pieces = new Piece[3][3][3];
    // Arranged like a die 1-front,2-right,3-top,4-bottom,5-left,6-back
    Integer orientation = 1;

    public void setRandomPieces() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    this.pieces[i][j][k] = Piece.randomPiece();
                }
            }
        }
    }

    public Piece[][][] getPieces() {
        return this.pieces;
    }

    public void rotateLeft() {
        Piece[][][] rotated = new Piece[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[i][0][j] = this.pieces[2][i][j];
                rotated[i][1][j] = this.pieces[1][i][j];
                rotated[i][2][j] = this.pieces[0][i][j];
            }
        }
        this.pieces = rotated;
    }

    public void rotateRight() {
        Piece[][][] rotated = new Piece[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[2][i][j] = pieces[i][0][j];
                rotated[1][i][j] = pieces[i][1][j];
                rotated[0][i][j] = pieces[i][2][j];
            }
        }
        this.pieces = rotated;
    }

    public void rotateUp() {
        Piece[][][] rotated = new Piece[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[i][0][j] = this.pieces[i][j][2];
                rotated[i][1][j] = this.pieces[i][j][1];
                rotated[i][2][j] = this.pieces[i][j][0];
            }
        }
        this.pieces = rotated;
    }

    public void rotateDown() {
        Piece[][][] rotated = new Piece[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[i][j][2] = pieces[i][0][j];
                rotated[i][j][1] = pieces[i][1][j];
                rotated[i][j][0] = pieces[i][2][j];
            }
        }
        this.pieces = rotated;
    }
}
