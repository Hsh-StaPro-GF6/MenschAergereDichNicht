import greenfoot.*;

/**
 * Alle Ki Handlungen.
 */
public class Ai {
    private final Player player;
    private final int behaviour;

    /**
     * Instanziert eine neue KI.
     *
     * @param player    Der Spieler, für den die KI handelt.
     * @param behaviour Der Schwierigkeitsgrad der KI.
     */
    public Ai(Player player, int behaviour) {
        this.player = player;
        this.behaviour = behaviour;
    }

    public void processDecision(Decision decision) {

    }

    private int checkAvoidForeignSpawn(Figure figure) {
        boolean isTrue = false;

        return isTrue ? new int[]{0, 10, 20, 30, 40}[behaviour] : 0;
    }

    private int checkSpawnCamping(Figure figure) {

    }

    private int checkHomeboy(Figure figure) {

    }

    private int checkImpactPrevention(Figure figure) {

    }

    private int checkImpactChance(Figure figure) {

    }

    private int checkEnsureSpacing(Figure figure) {

    }

    private int checkPreventSpacing(Figure figure) {

    }

    private int checkFutureImpactPrevention(Figure figure) {

    }

    private int checkFutureImpactChance(Figure figure) {

    }

    private int checkLeaderHunt(Figure figure) {

    }
}
