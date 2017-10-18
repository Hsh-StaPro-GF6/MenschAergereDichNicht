import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Stellt einen am spiel teilnehmenden Spieler dar.
 */
public class Player {
    private final Map map;
    private final int playerId;

    /**
     * Erstellt eine neue Spieler-Instanz.
     *
     * @param map      Die Instanz der Map.
     * @param playerId Eine eindeutige Spieler-Nummer zwischen von 0-3.
     */
    public Player(Map map, int playerId) {
        this.map = map;
        this.playerId = playerId;
    }

    /**
     * Ruft die Spieler-Nummer ab.
     *
     * @return Die Spieler-Nummer.
     */
    public int getId() {
        return playerId;
    }

    /**
     * Generiert eine Spieler-Farbe und gibt diese zurück.
     * TODO: Anderer Rückgabewert. Greenfoot Color.
     *
     * @return Die Farbe für diesen Spieler.
     */
    public int getColor() {
        throw new NotImplementedException();
    }

    /**
     * Ruft die Figuren dieses Spielers ab.
     *
     * @return Ein Array aller Figuren dieses Spielers.
     */
    public Figure[] getFigures() {
        throw new NotImplementedException();
    }

    /**
     * Ruft ab, ob dieser Spieler alle Figuren im Home hat und somit fertig ist.
     *
     * @return True, wenn wer fertig ist, sonst False.
     */
    public boolean isFinished() {
        throw new NotImplementedException();
    }

    /**
     * Lässt diesen Spieler würfeln und gibt eine Entscheidung mit allen möglichen Auswahlmöglichkeiten zurück.
     *
     * @return Ein neues Entscheidungs-Objekt.
     */
    public Decision rollDice() {
        // TODO: Würfeln, prüfen ob Schlagzwang besteht (wenn canKickFigure() für irgendeine true),
        // TODO: dann für Jede Figur canMoveForward() abfragen, Ergebnis zurückgeben

        throw new NotImplementedException();
    }

    /**
     * Führt anhand der getroffenen Entscheidung den nächsten Spielzug aus.
     *
     * @param decision Die getroffene Entscheidung.
     * @return True, falls der Spieler durch diesen Spielzug gewonnen hat, sonst False.
     */
    public boolean processMove(Decision decision) {
        throw new NotImplementedException();
    }
}
