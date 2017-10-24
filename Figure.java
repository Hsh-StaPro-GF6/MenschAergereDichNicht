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
        return map.isFigureInHome(this);
    }

    /**
     * Prüft, ob mit der angegebenen Schritt-Zahl eine Figur gekickt werden würde.
     *
     * @param fields Die Schritt-Zahl.
     * @return True, falls eine Figur gekickt werden würde, sonst False.
     */
    public boolean canKickFigure(int fields) {
        int pos = this.isInStreet();
        if (pos == -1)
            return false;
        if (map.getFigureAtStreetPosition(pos + prepare(fields)) != null)
            return true;
        return false;
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
        int pos = this.isInStreet();
        return !(pos == -1 || (pos + fields) <= (this.getPlayer().getEnd() + 4));
    }

    /**
     * Führt für diese Figur einen Spiel-Zug durch.
     *
     * @param fields Die gewürfelte Zahl für diesen Spielzug.
     */
    public void processMove(int fields) {
        System.out.println("ALALALALALALALALALLALA1" + this.canMoveForward(fields) + " - " + this.isInBase() + " - " + !this.canLeaveBase(fields));




        if (!(this.canMoveForward(fields)) && (this.isInBase() && !this.canLeaveBase(fields)))
            return;


        System.out.println("ALALALALALALALALALLALA2");
        //Kickt eine Figur vom Spielfeld falls möglich
        if (canKickFigure(fields))
            map.moveFigureToBase(map.getFigureAtStreetPosition(this.isInStreet() + prepare(fields)));

        System.out.println("ALALALALALALALALALLALA3");

        //Verlässt die Base
        if (canLeaveBase(fields)) {
            map.moveFigureToStreetPosition(this, this.getPlayer().getStart());
            System.out.println("ALALALALALALALALALLALA4");
            return;
        }

        //Geht ins Home
        //if ((this.isInStreet() + fields) > this.getPlayer().getEnd()) {
        if (checkEnd(fields, this.getPlayer().getEnd())) {
            map.moveFigureToHomePosition(this, (this.getPlayer().getEnd() - (this.isInStreet() + prepare(fields))));
            System.out.println("ALALALALALALALALALLALA5");
            return;
        }

        //ToDo Check ob Figuren davor
        if (isInHome() != -1) {
            map.moveFigureToHomePosition(this, isInHome() + prepare(fields));
            System.out.println("ALALALALALALALALALLALA6");
            return;
        }
        System.out.println("ALALALALALALALALALLALA7");
        //Normales bewegen ohne Zwischenfall
        map.moveFigureToStreetPosition(this, this.isInStreet() + prepare(fields));
    }

    public boolean checkEnd(int fields, int end) {
        return (this.isInStreet() <= end && (this.isInStreet() + fields) > end);
    }

    public int prepare(int fields) {
        int ret = fields;
        if (this.isInStreet() + fields > 39)
            ret = (this.isInStreet() + fields - 40) - this.isInStreet();
        return ret;
    }
}
