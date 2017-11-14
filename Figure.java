/**
 * Stellt eine Spiel-Figur eines Spielers dar.
 * Diese Methoden stellt spezifische Methoden zum Abfragen des Zustands einer Spielfigur
 * und zum validieren und ausführen des nächsten Spielzugs bereit.
 */
public class Figure {
    private final Map map;
    private final Player player;

    /**
     * Instanziert eine Spielfigur des Spielers.
     * Diese sollte anschließend mit Map.moveFigureToBase() in die Base gesetzt werden.
     *
     * @param map    Die Instanz der Map.
     * @param player Die Instanz des Spielers, dem diese Figur gehört.
     */
    public Figure(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    /**
     * Ruft den Spieler ab, dem diese Figur gehört
     *
     * @return Der Spieler, dem diese Figur gehört.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Prüft, ob sich die Figur in der Base des zugehörigen Spielers befindet.
     *
     * @return True, falls sich die Figur in der Base befindet, sonst False.
     */
    public boolean isInBase() {
        return map.isFigureInBase(this);
    }

    /**
     * Prüft, ob die Figur gerade auf der Straße unterwegs ist.
     *
     * @return Die Position der Figur auf der Straße. Wenn sich die Figur nicht auf der Straße befindet: -1.
     */
    public int isInStreet() {
        return map.isFigureInStreet(this);
    }

    /**
     * Prüft, ob sich die Figur im Home des zugehörigen Spielers befindet.
     *
     * @return Die Position der Figur im Home, wobei 0 ganz am Anfang und 3 ganz am Ende ist. Wenn sich die Figur nicht im Home befindet: -1.
     */
    public int isInHome() {
        System.out.println("Figure.isInHome");

        return map.isFigureInHome(this);
    }

    /**
     * Prüft, ob mit der angegebenen Schritt-Zahl eine Figur gekickt werden würde.
     *
     * @param fields Die Schritt-Zahl.
     * @return True, falls eine Figur gekickt werden würde, sonst False.
     */
    public boolean canKickFigure(int fields) {
        if (this.isInBase())
            return (this.canLeaveBase(fields) && map.getFigureAtStreetPosition(getPlayer().getStart()) != null);

        int position = this.isInStreet();
        if (position == -1)
            return false;

        int newPosition = (position + fields) % 40;
        Figure figureAtNewPosition = map.getFigureAtStreetPosition(newPosition);

        return (figureAtNewPosition != null && figureAtNewPosition.getPlayer() != player);
    }

    /**
     * Prüft, ob die Figur mit der angegebenen Würfel-Zahl die Base verlassen könnte.
     *
     * @param fields Die gewürfelte Zahl.
     * @return True, falls die Figur die Base verlassen könnte, sonst False.
     */
    public boolean canLeaveBase(int fields) {
        return (fields == 6 && this.isInBase());
    }

    /**
     * Prüft, ob die Figur um die angegebene Schritt-Zahl vorwärts-bewergt werden könnte.
     *
     * @param fields Die Schritt-Zahl.
     * @return True, falls die Figur vorwärts-bewegt werden könnte, sonst False.
     */
    public boolean canMoveForward(int fields) {
        int position = this.isInStreet();
        int endPosition = this.getPlayer().getEnd();

        // In Base?
        if (this.isInBase())
            return false;

        // Auf der Straße?
        if (isInStreet() != -1) {
            int stepsToHome = (endPosition - position) < 0 ? endPosition + (40 - position) : endPosition - position;

            // Man darf nicht über das Ende des Homes hinaus gehen
            if (stepsToHome + 4 < fields)
                return false;

            // Geht er ins Home?
            if (stepsToHome + 1 <= fields) {
                // Stehen Figuren im Home im Weg?
                int newPositionInHome = fields - (stepsToHome + 1);
                for (int i = 0; i <= newPositionInHome; i++)
                    if (map.getFigureAtHomePosition(this.getPlayer(), i) != null)
                        return false;
                return true;
            }

            return true;
        }

        // Im Home?
        if (isInHome() != -1) {
            int remaining = 4 - (isInHome() + 1);
            if (remaining < fields)
                return false;
            for (int i = isInHome() + 1; i <= isInHome() + fields; i++) {
                if (map.getFigureAtHomePosition(this.getPlayer(), i) != null)
                    return false;
            }

            return true;
        }
        return false;
    }

    /**
     * Führt für diese Figur einen Spiel-Zug durch.
     *
     * @param fields Die gewürfelte Zahl für diesen Spielzug.
     */
    public void processMove(int fields) {
        System.out.println("Figure.processMove");
        System.out.println("fields = [" + fields + "]");

        // Base verlassen?
        if (this.canLeaveBase(fields)) {
            if (canKickFigure(fields))
                map.moveFigureToBase(map.getFigureAtStreetPosition(this.getPlayer().getStart()));

            map.moveFigureToStreetPosition(this, this.getPlayer().getStart());
            System.out.println("Base verlassen.");
            return;
        }

        if (this.canMoveForward(fields)) {
            // Im Home vorwärts bewegen
            if (isInHome() != -1) {
                map.moveFigureToHomePosition(this, isInHome() + fields);
                System.out.println("Im Home vorwärts bewegt.");
                return;
            }

            // Auf der Straße vorwärts bewegen
            if (this.isInStreet() != -1) {
                int position = this.isInStreet();
                int endPosition = this.getPlayer().getEnd();

                int newPositionOnStreet = (position + fields) % 40;
                System.out.println("NewPositionOnStreet = " + newPositionOnStreet);

                int stepsToHome = (endPosition - position) < 0 ? endPosition + (40 - position) : endPosition - position;
                System.out.println("stepsToHome = " + stepsToHome);

                // Geht ins Home
                if (stepsToHome + 1 <= fields) {
                    int newPositionInHome = fields - (stepsToHome + 1);
                    map.moveFigureToHomePosition(this, newPositionInHome);

                    System.out.println("Ins Home gegangen. Pos: " + newPositionInHome);
                    return;
                }

                // Eine Figur vom Spielfeld kicken, falls möglich
                if (canKickFigure(fields))
                    map.moveFigureToBase(map.getFigureAtStreetPosition(newPositionOnStreet));

                // Normales Bewegen ohne Zwischenfall
                map.moveFigureToStreetPosition(this, newPositionOnStreet);
            }
        }
    }
}
