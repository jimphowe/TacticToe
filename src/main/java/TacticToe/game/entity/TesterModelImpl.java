package TacticToe.game.entity;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class TesterModelImpl extends TacticToeModelImpl {
    private final Random random = new Random();
    private final LocationState computerColor = LocationState.GREEN;
    private final LocationState playerColor = LocationState.RED;

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

    public Move getDefendingMove(LocationState player1, LocationState player2) {
        ArrayList<Move> potentialMoves = new ArrayList<>();
        for (Move move : getPossibleMoves()) {
            move(move.x,move.y,move.face,player1);
            if (getWinningMove(player2) == null) {
                potentialMoves.add(move);
            }
            undo();
        }
        if (potentialMoves.size() != 0) {
            return potentialMoves.get(random.nextInt(potentialMoves.size()));
        }
        else {
            return null;
        }
    }

    public Move getWinningMove(LocationState player) {
        for(Move move : getPossibleMoves()) {
            move(move.x,move.y,move.face,player);
            if (hasWon(player)) {
                this.undo();
                return move;
            }
            else {
                this.undo();
            }
        }
        return null;
    }


    public Move getWinInTwo(LocationState player1, LocationState player2) {
        // loop through all computer moves
        for(Move move : getPossibleMoves()) {
            move(move.x,move.y,move.face,player1);
            boolean found = true;
            if(getWinningMove(player2) == null) {
                for (Move inner : getPossibleMoves()) {
                    move(inner.x,inner.y,inner.face,player2);
                    if (getWinningMove(player1) == null) {
                        found = false;
                    }
                    undo();
                }
            }
            else {
                found = false;
            }
            undo();
            if (found) { return move; }
        }
        return null;
    }

    // returns a move where player2 can't win in either 1 or two moves, and if not possible to stop
    // wins in two, minimizes them. Also has a bias towards corner moves
    public Move getBestDefendingMove(LocationState player1, LocationState player2) {
        ArrayList<Move> potentialMoves = new ArrayList<>();
        int minOpponentWins = 100;
        for(Move move : getPossibleMoves()) {
            move(move.x, move.y, move.face,player1);
            int numOpponentWins = getNumWinInTwo(player2, player1);
            if(getWinningMove(player2) == null) {
                if(numOpponentWins == minOpponentWins) {
                    potentialMoves.add(move);
                }
                else if(numOpponentWins < minOpponentWins) {
                    potentialMoves.clear();
                    potentialMoves.add(move);
                    minOpponentWins = numOpponentWins;
                }
            }
            undo();
        }
        if(potentialMoves.size() != 0) {
            //Cool fact ->
            System.out.println("choose from " + potentialMoves.size() + " moves! Opponent ways to win: " + minOpponentWins);
            ArrayList<Move> betterMoves = new ArrayList<>();
            for(Move move : potentialMoves) {
                if(isCornerMove(move)) {
                    betterMoves.add(move);
                }
            }
            if(betterMoves.size() != 0) {
                return betterMoves.get(random.nextInt(betterMoves.size()));
            }
            return potentialMoves.get(random.nextInt(potentialMoves.size()));
        }
        else {
            return null;
        }
    }

    private boolean isCornerMove(Move move) {
        return move.x % 2 == 0 && move.y % 2 == 0;
    }

    private int getNumWinInTwo(LocationState player1, LocationState player2) {
        // loop through all computer moves
        int count = 0;
        for(Move move : getPossibleMoves()) {
            move(move.x,move.y,move.face,player1);
            boolean found = true;
            if(getWinningMove(player2) == null) {
                for (Move inner : getPossibleMoves()) {
                    move(inner.x,inner.y,inner.face,player2);
                    if (getWinningMove(player1) == null) {
                        found = false;
                    }
                    undo();
                }
            }
            else {
                found = false;
            }
            undo();
            if (found) {
                count++;
            }
        }
        return count;
    }

    //returns a move if player1 has a move which stops opponent winning and maximizes their
    //own two in a rows
    public Move getBetterDefendingMove(LocationState player1, LocationState player2) {
        ArrayList<Move> potentialMoves = new ArrayList<>();
        int maxDoubles = -1;
        for(Move move : getPossibleMoves()) {
            move(move.x,move.y,move.face,player1);
            if(getWinningMove(player2) == null) {
                if(getNumDoubles(player1) == maxDoubles) {
                    potentialMoves.add(move);
                }
                else if(getNumDoubles(player1) > maxDoubles) {
                    potentialMoves.clear();
                    potentialMoves.add(move);
                }
            }
            undo();

        }
        if(potentialMoves.size() != 0) {
            return potentialMoves.get(random.nextInt(potentialMoves.size()));
        }
        else {
            return null;
        }
    }

    private int getNumDoubles(LocationState player) {
        int doubles = 0;
        for(ArrayList<BoardLocation> run : super.runs) {
            if((locationToState(run.get(0)).equals(player) && locationToState(run.get(1)).equals(player))
                    || (locationToState(run.get(1)).equals(player) && locationToState(run.get(2)).equals(player))
                    || (locationToState(run.get(0)).equals(player) && locationToState(run.get(2)).equals(player))) {
                doubles++;
            }
        }
        return doubles;
    }

    @Override
    public void restart() {

    }

    @Override
    public void undo() {
        if(previousBoards.size() > 1) {
            this.pieces = previousBoards.get(previousBoards.size()-2);
            previousBoards.remove(previousBoards.size()-1);
        }
    }
}
