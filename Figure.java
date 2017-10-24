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

        int pos = this.isInStreet();
        if (pos == -1)
            return false;
        return (map.getFigureAtStreetPosition(pos + prepare(fields)) != null);
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
        int stepsToHome;
        int pend = this.getPlayer().getEnd();
        int inHomePos;

        // In Base?
        if (this.isInBase())
            return false;

        // Auf der Straße?
        if(isInStreet() != -1) {
            stepsToHome = (pend - pos) < 0 ? pend+(40-pos) : pend-pos;

                if (stepsToHome + 4 < fields)
                    return false;
                inHomePos = fields - stepsToHome;
                for (int i = 0; i < inHomePos; i++) {
                    if (map.getFigureAtHomePosition(this.getPlayer(), i) != null)
                        return false;
                }

            return true;
        }

        //Im Home?
        if (isInHome() != -1) {
            int remaining = 4 - (isInHome() + 1);
            if (remaining < fields)
                return false;
            for (int i = isInHome(); i <= isInHome() + fields; i++) {
                if (map.getFigureAtHomePosition(this.getPlayer(), i) != null)
                    return false;
            }
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

        if (this.canMoveForward(fields)) {

            int Waypoint = prepare(fields);
            System.out.println("Waypoint = " + Waypoint);

            System.out.println("ALALALALALALALALALLALA2");



            System.out.println("ALALALALALALALALALLALA3");

            //Im home vorwärts bewegen
            if (isInHome() != -1) {
                map.moveFigureToHomePosition(this, isInHome() + fields);
                System.out.println("ALALALALALALALALALLALA6");
                return;
            }

            int stepsToHome = (this.getPlayer().getEnd() - this.isInStreet()) < 0 ? this.getPlayer().getEnd()+(40-this.isInStreet()) : this.getPlayer().getEnd()-this.isInStreet();
            //Geht ins Home
            if (this.isInStreet() + Waypoint > this.getPlayer().getEnd() && stepsToHome < fields) {
                System.out.println("ALALALALALALALALALLALA5");
                map.moveFigureToHomePosition(this, ((this.isInStreet() + Waypoint) - this.getPlayer().getEnd() - 1));
                return;
            }

            //Kickt eine Figur vom Spielfeld falls möglich
            if (canKickFigure(fields))
                map.moveFigureToBase(map.getFigureAtStreetPosition(this.isInStreet() + Waypoint));

            System.out.println("ALALALALALALALALALLALA7");
            //Normales bewegen ohne Zwischenfall
            map.moveFigureToStreetPosition(this, this.isInStreet() + Waypoint);

        }

        if (this.isInBase() && this.canLeaveBase(fields)) {

            if (canKickFigure(this.getPlayer().getStart()))
                map.moveFigureToBase(map.getFigureAtStreetPosition(this.getPlayer().getStart()));

            map.moveFigureToStreetPosition(this, this.getPlayer().getStart());
            System.out.println("ALALALALALALALALALLALA4");
            return;
        }
    }

    //ToDo: Bekannte fehler: 0 geht nicht in Home; Figuren in Home werden nicht weiter bewegt

    public int prepare(int fields) {
        int ret = fields;
        if (fields > 6) {
            System.out.println("FUUUUUUUUUU HUSO ERROR!");
            return 0;
        }
        if (this.isInStreet() + fields > 39) {
            ret = fields - 40;
            System.out.println("Achtung Wechsel von: " + this.isInStreet() + " zu: " + ret + " bei gewürfelt: " + fields + "Also neues feld laut berechnung: " + (this.isInStreet() + ret));
        }
        return ret;
    }
}
