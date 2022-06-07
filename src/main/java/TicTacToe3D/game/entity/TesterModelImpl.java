package TicTacToe3D.game.entity;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class TesterModelImpl extends TacticToeModelImpl {
    private Random random = new Random();
    private final LocationState computerColor = LocationState.GREEN;

    public TesterModelImpl(LocationState[][][] pieces) {
        super();
        this.pieces = copyBoard(pieces);
        this.previousBoards = new ArrayList<>();
    }

    private ArrayList<Move> getPossibleMoves(LocationState player) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(Integer face : IntStream.range(1, 7).toArray()) {
                    if(this.isValidMove(i,j,face)) {
                        Move toAdd = new Move(i,j,face,player);
                        possibleMoves.add(toAdd);
                    }
                }
            }
        }
        return possibleMoves;
    }

    public Move getRandomMove(LocationState player) {
        ArrayList<Move> possibleMoves = getPossibleMoves(computerColor);
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }

    public Move getWinningMove(LocationState player) {
        for(Move move : getPossibleMoves(player)) {
            move(move.x,move.y,move.face,move.player);
            if (hasWon(player)) {
                this.undo();
                //System.out.println(getBoardString());
                //System.out.println(move.x + "," + move.y + "," + move.face);
                return move;
            } else {
                System.out.println("Before undo " + move.x + "," + move.y + "," + move.face);
                System.out.println(getBoardString());
                System.out.println(this.previousBoards.size());
                this.undo();
                System.out.println("After undo ");
                System.out.println(getBoardString());
                System.out.println(this.previousBoards.size());
            }
        }
        undo();
        return null;
    }

    @Override
    public void restart() {

    }

    @Override
    public void undo() {
        if(previousBoards.size() > 0) {
            this.pieces = previousBoards.get(previousBoards.size()-1);
            previousBoards.remove(previousBoards.size()-1);
        }
    }
}
