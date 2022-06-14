package TacticToe.game.entity;

public class BoardLocation {
    private int x;
    private int y;
    private int z;

    BoardLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    int getZ() {
        return this.z;
    }
}
