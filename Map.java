import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Repräsentiert die einzelnen Spielfelder anhand von Arrays und speichert die akutellen Spiel-Figur-Positionen.
 * Diese Klasse stellt ausschließlich Methoden zum direkten Verändern und Abfragen der Zustände des Spielaufbaus dar.
 * Weiterführende Operationen und Berechnungen sollten in anderen Klassen erfolgen.
 */
public class Map {
    /**
     * Erstellt eine neue Map-Instanz.
     */
    public Map() {
    }

    /**
     * Prüft, ob sich die angegebene Figur in der Base des zugehörigen Spielers befindet.
     *
     * @param figure Die Figur, für die dies geprüft werden soll.
     * @return True, falls sich die Figur in der Base befindet, sonst False.
     */
    public boolean isFigureInBase(Figure figure) {
        throw new NotImplementedException();
    }

    /**
     * Prüft, ob die angegebene Figur gerade auf der Straße unterwegs ist.
     *
     * @param figure Die Figur, für die dies geprüft werden soll.
     * @return Die Position der Figur auf der Straße. Wenn sich die Figur nicht auf der Straße befindet: -1.
     */
    public int isFigureInStreet(Figure figure) {
        throw new NotImplementedException();
    }

    /**
     * Prüft, ob sich die angegebene Figur im Home des zugehörigen Spielers befindet.
     *
     * @param figure Die Figur, für die dies geprüft werden soll.
     * @return Die Position der Figur im Home, wobei 0 ganz am Anfang und 3 ganz am Ende ist. Wenn sich die Figur nicht im Home befindet: -1.
     */
    public int isFigureInHome(Figure figure) {
        throw new NotImplementedException();
    }

    /**
     * Fragt ab, welche Figur sich an einer angegebenen absoluten Position auf dem Haupt-Spielfeld befindet.
     *
     * @param position Absolute Position auf dem Haupt-Spielfeld.
     * @return Falls sich an dieser Position eine Figur befindet, wird dessen Figure-Instanz zurückgegeben. Sonst Null.
     */
    public Figure getFigureAtStreetPosition(int position) {
        throw new NotImplementedException();
    }

    /**
     * Fragt ab, welche Figur sich an einer angegebenen absoluten Position im Home befindet.
     *
     * @param position Absolute Position im Home.
     * @return Falls sich an dieser Position eine Figur befindet, wird dessen Figure-Instanz zurückgegeben. Sonst Null.
     */
    public Figure getFigureAtHomePosition(int position) {
        throw new NotImplementedException();
    }

    /**
     * Verschiebt die angegebene Figur in die Base des Spielers.
     *
     * @param figure Die Figur, die verschoben werden soll.
     */
    public void moveFigureToBase(Figure figure) {
        throw new NotImplementedException();
    }

    /**
     * Verschiebt die angegebene Figur an eine angegebene Position auf der Straße.
     *
     * @param figure   Die Figur, die verschoben werden soll.
     * @param position Die Position auf der Straße.
     */
    public void moveFigureToStreetPosition(Figure figure, int position) {
        throw new NotImplementedException();
    }

    /**
     * Verschiebt die angegebene Figur an eine angegebene Position im Home des Spielers.
     *
     * @param figure   Die Figur, die verschoben werden soll.
     * @param position Die Position im Home.
     */
    public void moveFigureToHomePosition(Figure figure, int position) {
        throw new NotImplementedException();
    }
}

