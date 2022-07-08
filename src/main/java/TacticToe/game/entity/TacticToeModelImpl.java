package TacticToe.game.entity;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class TacticToeModelImpl implements TacticToeModel {
    // [left-right][front-back][top-bottom]
    public LocationState[][][] pieces = new LocationState[3][3][3];
    ArrayList<LocationState[][][]> previousBoards;
    public String displayMessage = "";
    public String winner = "";
    /*
    // Horizontal
    ArrayList<BoardLocation> a1 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 0, 0), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a2 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 1)));
    ArrayList<BoardLocation> a3 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(1, 0, 2), new BoardLocation(2, 0, 2)));
    // Vertical
    ArrayList<BoardLocation> a4 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(0, 0, 1), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a5 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 0), new BoardLocation(1, 0, 1), new BoardLocation(1, 0, 2)));
    ArrayList<BoardLocation> a6 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 0), new BoardLocation(2, 0, 1), new BoardLocation(2, 0, 2)));
    // Diagonal
    ArrayList<BoardLocation> a7 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 2)));
    ArrayList<BoardLocation> a8 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 0)));
    // Center
    ArrayList<BoardLocation> a9 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(1, 2, 1)));
    ArrayList<BoardLocation> a10 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 1, 1), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a11 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(2, 2, 1)));

    ArrayList<ArrayList<BoardLocation>> runs = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8,
            a9, a10, a11));
     */

    //front top left
    ArrayList<BoardLocation> a1 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(0, 0, 1), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a2 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(0, 1, 0), new BoardLocation(0, 2, 0)));
    ArrayList<BoardLocation> a3 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 0, 0), new BoardLocation(2, 0, 0)));

    //back top right
    ArrayList<BoardLocation> a4 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 2, 0), new BoardLocation(1, 2, 0), new BoardLocation(0, 2, 0)));
    ArrayList<BoardLocation> a5 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 2, 0), new BoardLocation(2, 1, 0), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a6 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 2, 0), new BoardLocation(2, 2, 1), new BoardLocation(2, 2, 2)));

    //back bottom left
    ArrayList<BoardLocation> a7 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 2), new BoardLocation(0, 1, 2), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a8 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 2), new BoardLocation(1, 2, 2), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a9 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 2), new BoardLocation(0, 2, 1), new BoardLocation(0, 2, 0)));

    // front bottom right
    ArrayList<BoardLocation> a10 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(2, 0, 1), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a11 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(1, 0, 2), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a12 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(2, 1, 2), new BoardLocation(2, 2, 2)));

    // faces

    //front
    ArrayList<BoardLocation> a13 =new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 2)));
    ArrayList<BoardLocation> a14 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a15 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 0), new BoardLocation(1, 0, 1), new BoardLocation(1, 0, 2)));
    ArrayList<BoardLocation> a16 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 1)));

    //top
    ArrayList<BoardLocation> a17 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 1, 0), new BoardLocation(2, 2, 0)));
    ArrayList<BoardLocation> a18 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 0), new BoardLocation(1, 1, 0), new BoardLocation(2, 0, 0)));
    ArrayList<BoardLocation> a19 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 0), new BoardLocation(1, 1, 0), new BoardLocation(2, 1, 0)));
    ArrayList<BoardLocation> a20 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 2, 0), new BoardLocation(1, 1, 0), new BoardLocation(1, 0, 0)));

    //left
    ArrayList<BoardLocation> a21 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(0, 1, 1), new BoardLocation(0, 2, 2)));
    ArrayList<BoardLocation> a22 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(0, 1, 1), new BoardLocation(0, 2, 0)));
    ArrayList<BoardLocation> a23 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(0, 1, 1), new BoardLocation(0, 2, 1)));
    ArrayList<BoardLocation> a24 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 0), new BoardLocation(0, 1, 1), new BoardLocation(0, 1, 2)));

    //back
    ArrayList<BoardLocation> a25 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 2), new BoardLocation(1, 2, 1), new BoardLocation(2, 2, 0)));
    ArrayList<BoardLocation> a26 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 0), new BoardLocation(1, 2, 1), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a27 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 2, 0), new BoardLocation(1, 2, 1), new BoardLocation(1, 2, 2)));
    ArrayList<BoardLocation> a28 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 1), new BoardLocation(1, 2, 1), new BoardLocation(2, 2, 1)));

    //right
    ArrayList<BoardLocation> a29 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(2, 1, 1), new BoardLocation(2, 2, 0)));
    ArrayList<BoardLocation> a30 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 0), new BoardLocation(2, 1, 1), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a31 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 1), new BoardLocation(2, 1, 1), new BoardLocation(2, 2, 1)));
    ArrayList<BoardLocation> a32 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 1, 0), new BoardLocation(2, 1, 1), new BoardLocation(2, 1, 2)));

    //bottom
    ArrayList<BoardLocation> a33 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 2), new BoardLocation(1, 1, 2), new BoardLocation(0, 2, 2)));
    ArrayList<BoardLocation> a34 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(1, 1, 2), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a35 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 2), new BoardLocation(1, 1, 2), new BoardLocation(2, 1, 2)));
    ArrayList<BoardLocation> a36 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 2), new BoardLocation(1, 1, 2), new BoardLocation(1, 2, 2)));

    // inner

    //diagonals (corners)
    ArrayList<BoardLocation> a37 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 1, 1), new BoardLocation(2, 2, 2)));
    ArrayList<BoardLocation> a38 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 0), new BoardLocation(1, 1, 1), new BoardLocation(0, 2, 2)));
    ArrayList<BoardLocation> a39 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 2, 0), new BoardLocation(1, 1, 1), new BoardLocation(0, 0, 2)));
    ArrayList<BoardLocation> a40 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 2, 0), new BoardLocation(1, 1, 1), new BoardLocation(2, 0, 2)));

    //diagonals (edges)
    ArrayList<BoardLocation> a41 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 0), new BoardLocation(1, 1, 1), new BoardLocation(1, 2, 2)));
    ArrayList<BoardLocation> a42 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 1, 0), new BoardLocation(1, 1, 1), new BoardLocation(0, 1, 2)));
    ArrayList<BoardLocation> a43 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 2, 0), new BoardLocation(1, 1, 1), new BoardLocation(1, 0, 2)));
    ArrayList<BoardLocation> a44 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 0), new BoardLocation(1, 1, 1), new BoardLocation(2, 1, 2)));
    ArrayList<BoardLocation> a45 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(2, 2, 1)));
    ArrayList<BoardLocation> a46 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(0, 2, 1)));

    //middles
    ArrayList<BoardLocation> a47 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 1, 0), new BoardLocation(1, 1, 1), new BoardLocation(1, 1, 2)));
    ArrayList<BoardLocation> a48 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(1, 2, 1)));
    ArrayList<BoardLocation> a49 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 1, 1), new BoardLocation(1, 1, 1), new BoardLocation(2, 1, 1)));

    ArrayList<ArrayList<BoardLocation>> runs = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8,
    a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27,
    a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44,
    a45, a46, a47, a48, a49));

    // For Debugging
    public String getBoardString() {
        String gameState = "+----------------------\n";
        gameState += "| ∖ " + pieces[0][2][0].toString() + "  " + pieces[1][2][0].toString() + "  " + pieces[2][2][0].toString() + " ∖\n";
        gameState += "|   ∖                     ∖\n";
        gameState += "|     ∖ " + pieces[0][1][0].toString() + "  " + pieces[1][1][0].toString() + "  " + pieces[2][1][0].toString() + " ∖\n";
        gameState += "|       ∖                     ∖\n";
        gameState += "|         ∖ " + pieces[0][0][0].toString() + "  " + pieces[1][0][0].toString() + "  " + pieces[2][0][0].toString() + " ∖\n";
        gameState += "|          ---------------------|\n";
        gameState += "|   " + pieces[0][2][1].toString() + " |" + pieces[1][2][1].toString() + "  " + pieces[2][2][1].toString() + "         |\n";
        gameState += "|         |                     |\n";
        gameState += "|       " + pieces[0][1][1].toString() + "  " + pieces[1][1][1].toString() + "  " + pieces[2][1][1].toString() + "     |\n";
        gameState += "|         |                     |\n";
        gameState += "|         | " + pieces[0][0][1].toString() + "  " + pieces[1][0][1].toString() + "  " + pieces[2][0][1].toString() + " |\n";
        gameState += "|         |                     |\n";
        gameState += " ∖ " + pieces[0][2][2].toString() + "  " + pieces[1][2][2].toString() + "  " + pieces[2][2][2].toString() + "          |\n";
        gameState += "   ∖      |                     |\n";
        gameState += "     ∖ " + pieces[0][1][2].toString() + "  " + pieces[1][1][2].toString() + "  " + pieces[2][1][2].toString() + "      |\n";
        gameState += "       ∖  |                     |\n";
        gameState += "         ∖| " + pieces[0][0][2].toString() + "  " + pieces[1][0][2].toString() + "  " + pieces[2][0][2].toString() + " |\n";
        gameState += "           ---------------------+\n\n";
        return gameState;
    }

    public String getBoardString(LocationState[][][] board) {
        String gameState = "+----------------------\n";
        gameState += "| ∖ " + board[0][2][0].toString() + "  " + board[1][2][0].toString() + "  " + board[2][2][0].toString() + " ∖\n";
        gameState += "|   ∖                     ∖\n";
        gameState += "|     ∖ " + board[0][1][0].toString() + "  " + board[1][1][0].toString() + "  " + board[2][1][0].toString() + " ∖\n";
        gameState += "|       ∖                     ∖\n";
        gameState += "|         ∖ " + board[0][0][0].toString() + "  " + board[1][0][0].toString() + "  " + board[2][0][0].toString() + " ∖\n";
        gameState += "|          ---------------------|\n";
        gameState += "|   " + board[0][2][1].toString() + " |" + board[1][2][1].toString() + "  " + board[2][2][1].toString() + "         |\n";
        gameState += "|         |                     |\n";
        gameState += "|       " + board[0][1][1].toString() + "  " + board[1][1][1].toString() + "  " + board[2][1][1].toString() + "     |\n";
        gameState += "|         |                     |\n";
        gameState += "|         | " + board[0][0][1].toString() + "  " + board[1][0][1].toString() + "  " + board[2][0][1].toString() + " |\n";
        gameState += "|         |                     |\n";
        gameState += " ∖ " + board[0][2][2].toString() + "  " + board[1][2][2].toString() + "  " + board[2][2][2].toString() + "          |\n";
        gameState += "   ∖      |                     |\n";
        gameState += "     ∖ " + board[0][1][2].toString() + "  " + board[1][1][2].toString() + "  " + board[2][1][2].toString() + "      |\n";
        gameState += "       ∖  |                     |\n";
        gameState += "         ∖| " + board[0][0][2].toString() + "  " + board[1][0][2].toString() + "  " + board[2][0][2].toString() + " |\n";
        gameState += "           ---------------------+\n\n";
        return gameState;
    }

    boolean isValidMove(int x,int y) {
        return this.pieces[x][0][y] == LocationState.EMPTY ||
                this.pieces[x][1][y] == LocationState.EMPTY ||
                this.pieces[x][2][y] == LocationState.EMPTY;
    }

    boolean isValidMove(int x,int y,int face) {
        boolean result = false;
        switch (face) {
            case 1:
                result = isValidMove(x,y);
                break;
            case 2:
                this.rotateDown();
                result = isValidMove(x,y);
                this.rotateUp();
                break;
            case 3:
                this.rotateUp();
                result = isValidMove(x,y);
                this.rotateDown();
                break;
            case 4:
                this.rotateRight();
                result = isValidMove(x,y);
                this.rotateLeft();
                break;
            case 5:
                this.rotateLeft();
                result = isValidMove(x,y);
                this.rotateRight();
                break;
            case 6:
                this.rotateUp();
                this.rotateUp();
                result = isValidMove(x,y);
                this.rotateDown();
                this.rotateDown();
                break;
        }
        return result;
    }

    LocationState[][][] copyBoard(LocationState[][][] board) {
        LocationState[][][] copy = new LocationState[3][3][3];
        for (int i = 0; i <= 2; i++) {
            for(int j = 0; j <= 2; j++) {
                System.arraycopy(board[i][j], 0, copy[i][j], 0, 3);
            }
        }
        return copy;
    }

    public void move(int x, int y, LocationState player) {
        if (!isValidMove(x,y)) {
            throw new IllegalArgumentException("The spot chosen must either be empty or be able to push other " +
                    "balls forward without pushing one out");
        }
        else {
            if (this.pieces[x][0][y] == LocationState.EMPTY) {
                this.pieces[x][0][y] = player;
            }
            else if (this.pieces[x][1][y] == LocationState.EMPTY) {
                this.pieces[x][1][y] = this.pieces[x][0][y];
                this.pieces[x][0][y] = player;
            }
            else {
                this.pieces[x][2][y] = this.pieces[x][1][y];
                this.pieces[x][1][y] = this.pieces[x][0][y];
                this.pieces[x][0][y] = player;
            }
        }
    }

    @Override
    public void move(int x, int y, int face, LocationState player) {
        switch (face) {
            case 1:
                //TODO why is this necessary here?... Idk how java works :((( also took forever to find this bug
                this.pieces = copyBoard(this.pieces);
                move(x,y,player);
                break;
            case 2:
                this.rotateDown();
                move(x,y,player);
                this.rotateUp();
                break;
            case 3:
                this.rotateUp();
                move(x,y,player);
                this.rotateDown();
                break;
            case 4:
                this.rotateRight();
                move(x,y,player);
                this.rotateLeft();
                break;
            case 5:
                this.rotateLeft();
                move(x,y,player);
                this.rotateRight();
                break;
            case 6:
                this.rotateUp();
                this.rotateUp();
                move(x,y,player);
                this.rotateDown();
                this.rotateDown();
                break;
            default:
                throw new IllegalArgumentException("Tried to move on an invalid face");
        }
        previousBoards.add(copyBoard(this.pieces));
    }

    // Used in game view template
    public String[][][] getBoard() {
        String[][][] out = new String[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    out[i][j][k] = this.pieces[i][j][k].toString();
                }
            }
        }
        return out;
    }

    private boolean boardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (this.pieces[i][j][k] == LocationState.EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    LocationState locationToState(BoardLocation location) {
        return this.pieces[location.getX()][location.getY()][location.getZ()];
    }

    boolean hasWon(LocationState player) {
        boolean gameOver = false;
        for (int i = 0; i < 6; i++) {
            for(ArrayList<BoardLocation> run : runs) {
                if (locationToState(run.get(0)).equals(player) && locationToState(run.get(1)).equals(player)
                        && locationToState(run.get(2)).equals(player)) {
                    gameOver = true;
                }
            }
            if (i % 2 == 0) {
                this.rotateLeft();
            }
            else {
                this.rotateUp();
            }
        }
        return gameOver;
    }

    @Override
    public boolean isGameOver() {
        return hasWon(LocationState.GREEN) || hasWon(LocationState.RED) || boardFull();
    }

    public void rotateLeft() {
        LocationState[][][] rotated = new LocationState[3][3][3];
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
        LocationState[][][] rotated = new LocationState[3][3][3];
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
        LocationState[][][] rotated = new LocationState[3][3][3];
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
        LocationState[][][] rotated = new LocationState[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[i][j][2] = pieces[i][0][j];
                rotated[i][j][1] = pieces[i][1][j];
                rotated[i][j][0] = pieces[i][2][j];
            }
        }
        this.pieces = rotated;
    }

    @Override
    public abstract void restart();

    @Override
    public abstract void undo();
}
