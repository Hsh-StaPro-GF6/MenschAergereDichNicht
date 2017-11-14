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
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        // Überprüft die letzten 5 Felder auf Spieler
        Figure possibleImpact;
        for (int i = 1; i < 6; i++) {
            possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionfromBacksteps(pos, i));
            if (possibleImpact != null)
                return new int[]{0, 5, 20, 30, 40}[behaviour];
        }

        // Keine Gefahr!
        return 0;
    }

    //E
    private int checkImpactChance(Figure figure) {
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        // Überprüft die nächsten 5 Felder auf Spieler
        Figure possibleImpact;
        for (int i = 1; i < 6; i++) {
            possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionfromSteps(pos, i));
            if (possibleImpact != null)
                return new int[]{40, 30, 20, 10, 0}[behaviour];
        }

        // Nichts da, was man schlagen könnte
        return 0;
    }

    // Überprüft ob eigene Figuren auf einem Haufen stehen
    private int checkEnsureSpacing(Figure figure) {
    	int ownPosition =gameManager.getMap().isFigureInStreet(figure);    	
    	
    	// Überhaupt auf der Straße
    	if (ownPosition == -1)
    		return 0;
    	
    	// Alle Figuren des Spielers
    	for (Figure figure2: player.getFigures()){
    		// Aktuelle Figur ausgewählt?
    		if (figure==figure2)
    				continue;
    			
    		int figure2Position =gameManager.getMap().isFigureInStreet(figure2);
    		
        	// Überhaupt auf der Straße
        	if (ownPosition == -1)
        		return 0;
    		
    		int distance=getDistanceBetweenStreetPositions(ownPosition,figure2Position);
    		
    		// Distanz gleich 1? 
       		if (distance == 1)
    			return new int[]{10, 10, 20, 30, 30}[behaviour];	
    		
    	}
    		 
    return 0;
    }

    // Überprüft ob eigene Figuren nach Zug auf haufen stehen
    private int checkPreventSpacing(Figure figure, Decision decision ) {
    	int ownPosition = getStreetPositionFromSteps(gameManager.getMap().isFigureInStreet(figure), decision.getFields());    	
    	
    	// Überhaupt auf der Straße
    	if (ownPosition == -1)
    		return 0;
    	
    	// Alle Figuren des Spielers
    	for (Figure figure2: player.getFigures()){
    		// Aktuelle Figur ausgewählt?
    		if (figure==figure2)
    				continue;
    			
    		int figure2Position =gameManager.getMap().isFigureInStreet(figure2);
    		
        	// Überhaupt auf der Straße
        	if (ownPosition == -1)
        		return 0;
    		
    		int distance=getDistanceBetweenStreetPositions(ownPosition,figure2Position);
    		
    		// Distanz gleich 1? 
       		if (distance == 1)
    			return 0;	
    		
    	}
    		 
    	return new int[]{10, 10, 20, 30, 30}[behaviour];
    }

    private int checkFutureImpactPrevention(Figure figure, Decision decision) {
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        pos =+ decision.getFields();

        // Überprüft die letzten 5 Felder auf Spieler
        Figure possibleImpact;
        for (int i = 1; i < 6; i++) {
            possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionfromBacksteps(pos, i));
            if (possibleImpact != null)
                return new int[]{0, 5, 20, 30, 40}[behaviour];
        }

        // Keine Gefahr!
        return 0;
    }

    private int checkFutureImpactChance(Figure figure, Decision decision) {
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        pos =+ decision.getFields();

        // Überprüft die nächsten 5 Felder auf Spieler
        Figure possibleImpact;
        for (int i = 1; i < 6; i++) {
            possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionfromSteps(pos, i));
            if (possibleImpact != null)
                return new int[]{40, 30, 20, 10, 0}[behaviour];
        }

        // Nichts da, was man schlagen könnte
        return 0;
    }

    //F
    private int checkLeaderHunt(Figure figure) {

    }

    // Die Distance zwischen zwei Straßen Positionen
    private int getDistanceBetweenStreetPositions(int position1, int position2) {
    	return (position2 - position1) < 0 ? position2 + (40 - position1) : position2 - position1;    	
    }

    // Die Distanz vorwärts von einer Position
    private int getStreetPositionfromSteps(int currentPos, int steps) {
        return (currentPos + steps) > 39 ? (currentPos + steps) - 40 : currentPos + steps;
    }

    // Die Distanz rückwärts von einer Position
    private int getStreetPositionfromBacksteps(int currentPos, int steps) {
        return (currentPos - steps) < 0 ? (currentPos - steps) + 40 : currentPos - steps;
    }
}
