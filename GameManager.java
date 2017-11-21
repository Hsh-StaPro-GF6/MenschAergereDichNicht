/**
 * Haupt-Klasse, die einfache Methoden zum Verwalten des Spiels bereitstellt.
 */
public class GameManager {

    private final Map map;
    private final Player[] players = new Player[4];

    private int currentPlayer = 0;

    private int sixRepeatCount = 0;
    private int leaveBaseRepeatCount = 0;

    /**
     * Instanziert einen neuen GameManager. Dieser sollte nur einmal existieren.
     */
    public GameManager() {
        map = new Map();
        players[0] = new Player(map, 0, 0, 39);
        players[1] = new Player(map, 1, 10, 9);
        players[2] = new Player(map, 2, 20, 19);
        players[3] = new Player(map, 3, 30, 29);

        resetGame();
    }

    /**
     * Ruft das Map-Objekt ab.
     *
     * @return Die Map.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Ruft alle Spieler des Spiels ab.
     *
     * @return Array mit allen Spielern.
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Ruft den Spieler ab, der gerade an der Reihe ist.
     *
     * @return Der Spieler, der gerade an der Reihe ist.
     */
    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    /**
     * Setzt das Spiel auf den Anfangszustand zurück.
     */
    public void resetGame() {
        for (Player player : players)
            for (Figure figure : player.getFigures())
                map.moveFigureToBase(figure);
    }

    /**
     * Lässt den aktuellen Spieler einmal würfeln und gibt ein Objekt zurück,
     * das die Entscheidung, die der Spieler nun treffen muss, wiederspiegelt.
     *
     * @return Ein neues Entscheidungs-Objekt.
     */
    public Decision rollDice() {
        return players[currentPlayer].rollDice();
    }

    /**
     * Führt den nächsten Spielzug des aktuellen Spielers aus, nachdem dieser eine Entscheidung getroffen hat
     * und wechselt dannach zum nächsten Spieler, der an der Reihe ist.
     *
     * @param decision Die getroffene Entscheidung für diesen Spielzug.
     * @return True, falls der Spieler durch diesen Spielzug gewonnen hat, sonst False.
     */
    // TODO: Ende implementieren
    public boolean exertDecision(Decision decision) {
        boolean won = players[currentPlayer].processMove(decision);
        int figureCountInBase = map.getFigureCountInBase(players[currentPlayer]);

        int figureCountAtEndOfHome = 0;
        for (int i = 3; i > 0; i--) {
            if (map.getFigureAtHomePosition(players[currentPlayer], i) == null)
                break;
            figureCountAtEndOfHome++;
        }

        boolean repeat = !won && ((decision.getFields() == 6 && sixRepeatCount++ < 2) || (figureCountInBase + figureCountAtEndOfHome == 4 && leaveBaseRepeatCount++ < 2));

        // Bei 6 nochmal würfeln
        if (!repeat) {
            sixRepeatCount = 0;
            leaveBaseRepeatCount = 0;
            do {
                if (currentPlayer == 3)
                    currentPlayer = 0;
                else
                    currentPlayer++;
            } while (players[currentPlayer].isFinished());
        }

        return won;
    }
}
