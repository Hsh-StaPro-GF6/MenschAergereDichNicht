import greenfoot.*;

/**
 * Alle Ki Handlungen.
 */
public class Ai {
    private final GameManager gameManager;
    private final Player player;
    private final int behaviour;

    /**
     * Instanziert eine neue KI.
     *
     * @param gameManager Der GameManager des aktuellen Spiels (Wird benötigt um Informationen über das aktuelle Spiel abzurufen).
     * @param player      Der Spieler, für den die KI handelt.
     * @param behaviour   Der Schwierigkeitsgrad der KI.
     */
    public Ai(GameManager gameManager, Player player, int behaviour) {
        this.gameManager = gameManager;
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
<<<<<<< HEAD
    	boolean isTrue = false; 
    	int position1 =map.isFigureInStreet(figure);
    	
    	for (Figure figure2: player.getFigures()){
    		if (figure==figure2){
    				continue;
    			}
    		int position2 =map.isFigureInStreet(figure2);
    		
    		int Distance=getDistanceBetweenStreetPositions(position1,position2);
    		if (Distance == 1){
    			isTrue = true;
    		
    		}
    			
    	}
    		 
    return isTrue ? new int[]{0, 10, 20, 30, 40}[behaviour] : 0;
=======
        int position = map.isFigureInStreet(figure);
>>>>>>> d9ae3179d6bb929d327f077072cca921338f2572
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
<<<<<<< HEAD
    private int getDistanceBetweenStreetPositions(int position1, int position2) {
    	return (position2 - position1) < 0 ? position2 + (40 - position1) : position2 - position1;    	
=======
    private int distanceBetweenStreetPositions(int position1, int position2) {
        return (position2 - position1) < 0 ? position2 + (40 - position1) : position2 - position1;
>>>>>>> d9ae3179d6bb929d327f077072cca921338f2572
    }
}
