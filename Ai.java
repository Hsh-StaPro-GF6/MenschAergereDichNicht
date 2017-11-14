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

        //Failsave Check
        if (pos == -1)
            return 0;

        Figure possibleImpact;

        //Überprüft die letzten 5 Felder auf Spieler
        for (int i = 1; i < 6; i++) {
            possibleImpact = map.getFigureAtStreetPosition(getStreetPositionfromBacksteps(pos, i));
            if (possibleImpact != null)
                return new int[]{0, 5, 20, 30, 40}[behaviour];
        }
        return 0;
    }
//E
    private int checkImpactChance(Figure figure) {
        int pos = figure.isInStreet();

        //Failsave Check
        if (pos == -1)
            return 0;

        Figure possibleImpact;

        //Überprüft die nächsten 5 Felder auf Spieler
        for (int i = 1; i < 6; i++) {
            possibleImpact = map.getFigureAtStreetPosition(getStreetPositionfromSteps(pos, i));
            if (possibleImpact != null)
                return new int[]{40, 30, 20, 10, 0}[behaviour];
        }
        return 0;
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

    // Die Distanz rückwärts von einer position
    private int getStreetPositionfromBacksteps(int currentPos, int steps) {
        return (currentPos - steps) < 0 ? (currentPos - steps) + 40 : currentPos - steps;
    }

    // Die Distanz rückwärts von einer position
    private int getStreetPositionfromSteps(int currentPos, int steps) {
        return (currentPos + steps) > 39 ? (currentPos + steps) - 40 : currentPos + steps;
    }
}
