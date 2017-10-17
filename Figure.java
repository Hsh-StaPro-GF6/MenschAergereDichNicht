import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Stellt eine Spiel-Figur eines spielers dar.
 */
public class Figure {
    private final Map map;
    private final Player player;

    public Figure(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean getInBase() {
        throw new NotImplementedException();
    }

    public int isInStreet() {
        throw new NotImplementedException();
    }

    public int isInHome() {
        throw new NotImplementedException();
    }

    public boolean canKickFigure(int fields) {
        throw new NotImplementedException();
    }

    public boolean canMoveForward(int fields) {
        throw new NotImplementedException();
    }
}
