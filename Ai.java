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

    private int checkSpawnCamping(Figure figure) {
    	Player [] players = gameManager.getPlayers();
    	
    	int position = figure.isInStreet();
    	
    	// Überhaupt auf der Straße?
    	if(position == -1)
    		return 0;
    	
    	switch (position){
    		// Wenn die Figur 1 feld vor dem Spawn steht und ist das nicht unser 
			// Spawn und die base von player dem  des Spawnfeld gehort nicht leer ist return 0 (warte)
    		case 9:
    			if(position != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[1])>0)
    				return 0;
    			break;
    		
    		case 19:
    			if(position != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[2])>0)
    				return 0;
    			break;
        		
    		
    		case 29:
    			if(position != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[3])>0)
    				return 0;
    			break;
        		
    	
    		case 39:
    			if(position != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[0])>0)
    				return 0;
    			break;
        		
    		// Sonst nicht warten
    		default: 
    			return new int[]{40, 25, 20, 0, 0}[behaviour];
    	}
    }

    //B/N
    private int checkHomeboy(Figure figure, Decision decision) {
    	int stepsToHome = player.getEnd()-figureAtStreetPosition
    			decision.getFields()
    }

    //E
    private int checkImpactPrevention(Figure figure) {

    }

    //E
    private int checkImpactChance(Figure figure) {

    }

    //T
    private int checkEnsureSpacing(Figure figure) {
        int position = map.isFigureInStreet(figure);
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
