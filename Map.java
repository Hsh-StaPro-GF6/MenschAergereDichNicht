import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Repräsentiert die einzelnen Spielfelder anhand von Arrays und speichert die akutellen Spiel-Figur-Positionen.
 * Diese Klasse stellt ausschließlich Methoden zum direkten Verändern und Abfragen der Zustände des Spielaufbaus dar.
 * Weiterführende Operationen und Berechnungen sollten in anderen Klassen erfolgen.
 */
public class Map {
    private final Figure[] base0 = new Figure[4];
    private final Figure[] base1 = new Figure[4];
    private final Figure[] base2 = new Figure[4];
    private final Figure[] base3 = new Figure[4];

    private final Figure[] home0 = new Figure[4];
    private final Figure[] home1 = new Figure[4];
    private final Figure[] home2 = new Figure[4];
    private final Figure[] home3 = new Figure[4];

    private final Figure[] street = new Figure[40];

    private final Figure[][] bases = {base0, base1, base2, base3};
    private final Figure[][] homes = {home0, home1, home2, home3};

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
        int playerId = figure.getPlayer().getId();
        Figure[] base = bases[playerId];

        for (int i = 0; i < base.length; i++)
            if (base[i] == figure)
                return true;

        return false;
    }

    /**
     * Prüft, ob die angegebene Figur gerade auf der Straße unterwegs ist.
     *
     * @param figure Die Figur, für die dies geprüft werden soll.
     * @return Die Position der Figur auf der Straße. Wenn sich die Figur nicht auf der Straße befindet: -1.
     */
    public int isFigureInStreet(Figure figure) {
        for (int i = 0; i < street.length; i++)
            if (street[i] == figure)
                return i;

        return -1;
    }

    /**
     * Prüft, ob sich die angegebene Figur im Home des zugehörigen Spielers befindet.
     *
     * @param figure Die Figur, für die dies geprüft werden soll.
     * @return Die Position der Figur im Home, wobei 0 ganz am Anfang und 3 ganz am Ende ist. Wenn sich die Figur nicht im Home befindet: -1.
     */
    public int isFigureInHome(Figure figure) {
        int playerId = figure.getPlayer().getId();
        Figure[] home = homes[playerId];

        for (int i = 0; i < home.length; i++)
            if (home[i] == figure)
                return i;

        return -1;
    }

    /**
     * Fragt ab, welche Figur sich an einer angegebenen absoluten Position auf dem Haupt-Spielfeld befindet.
     *
     * @param position Absolute Position auf dem Haupt-Spielfeld.
     * @return Falls sich an dieser Position eine Figur befindet, wird dessen Figure-Instanz zurückgegeben. Sonst Null.
     */
    public Figure getFigureAtStreetPosition(int position) {
        return street[position];
    }

    /**
     * Fragt ab, welche Figur sich an einer angegebenen absoluten Position im Home befindet.
     *
     * @param player   Der Spieler, dessen Home geprüft werden soll.
     * @param position Absolute Position im Home.
     * @return Falls sich an dieser Position eine Figur befindet, wird dessen Figure-Instanz zurückgegeben. Sonst Null.
     */
    public Figure getFigureAtHomePosition(Player player, int position) {
        int playerId = player.getId();
        return homes[playerId][position];
    }

    /**
     * Verschiebt die angegebene Figur in die Base des Spielers.
     *
     * @param figure Die Figur, die verschoben werden soll.
     */
    public void moveFigureToBase(Figure figure) {
        int playerId = figure.getPlayer().getId();

        // Figur bereits in der Base?
        if (isFigureInBase(figure))
            return;

        // Figur aus dem Home löschen
        if (isFigureInHome(figure) >= 0) {
            for (int i = 0; i < homes[playerId].length; i++) {
                if (homes[playerId][i] == figure) {
                    homes[playerId][i] = null;
                    break;
                }
            }
        }

        // Figur von der Straße löschen
        if (isFigureInStreet(figure) >= 0) {
            for (int i = 0; i < street.length; i++) {
                if (street[i] == figure) {
                    street[i] = null;
                    break;
                }
            }
        }

        // Figur an erster freier Position in Base platzieren
        boolean moved = false;
        for (int i = 0; i < bases[playerId].length; i++) {
            if (bases[playerId][i] == null) {
                bases[playerId][i] = figure;
                moved = true;
                break;
            }
        }
        assert(moved);
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

