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
//B/N
    private int checkAvoidForeignSpawn(Figure figure) {
        boolean isTrue = false;

        return isTrue ? new int[]{0, 10, 20, 30, 40}[behaviour] : 0;
    }
//B/N
    private int checkSpawnCamping(Figure figure) {

    }
//B/N
    private int checkHomeboy(Figure figure) {

    }
//E
    private int checkImpactPrevention(Figure figure) {

    }
//E
    private int checkImpactChance(Figure figure) {

    }
//T
    private int checkEnsureSpacing(Figure figure) {
    	 int position =map.isFigureInStreet(figure);
    }
//T
    private int checkPreventSpacing(Figure figure) {

    }

    private int checkFutureImpactPrevention(Figure figure) {

    }

    private int checkFutureImpactChance(Figure figure) {

    }
//F
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
    
    // Die Distance zwischen zwei Straßen Positionen
    private int distanceBetweenStreetPositions(int position1, int position2) {
    	return (position2 - position1) < 0 ? position2 + (40 - position1) : position2 - position1;    	
    }
}
