package TicTacToe3D.game.entity;

public class Move {
    int x,y;
    Integer face;
    LocationState player;

    Move(int x, int y, Integer face, LocationState player) {
        this.x = x;
        this.y = y;
        this.face = face;
        this.player = player;
    }
}
