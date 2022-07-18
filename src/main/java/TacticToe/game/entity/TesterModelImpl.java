package TacticToe.game.entity;

import java.util.*;
import java.util.stream.Collectors;
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

    // Returns a list off valid moves in the position, in a random order
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
        Collections.shuffle(possibleMoves);
        return possibleMoves;
    }

    private LocationState getPiece(int x, int y, int face) {
        switch (face) {
            case 1:
                return pieces[x][0][y];
            case 2:
                return pieces[x][y][0];
            case 3:
                return pieces[x][y][2];
            case 4:
                return pieces[0][x][y];
            case 5:
                return pieces[2][x][y];
            case 6:
                return pieces[x][2][2-y];
        }
        return null;
    }

    // Returns a list off valid moves in the position, with moves pushing the opponent suggested first
    private ArrayList<Move> getPossibleMovesPush() {
        ArrayList<Move> possiblePushMoves = new ArrayList<>();
        ArrayList<Move> possibleOtherMoves = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(Integer face : IntStream.range(1, 7).toArray()) {
                    if(this.isValidMove(i,j,face)) {
                        Move toAdd = new Move(i,j,face);
                        if (getPiece(i,j,face) == LocationState.RED) {
                            possiblePushMoves.add(toAdd);
                        }
                        else {
                            possibleOtherMoves.add(toAdd);
                        }
                    }
                }
            }
        }
        Collections.shuffle(possiblePushMoves);
        Collections.shuffle(possibleOtherMoves);
        possiblePushMoves.addAll(possibleOtherMoves);
        return possiblePushMoves;
    }

    // Returns a list off valid moves in the position, with corner moves suggested first
    private ArrayList<Move> getPossibleMovesCorner() {
        ArrayList<Move> possibleCornerMoves = new ArrayList<>();
        ArrayList<Move> possibleOtherMoves = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(Integer face : IntStream.range(1, 7).toArray()) {
                    if(this.isValidMove(i,j,face)) {
                        Move toAdd = new Move(i,j,face);
                        if ((i == 0 || i == 2) && (j == 0 || j == 2)) {
                            possibleCornerMoves.add(toAdd);
                        }
                        else {
                            possibleOtherMoves.add(toAdd);
                        }
                    }
                }
            }
        }
        Collections.shuffle(possibleCornerMoves);
        Collections.shuffle(possibleOtherMoves);
        possibleCornerMoves.addAll(possibleOtherMoves);
        return possibleCornerMoves;
    }

    // Returns the first possible move from a shuffles list of all possible moves
    public Move getRandomMove() {
        ArrayList<Move> possibleMoves = getPossibleMoves();
        return possibleMoves.get(0);
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
        boolean found;
        for(Move move : getPossibleMoves()) {
            move(move.x,move.y,move.face,player1);
            found = true;
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
    // wins in two, minimizes them
    public Move getBestDefendingMove(LocationState player1, LocationState player2) {
        Move result = null;
        int checked = 0;
        int minOpponentWins = 100;
        ArrayList<Move> possibleMoves = getPossibleMovesCorner();
        System.out.println("Num possible moves: " + possibleMoves.size());
        for (Move move : possibleMoves) {
            // Caps number of moves to look at, for speed's sake
            if (checked >= 8) {
                return move;
            }
            move(move.x, move.y, move.face,player1);
            if (getWinningMove(player2) == null) {
                checked += 1;
                int numOpponentWins = getNumWinInTwo(player2, player1);
                System.out.println(numOpponentWins);
                if (numOpponentWins == 0) {
                    undo();
                    return move;
                }
                else if (numOpponentWins < minOpponentWins) {
                    result = move;
                    minOpponentWins = numOpponentWins;
                }
            }
            undo();
        }
        System.out.println("Looped through all and returning move");
        if (result != null) {
            undo();
        }
        return result;
    }

    // Picks a center if one is available, otherwise random
    public Move getFirstMove() {
        List<Integer> faces = IntStream.range(1,7).boxed().collect(Collectors.toList());
        for (Integer face : faces) {
            if (isValidMove(1,1,face)) {
                return new Move(1,1,face);
            }
        }
        return getRandomMove();
    }

    private int getNumWinInTwo(LocationState player1, LocationState player2) {
        // loop through all computer moves
        int count = 0;
        boolean found;
        for(Move move : getPossibleMoves()) {
            move(move.x,move.y,move.face,player1);
            found = true;
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
