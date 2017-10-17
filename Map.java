import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Repräsentiert die einzelnen Spielfelder anhand von Arrays und speichert die akutellen Spiel-Figur-Positionen.
 */
public class Map {
    public Map() {
    }

    public Boolean isFigureInBase(Figure figure) {
        throw new NotImplementedException();
    }

    public int isFigureInStreet(Figure figure) {
        throw new NotImplementedException();
    }

    public int isFigureInHome(Figure figure) {
        throw new NotImplementedException();
    }

    public Figure getFigureAtStreetPosition(int position) {
        throw new NotImplementedException();
    }

    public Figure getFigureAtHomePosition(int position) {
        throw new NotImplementedException();
    }

    public void moveFigureForward(Figure figure, int fields) {
        throw new NotImplementedException();
    }
}
