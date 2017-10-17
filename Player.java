import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by emilmilow on 17.10.17.
 */
public class Player {
    private Map map;
    private int playerId;

    public Player(Map map, int playerId) {
        this.map = map;
        this.playerId = playerId;
    }

    public int getId() {
        return playerId;
    }

    public int getColor() {
        throw new NotImplementedException();
    }

    public Figure[] getFigures() {
        throw new NotImplementedException();
    }

    public boolean isFinished() {
        throw new NotImplementedException();
    }

    public Decision rollDice() {
        throw new NotImplementedException();
    }

    public boolean moveFigureForward(Decision decision) {
        throw new NotImplementedException();
    }

    public boolean areAllFiguresInBase() {
        throw new NotImplementedException();
    }
}
