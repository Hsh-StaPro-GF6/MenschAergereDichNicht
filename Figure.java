import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
    }

    /**
     * Prüft, ob die Figur gerade auf der Straße unterwegs ist.
     *
     * @return Die Position der Figur auf der Straße. Wenn sich die Figur nicht auf der Straße befindet: -1.
     */
    public int isInStreet() {
        throw new NotImplementedException();
    }

    /**
     * Prüft, ob sich die Figur im Home des zugehörigen Spielers befindet.
     *
     * @return Die Position der Figur im Home, wobei 0 ganz am Anfang und 3 ganz am Ende ist. Wenn sich die Figur nicht im Home befindet: -1.
     */
    public int isInHome() {
        throw new NotImplementedException();
    }

    /**
     * Prüft, ob mit der angegebenen Schritt-Zahl eine Figur gekickt werden würde.
     *
     * @param fields Die Schritt-Zahl.
     * @return True, falls eine Figur gekickt werden würde, sonst False.
     */
    public boolean canKickFigure(int fields) {
        throw new NotImplementedException();
    }

    /**
     * Prüft, ob die Figur mit der angegebenen Würfel-Zahl die Base verlassen könnte.
     *
     * @param fields Die gewürfelte Zahl.
     * @return True, falls die Figur die Base verlassen könnte, sonst False.
     */
    public boolean canLeaveBase(int fields) {
        throw new NotImplementedException();
    }

    /**
     * Prüft, ob die Figur um die angegebene Schritt-Zahl vorwärts-bewergt werden könnte.
     *
     * @param fields Die Schritt-Zahl.
     * @return True, falls die Figur vorwärts-bewegt werden könnte, sonst False.
     */
    public boolean canMoveForward(int fields) {
        throw new NotImplementedException();
    }

    /**
     * Führt für diese Figur einen Spiel-Zug durch.
     *
     * @param fields Die gewürfelte Zahl für diesen Spielzug.
     */
    public void processMove(int fields) {
        // TODO: Bei einer 6 ggf. die Base verlassen, ggf. Figur forwärts bewegen, Figuren ggf. rauskicken (nur in der Straße),
        // TODO: wenn Aktion ungültig Exception werfen (dann muss wer wohl canMoveForward etc. fixen.. :D )

        throw new NotImplementedException();
    }
}
