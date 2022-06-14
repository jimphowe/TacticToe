package TacticToe.game.entity;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class TesterModelImpl extends TacticToeModelImpl {
    private final Random random = new Random();
    private final LocationState computerColor = LocationState.GREEN;

    public TesterModelImpl(LocationState[][][] pieces) {
        this.pieces = copyBoard(pieces);
        this.previousBoards = new ArrayList<>();
        this.previousBoards.add(copyBoard(this.pieces));
    }

    private ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(Integer face : IntStream.range(1, 7).toArray()) {
                    if(this.isValidMove(i,j,face)) {
                        Move toAdd = new Move(i,j,face);
                        possibleMoves.add(toAdd);
                    }
                }
            }
        }
        return possibleMoves;
    }

    public Move getRandomMove() {
        ArrayList<Move> possibleMoves = getPossibleMoves();
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }

    public Move getWinningMove() {
        for(Move move : getPossibleMoves()) {
            move(move.x,move.y,move.face,this.computerColor);
            if (hasWon(this.computerColor)) {
                this.undo();
                return move;
            }
            else {
                this.undo();
            }
        }
        return null;
    }

    @Override
    public void restart() {

    }

    @Override
    public void undo() {
        //System.out.println("BEFORE UNDO:");
        //for (LocationState[][][] board : this.previousBoards) {
        //    System.out.println(getBoardString(board));
        //}
        if(previousBoards.size() > 1) {
            this.pieces = previousBoards.get(previousBoards.size()-2);
            previousBoards.remove(previousBoards.size()-1);
        }
        //System.out.println("AFTER UNDO:");
        //for (LocationState[][][] board : this.previousBoards) {
        //    System.out.println(getBoardString(board));
        //}
    }
}
