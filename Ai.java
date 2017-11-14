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
        int pos = figure.isInStreet();
        if (pos == -1)
            return 0;
        for 
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

    }
    
    // Die Distance zwischen zwei Straßen Positionen
    private int distanceBetweenStreetPositions(int position1, int position2) {
    	return (position2 - position1) < 0 ? position2 + (40 - position1) : position2 - position1;    	
    }
}
