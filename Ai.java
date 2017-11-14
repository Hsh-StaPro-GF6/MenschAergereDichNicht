import greenfoot.*;

/**
 * Alle Ki Handlungen.
 */
public class Ai {
    private final Map map;
    private final Player player;
    private final int behaviour;

    /**
     * Instanziert eine neue KI.
     *
     * @param map       Das Spielfeld.
     * @param player    Der Spieler, für den die KI handelt.
     * @param behaviour Der Schwierigkeitsgrad der KI.
     */
    public Ai(Map map, Player player, int behaviour) {
        this.map = map;
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
        boolean isTrue = false;
        int figurePos = map.isFigureInStreet(figure);
        int end = figure.getPlayer().getEnd();

        for (int i = 0; i < 12; i++, figurePos++) {
            if (figurePos >= 40) {
                figurePos = 0;
            }
        }
        
        return isTrue ? new int[]{40, 40, 20, 10, 5}[behaviour] : 0;
    }
}
