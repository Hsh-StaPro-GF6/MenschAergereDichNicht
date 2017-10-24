import com.sun.javaws.exceptions.InvalidArgumentException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Haupt-Klasse, die einfache Methoden zum Verwalten des Spiels bereitstellt.
 */
public class GameManager {

    private final Map map;
    private final Player[] players = new Player[4];
    private int currentPlayer = 0;



    /**
     * Instanziert einen neuen GameManager. Dieser sollte nur einmal existieren.
     */
    public GameManager() {
        map = new Map();
        players[0] = new Player(map, 0, 0, 39);
        players[1] = new Player(map, 1, 10, 9);
        players[2] = new Player(map, 2, 20, 19);
        players[3] = new Player(map, 3, 30, 29);
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
    public boolean exertDecision(Decision decision) {

        boolean x = players[currentPlayer].processMove(decision);

        if(currentPlayer == 3)
            currentPlayer = 0;
        else
            currentPlayer++;
        
        return x;
    }
}
