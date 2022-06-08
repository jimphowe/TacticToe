package TicTacToe3D.game.entity;

import java.util.ArrayList;
import java.util.Random;

public class OnePlayerImpl extends TacticToeModelImpl {
    public Integer difficulty = null;
    private final LocationState computerColor = LocationState.GREEN;
    private final LocationState playerColor = LocationState.RED;
    private static final Random random = new Random();

    private Move lvl1Move() {
        TesterModelImpl tester = new TesterModelImpl(this.pieces);
        Move move = tester.getWinningMove();
        if (move == null) {
            move = tester.getRandomMove();
        }
        return move;
    }

    private Move lvl2Move() {
        TesterModelImpl tester = new TesterModelImpl(this.pieces);
        Move move = tester.getWinningMove();
        if (move == null) {
            move = tester.getRandomMove();
        }
        return move;
    }

    private Move lvl3Move() {
        TesterModelImpl tester = new TesterModelImpl(this.pieces);
        Move move = tester.getWinningMove();
        if (move == null) {
            move = tester.getRandomMove();
        }
        return move;
    }

    public void computerMove() {
        Move move = null;
        switch (this.difficulty) {
            case 1:
                move = lvl1Move();
                break;
            case 2:
                move = lvl2Move();
                break;
            case 3:
                move = lvl3Move();
                break;
            default:
        }
        assert move != null;
        move(move.x,move.y,move.face,this.computerColor);
        this.previousBoards.add(copyBoard(this.pieces));
    }

    // Sets the board to start a game (9 Neutral Black pieces in random locations)
    public void setStartingBoard() {
        // Set everything to empty
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    this.pieces[i][j][k] = LocationState.EMPTY;
                }
            }
        }
        // Pick 9 to make Black
        for (int i = 0; i < 9; i++) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);
            int z = random.nextInt(3);
            while (this.pieces[x][y][z] == LocationState.BLACK) {
                x = random.nextInt(3);
                y = random.nextInt(3);
                z = random.nextInt(3);
            }
            this.pieces[x][y][z] = LocationState.BLACK;
        }
        // Set previous boards
        this.previousBoards = new ArrayList<>();
    }

    @Override
    public void restart() {
        this.setStartingBoard();
        this.winner = "";
    }

    @Override
    public void undo() {
        if(previousBoards.size() > 2) {
            this.pieces = previousBoards.get(previousBoards.size()-3);
            previousBoards.remove(previousBoards.size()-1);
            previousBoards.remove(previousBoards.size()-1);
        }
    }
}
