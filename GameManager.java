import greenfoot.*;

/**
 * Haupt-Klasse, die einfache Methoden zum Verwalten des Spiels bereitstellt.
 */
public class GameManager {
    private final GameMember member0;
    private final GameMember member1;
    private final GameMember member2;
    private final GameMember member3;

    private final Map map;
    private final Player[] players = new Player[4];

    private int currentPlayer = -1;
    private Decision currentDecision = null;

    private int sixRepeatCount = 0;
    private int leaveBaseRepeatCount = 0;

    /**
     * Instanziert einen neuen GameManager. Dieser sollte nur einmal existieren.
     */
    public GameManager(GameMember member0, GameMember member1, GameMember member2, GameMember member3) {
        System.out.println("--- NEUES SPIEL ---");

        this.member0 = member0;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;

        map = new Map();

        players[0] = new Player(map, 0, 0, 39, member0);
        players[1] = new Player(map, 1, 10, 9, member1);
        players[2] = new Player(map, 2, 20, 19, member2);
        players[3] = new Player(map, 3, 30, 29, member3);

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
        currentPlayer = -1;
        currentDecision = null;

        for (Player player : players) {
            for (Figure figure : player.getFigures())
                map.moveFigureToBase(figure);
            if (player.isActiveMember() && currentPlayer == -1)
                currentPlayer = player.getId();
        }

        if (currentPlayer == -1)
            System.out.println("KEIN SPIELER AKTIV! - Wer hat da verkackt?");
    }

    /**
     * Lässt den aktuellen Spieler einmal würfeln und gibt ein Objekt zurück,
     * das die Entscheidung, die der Spieler nun treffen muss, wiederspiegelt.
     *
     * @return Ein neues Entscheidungs-Objekt.
     */
    public Decision rollDice() {
        return currentDecision = players[currentPlayer].rollDice();
    }

    /**
     * Führt den nächsten Spielzug des aktuellen Spielers aus, nachdem dieser eine Entscheidung getroffen hat
     * und wechselt dannach zum nächsten Spieler, der an der Reihe ist.
     *
     * @param decision Die getroffene Entscheidung für diesen Spielzug.
     * @return True, falls der Spieler durch diesen Spielzug gewonnen hat, sonst False.
     */
    public boolean exertDecision() {
        boolean won = players[currentPlayer].processMove(currentDecision);

        int figureCountInBase = map.getFigureCountInBase(players[currentPlayer]);

        int figureCountAtEndOfHome = 0;
        for (int i = 3; i > 0; i--) {
            if (map.getFigureAtHomePosition(players[currentPlayer], i) == null)
                break;
            figureCountAtEndOfHome++;
        }

        boolean repeat = !won && ((currentDecision.getFields() == 6 && sixRepeatCount++ < 2) || (figureCountInBase + figureCountAtEndOfHome == 4 && leaveBaseRepeatCount++ < 2));

        // Bei 6 nochmal würfeln
        if (!repeat) {
            sixRepeatCount = 0;
            leaveBaseRepeatCount = 0;

            int finishedPlayers = 0;
            do {
                if (finishedPlayers >= 4) {
                    Greenfoot.setWorld(new Menu());

                    // Spielende
                    break;
                }

                if (currentPlayer == 3)
                    currentPlayer = 0;
                else
                    currentPlayer++;
            }
            while (!players[currentPlayer].isActiveMember() || (players[currentPlayer].isFinished() && ++finishedPlayers == finishedPlayers));
        }

        return won;
    }
}
