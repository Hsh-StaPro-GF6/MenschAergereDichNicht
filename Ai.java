import java.util.ArrayList;
import java.util.List;

/**
 * Alle Ki Handlungen.
 */
public class Ai {
    private static final int[] CHECK_CLEAR_FOREIGN_SPAWN = {0, 10, 20, 30, 40};
    private static final int[] CHECK_AVOID_FOREIGN_SPAWN = {0, 10, 20, 30, 40};
    private static final int[] CHECK_SPAWN_CAMPING = {40, 25, 20, 0, 0};
    private static final int[] CHECK_SPAWN_CAMPING_CHANCE = {40, 25, 20, 0, 0};
    private static final int[] CHECK_HOMEBOY = {0, 30, 20, 40, 40};
    private static final int[] CHECK_IMPACT_PREVENTION = {0, 5, 20, 30, 40};
    private static final int[] CHECK_MAX_IMPACT_PREVENTION = {0, 5, 20, 30, 40};
    private static final int[] CHECK_IMPACT_CHANCE = {40, 30, 20, 10, 0};
    private static final int[] CHECK_MAX_IMPACT_CHANCE = {40, 30, 20, 10, 0};
    private static final int[] CHECK_ENSURE_SPACING = {10, 10, 20, 30, 30};
    private static final int[] CHECK_PREVENT_SPACING = {10, 10, 20, 30, 30};
    private static final int[] CHECK_FUTURE_IMPACT_PREVENTION = {0, 5, 20, 30, 40};
    private static final int[] CHECK_FUTURE_IMPACT_CHANCE = {40, 30, 20, 10, 0};
    private static final int[] CHECK_FUTURE_MAX_IMPACT_PREVENTION = {0, 5, 20, 30, 40};
    private static final int[] CHECK_FUTURE_MAX_IMPACT_CHANCE = {40, 30, 20, 10, 0};
    private static final int[] CHECK_LEADER_HUNT = {40, 40, 20, 10, 5};
    private static final int[] CHECK_HOME_POSITION = {40, 30, 20, 10, 0};

    private static final int[] CHECK_FIRST_POSITION = {100, 50, 0, 0, 0};
    private static final int[] CHECK_LAST_POSITION = {0, 0, 0, 50, 100};

    private final GameManager gameManager;
    private final Player player;
    private final int behaviour;
    private final int speedBehaviour;
    private final boolean kevin;

    /**
     * Instanziert eine neue KI.
     *
     * @param gameManager    Der GameManager des aktuellen Spiels (Wird benötigt um Informationen über das aktuelle Spiel abzurufen).
     * @param player         Der Spieler, für den die KI handelt.
     * @param behaviour      Das Taktikverhalten der KI.
     * @param speedBehaviour Das Geschwindigkeitsverhalten der KI.
     */
    public Ai(GameManager gameManager, Player player, int behaviour, int speedBehaviour, boolean kevin) {
        this.gameManager = gameManager;
        this.player = player;
        this.behaviour = behaviour;
        this.speedBehaviour = speedBehaviour;
        this.kevin = kevin;
    }

    public void processDecision(Decision decision) {
        int highestParameter = 0;
        Figure highestFigure = null;

        if (kevin) {
            // Kevin ist verplant, er nimmt immer die Figur mit der niedrigsten Bewertung.
            for (Figure figure : decision.getMovableFigures()) {
                System.out.println(" Figur steht an Position: " + figure.isInStreet());
                int figureParameter = calculateFigureParameter(decision, figure);

                if (highestParameter == 0 || figureParameter <= highestParameter) {
                    highestParameter = figureParameter;
                    highestFigure = figure;
                }
            }
        } else {
            for (Figure figure : decision.getMovableFigures()) {
                System.out.println(" Figur steht an Position: " + figure.isInStreet());
                int figureParameter = calculateFigureParameter(decision, figure);

                if (figureParameter >= highestParameter) {
                    highestParameter = figureParameter;
                    highestFigure = figure;
                }
            }
        }

        System.out.println("highestFigure = " + highestFigure);

        decision.setSelectedFigure(highestFigure);
    }

    private int calculateFigureParameter(Decision decision, Figure figure) {
        int figurParameter = 0;
        figurParameter += checkClearForeignSpawn(figure, decision);
        //checked    System.out.print("| " + figure + ": checkClearForeignSpawn:  " + checkClearForeignSpawn( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkAvoidForeignSpawn(figure, decision);
        //checked    System.out.print("| " + figure + ": checkAvoidForeignSpawn:  " + checkAvoidForeignSpawn( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkSpawnCamping(figure, decision);
        //checked    System.out.print("| " + figure + ": checkSpawnCamping:  " + checkSpawnCamping( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkSpawnCampingChance(figure, decision);
        //checked    System.out.print("| " + figure + ": checkSpawnCampingChance:  " + checkSpawnCampingChance( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkHomeboy(figure, decision);
        //checked    System.out.print("| " + figure + ": checkHomeboy:  " + checkHomeboy( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkHomePosition(figure, decision);
        //checked    System.out.print("| " + figure + ": checkHomePosition:  " + checkHomePosition( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkImpactPrevention(figure, decision);
        System.out.print("| " + figure + ": checkImpactPrevention:  " + checkImpactPrevention(figure, decision) + " ");
        figurParameter += checkImpactChance(figure, decision);
        //checked    System.out.print("| " + figure + ": checkImpactChance:  " + checkImpactChance( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkFutureImpactPrevention(figure, decision);
        //checked    System.out.print("| " + figure + ": checkFutureImpactPrevention:  " + checkFutureImpactPrevention( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkFutureImpactChance(figure, decision);
        //checked    System.out.print("| " + figure + ": checkFutureImpactChance:  " + checkFutureImpactChance( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkEnsureSpacing(figure, decision);
        //checked    System.out.print("| " + figure + ": checkEnsureSpacing:  " + checkEnsureSpacing( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkPreventSpacing(figure, decision);
        //checked    System.out.print("| " + figure + ": checkPreventSpacing:  " + checkPreventSpacing( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkLeaderHunt(figure, decision);
        // checked System.out.print("| " + figure + ": checkLeaderHunt:  " + checkLeaderHunt(figure, decision) + " ");
        figurParameter += checkFirstPosition(figure, decision);
        //checked   System.out.print("| " + figure + ": checkFirstPosition:  " + checkFirstPosition( decision.getMovableFigures()[i],decision) + " ");
        figurParameter += checkLastPosition(figure, decision);
        //checked    System.out.println("| " + figure + ": checkLastPosition:  " + checkLastPosition( decision.getMovableFigures()[i],decision) + " ");

        System.out.println("| " + figure + ": gesamt:  " + figurParameter + " ");

        return figurParameter;
    }

    /**
     * Prüft, ob die Figur auf einem Spawnpunkt, einer fremden besetzen Base, steht
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_CLEAR_FOREIGN_SPAWN[behaviour], falls die Figur auf einenem Sawnpunkt, einer fremden besetzen Base, steht sonst 0.
     */
    private int checkClearForeignSpawn(Figure figure, Decision decision) {
        int ownPosition = figure.isInStreet();
        Player[] players = gameManager.getPlayers();

        // Überhaupt auf der Straße?
        if (ownPosition == -1)
            return 0;

        if (ownPosition == player.getStart())
            return 0;

        switch (ownPosition) {
            // Wenn die Figur auf Gengner Spawn steht und die dazugekörige Base besetrzt ist return CHECK_CLEAR_FOREIGN_SPAWN (bewegen)

            case 0:
                if (gameManager.getMap().getFigureCountInBase(players[0]) > 0)
                    return CHECK_CLEAR_FOREIGN_SPAWN[behaviour];
                break;

            case 10:
                if (gameManager.getMap().getFigureCountInBase(players[1]) > 0)
                    return CHECK_CLEAR_FOREIGN_SPAWN[behaviour];
                break;

            case 20:
                if (gameManager.getMap().getFigureCountInBase(players[2]) > 0)
                    return CHECK_CLEAR_FOREIGN_SPAWN[behaviour];
                break;

            case 30:
                if (gameManager.getMap().getFigureCountInBase(players[3]) > 0)
                    return CHECK_CLEAR_FOREIGN_SPAWN[behaviour];
                break;
        }

        return 0;
    }

    /**
     * Prüft, ob die Figur nach dem Würfeln auf einem Spawnpunkt, einer fremden besetzen Base, gelangt
     *
     * @param figure, die Figur welche geprüft wird
     * @return 0, falls die Figur auf einenem fremden besetzen Sawnpunkt gelangt, sonst CHECK_AVOID_FOREIGN_SPAWN[behaviour].
     */
    private int checkAvoidForeignSpawn(Figure figure, Decision decision) {
        int ownPosition = figure.isInStreet();
        Player[] players = gameManager.getPlayers();

        // Überhaupt auf der Straße?
        if (ownPosition == -1)
            return 0;

        int nextPosition = getStreetPositionFromSteps(ownPosition, decision.getFields());

        if (nextPosition == player.getStart())
            return CHECK_AVOID_FOREIGN_SPAWN[behaviour];

        switch (nextPosition) {
            // Wenn die Figur auf Gengner Spawn steht und die dazugekörige Base besetrzt ist return CHECK_CLEAR_FOREIGN_SPAWN (bewegen)

            case 0:
                if (gameManager.getMap().getFigureCountInBase(players[0]) > 0)
                    return 0;
                break;

            case 10:
                if (gameManager.getMap().getFigureCountInBase(players[1]) > 0)
                    return 0;
                break;

            case 20:
                if (gameManager.getMap().getFigureCountInBase(players[2]) > 0)
                    return 0;
                break;

            case 30:
                if (gameManager.getMap().getFigureCountInBase(players[3]) > 0)
                    return 0;
                break;
        }

        return CHECK_AVOID_FOREIGN_SPAWN[behaviour];
    }

    /**
     * Prüft, ob die Figur einen Spawnpunkt, einer fremden besetzen Base, campt
     *
     * @param figure, die Figur welche geprüft wird
     * @return 0, falls die Figur vor einenem Sawnpunkt, einer fremden besetzen Base, steht, sonst CHECK_SPAWN_CAMPING[behaviour].
     */
    private int checkSpawnCamping(Figure figure, Decision decision) {
        Player[] players = gameManager.getPlayers();

        int ownPosition = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (ownPosition == -1)
            return 0;

        switch (ownPosition) {
            // Wenn die Figur 1 feld vor dem Spawn steht und ist das nicht unser
            // Spawn und die base von player dem  des Spawnfeld gehort nicht leer ist return 0 (warte)
            // ^^ Wer das versteht kriegt nen Keks :P
            case 9:
                if (ownPosition != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[1]) > 0)
                    return 0;
                break;

            case 19:
                if (ownPosition != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[2]) > 0)
                    return 0;
                break;

            case 29:
                if (ownPosition != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[3]) > 0)
                    return 0;
                break;

            case 39:
                if (ownPosition != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[0]) > 0)
                    return 0;
                break;
        }

        // Sonst nicht warten
        return CHECK_SPAWN_CAMPING[behaviour];
    }

    /**
     * Prüft, ob die Figur nach dem Würfeln einen Spawnpunkt, einer fremden besetzen Base, campt
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_SPAWN_CAMPING_CHANCE[behaviour], falls die Figur nach dem Würfeln vor einenem Sawnpunkt, einer fremden besetzen Base, steht, sonst 0.
     */
    private int checkSpawnCampingChance(Figure figure, Decision decision) {
        Player[] players = gameManager.getPlayers();

        int nextPosition = getStreetPositionFromSteps(gameManager.getMap().isFigureInStreet(figure), decision.getFields());

        // Überhaupt auf der Straße?
        if (nextPosition == -1)
            return 0;

        switch (nextPosition) {
            // Wenn die Figur 1 feld vor dem Spawn steht und ist das nicht unser
            // Spawn und die base von player dem  des Spawnfeld gehort nicht leer ist return 0 (warte)
            // ^^ Wer das versteht kriegt nen Keks :P
            case 9:
                if (nextPosition != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[1]) > 0)
                    return CHECK_SPAWN_CAMPING_CHANCE[behaviour];
                break;

            case 19:
                if (nextPosition != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[2]) > 0)
                    return CHECK_SPAWN_CAMPING_CHANCE[behaviour];
                break;

            case 29:
                if (nextPosition != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[3]) > 0)
                    return CHECK_SPAWN_CAMPING_CHANCE[behaviour];
                break;

            case 39:
                if (nextPosition != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[0]) > 0)
                    return CHECK_SPAWN_CAMPING_CHANCE[behaviour];
                break;
        }

        // Sonst nicht warten
        return 0;
    }

    /**
     * Prüft, ob die Figur nach dem Würfeln ins Home gelangen kann
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_HOMEBOY[behaviour], falls die Figur nach dem Würfeln ins Home gelangen kann, sonst 0.
     */
    private int checkHomeboy(Figure figure, Decision decision) {
        int position = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (position == -1)
            return 0;

        // Ist der Home-Eingang in erreichbarer Nähe?
        if (getDistanceBetweenStreetPositions(position, player.getEnd()) < decision.getFields())
            return CHECK_HOMEBOY[behaviour];

        return 0;
    }

    /**
     * Prüft, ob die Figur im Home gelangen steht
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_HOME_POSITION[behaviour], falls die Figur im Home steht, sonst 0.
     */
    private int checkHomePosition(Figure figure, Decision decision) {
        //Figur im Home?
        if (figure.isInHome() != -1)
            return CHECK_HOME_POSITION[behaviour];
        return 0;
    }

    /**
     * Prüft, ob die Figur von einer generische Figur geschlagen werden kann
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_IMPACT_PREVENTION[behaviour]+CHECK_MAX_IMPACT_PREVENTION[behaviour], falls die Figur von den meisten Figuren geschalgen werden kann, sonst 0.
     */
    private int checkImpactPrevention(Figure figure, Decision decision) {
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        Figure myFigure = gameManager.getMap().getFigureAtStreetPosition(pos);
        int enemyCount = 0;
        int myEnemyCount = 0;
        int maxEnemyCount = 0;

        Figure possibleImpact;

        // Führt die Überprüfung für alle bewegbaren Figuren durch
        for (int j = 0; j < decision.getMovableFigures().length; j++) {
            Figure figure2 = decision.getMovableFigures()[j];

            // Überprüft die letzten 5 Felder auf Spieler
            for (int i = 1; i < 6; i++) {
                possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionFromBacksteps(pos, i));
                if (possibleImpact != null && possibleImpact.getPlayer() != figure.getPlayer())
                    enemyCount = enemyCount + 1;
            }
            if (figure2 == myFigure)
                myEnemyCount = enemyCount;

            if (enemyCount > maxEnemyCount)
                maxEnemyCount = enemyCount;
        }

        if (maxEnemyCount != 0 && maxEnemyCount != myEnemyCount)
            return CHECK_IMPACT_PREVENTION[behaviour];

        if (maxEnemyCount != 0 && maxEnemyCount == myEnemyCount)
            return CHECK_IMPACT_PREVENTION[behaviour] + CHECK_MAX_IMPACT_PREVENTION[behaviour];

        // Keine Gefahr!
        return 0;
    }

    /**
     * Prüft, ob die Figur eine generische Figur schlagen kann
     *
     * @param figure, die Figur welche geprüft wird
     * @return 0, falls eine Figur geschalgen werden kann, sonst CHECK_IMPACT_CHANCE[behaviour].
     */
    private int checkImpactChance(Figure figure, Decision decision) {
        int newPosition = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (newPosition == -1)
            return 0;

        Figure myFigure = gameManager.getMap().getFigureAtStreetPosition(newPosition);
        int enemyCount = 0;
        int myEnemyCount = 0;
        int maxEnemyCount = 0;

        // Führt die Überprüfung für alle bewegbaren Figuren durch
        for (int j = 0; j < decision.getMovableFigures().length; j++) {
            Figure figure2 = decision.getMovableFigures()[j];

            // Überprüft die nächsten 5 Felder auf Spieler
            Figure possibleImpact;
            for (int i = 1; i < 6; i++) {
                possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionFromSteps(newPosition, i));
                if (possibleImpact != null)
                    enemyCount = enemyCount + 1;

                if (figure2 == myFigure)
                    myEnemyCount = enemyCount;

                if (enemyCount > maxEnemyCount)
                    maxEnemyCount = enemyCount;
            }
        }
        if (maxEnemyCount != 0 && maxEnemyCount != myEnemyCount)
            return CHECK_IMPACT_CHANCE[behaviour];

        if (maxEnemyCount != 0 && maxEnemyCount == myEnemyCount)
            return 0;

        if (maxEnemyCount == 0)
            return CHECK_IMPACT_CHANCE[behaviour] + CHECK_MAX_IMPACT_CHANCE[behaviour];
        ;
        return 0;
    }

    /**
     * Prüft, ob die Figur nach dem Würfeln von einer generische Figur geschlagen werden kann
     *
     * @param figure, die Figur welche geprüft wird
     * @return 0, falls die Figur nach dem Würfeln geschalgen werden kann, sonst CHECK_FUTURE_IMPACT_PREVENTION[behaviour]+CHECK_FUTURE_MAX_ IMPACT_PREVENTION[behaviour] fals die meisten figuren hintereinem stehn werden.
     */
    private int checkFutureImpactPrevention(Figure figure, Decision decision) {
        int newPosition = getStreetPositionFromSteps(figure.isInStreet(), decision.getFields());

        // Überhaupt auf der Straße?
        if (newPosition == -1)
            return 0;

        Figure myFigure = gameManager.getMap().getFigureAtStreetPosition(newPosition);
        int enemyCount = 0;
        int myEnemyCount = 0;
        int maxEnemyCount = 0;

        // Führt die Überprüfung für alle bewegbaren Figuren durch
        for (int j = 0; j < decision.getMovableFigures().length; j++) {
            Figure figure2 = decision.getMovableFigures()[j];

            // Überprüft die letzten 5 Felder auf Spieler
            Figure possibleImpact;
            for (int i = 1; i < 6; i++) {
                possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionFromBacksteps(newPosition, i));
                if (possibleImpact != null && possibleImpact.getPlayer() != figure.getPlayer())
                    enemyCount = enemyCount + 1;

                if (figure2 == myFigure)
                    myEnemyCount = enemyCount;

                if (enemyCount > maxEnemyCount)
                    maxEnemyCount = enemyCount;
            }
        }
        if (maxEnemyCount != 0 && maxEnemyCount != myEnemyCount)
            return CHECK_FUTURE_IMPACT_PREVENTION[behaviour];

        if (maxEnemyCount != 0 && maxEnemyCount == myEnemyCount)
            return 0;

        if (maxEnemyCount == 0)
            return CHECK_FUTURE_IMPACT_PREVENTION[behaviour] + CHECK_FUTURE_MAX_IMPACT_PREVENTION[behaviour];

        return 0;
    }

    /**
     * Prüft, ob die Figur nach dem Würfeln eine generische Figur schlagen kann
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_FUTURE_IMPACT_CHANCE[behaviour], falls eine Figur nach dem Würfeln geschalgen werden kann, sonst 0.
     */
    private int checkFutureImpactChance(Figure figure, Decision decision) {
        int newPosition = getStreetPositionFromSteps(figure.isInStreet(), decision.getFields());

        // Überhaupt auf der Straße?
        if (newPosition == -1)
            return 0;

        Figure myFigure = gameManager.getMap().getFigureAtStreetPosition(newPosition);
        int enemyCount = 0;
        int myEnemyCount = 0;
        int maxEnemyCount = 0;
        // Führt die Überprüfung für alle bewegbaren Figuren durch
        for (int j = 0; j < decision.getMovableFigures().length; j++) {
            Figure figure2 = decision.getMovableFigures()[j];
            // Überprüft die nächsten 5 Felder auf Spieler
            Figure possibleImpact;
            for (int i = 1; i < 6; i++) {
                possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionFromSteps(newPosition, i));
                if (possibleImpact != null)
                    enemyCount = enemyCount + 1;

                if (figure2 == myFigure)
                    myEnemyCount = enemyCount;

                if (enemyCount > maxEnemyCount)
                    maxEnemyCount = enemyCount;
            }
        }

        if (maxEnemyCount != 0 && maxEnemyCount != myEnemyCount)
            return CHECK_FUTURE_IMPACT_CHANCE[behaviour];

        if (maxEnemyCount != 0 && maxEnemyCount == myEnemyCount)
            return CHECK_FUTURE_IMPACT_CHANCE[behaviour] + CHECK_FUTURE_MAX_IMPACT_CHANCE[behaviour];

        if (maxEnemyCount == 0)
            return 0;
        return 0;
    }

    /**
     * Prüft, ob die eigenen Figuren auf einem Haufen stehen, dierckte Feldnachbarn sind
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_ENSURE_SPACING[behaviour], falls die eigenen Figuren auf einem Haufen stehen, sonst 0.
     */
    private int checkEnsureSpacing(Figure figure, Decision decision) {
        int ownPosition = gameManager.getMap().isFigureInStreet(figure);

        // Überhaupt auf der Straße
        if (ownPosition == -1)
            return 0;

        // Alle Figuren des Spielers
        for (Figure figure2 : player.getFigures()) {
            // Aktuelle Figur ausgewählt?
            if (figure == figure2)
                continue;

            int figure2Position = gameManager.getMap().isFigureInStreet(figure2);

            // Überhaupt auf der Straße
            if (figure2Position == -1)
                continue;

            //Figur 2 steht vor aktueller Figur
            int distance = getDistanceBetweenStreetPositions(ownPosition, figure2Position);

            // Distanz gleich 1?
            if (distance == 1)
                return CHECK_ENSURE_SPACING[behaviour];

            //Figur 2 steht hinter aktueller Figur
            int distance2 = getDistanceBetweenStreetPositions(figure2Position, ownPosition);

            // Distanz gleich 1?
            if (distance2 == 1)
                return CHECK_ENSURE_SPACING[behaviour];
        }

        return 0;
    }

    /**
     * Prüft, die ausgewählte Figur nach dem Würfeln, dierckte freundliche Feldnachbarn hat
     *
     * @param figure, die Figur welche geprüft wird
     * @return 0, falls die ausgewählte Figur nach dem Würfeln, dierckte freundliche Feldnachbarn hat, sonst CHECK_PREVENT_SPACING[behaviour].
     */
    private int checkPreventSpacing(Figure figure, Decision decision) {
        int nextPosition = getStreetPositionFromSteps(gameManager.getMap().isFigureInStreet(figure), decision.getFields());

        // Überhaupt auf der Straße
        if (nextPosition == -1)
            return 0;

        // Alle Figuren des Spielers
        for (Figure figure2 : player.getFigures()) {
            // Aktuelle Figur ausgewählt?
            if (figure == figure2)
                continue;

            int figure2Position = gameManager.getMap().isFigureInStreet(figure2);

            // Überhaupt auf der Straße
            if (figure2Position == -1)
                continue;

            int distance = getDistanceBetweenStreetPositions(nextPosition, figure2Position);
            // Distanz gleich 1?
            if (distance == 1)
                return 0;

            //Figur 2 steht hinter aktueller Figur
            int distance2 = getDistanceBetweenStreetPositions(figure2Position, nextPosition);

            // Distanz gleich 1?
            if (distance2 == 1)
                return 0;

        }

        return CHECK_PREVENT_SPACING[behaviour];
    }

    /**
     * Prüft, ob die ausgewählte Figur 12 Felder hinter einer
     * Figur von führendem Spieler (meiste Figuren in Home) steht
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_LEADER_HUNT[behaviour], fals die ausgewählte Figur 12 Felder hinter einer
     * Figur von führendem Spieler (meiste Figuren in Home) steht, sonst 0.
     */
    // 12 Felder hinter Figur von führendem Spieler (meiste Figuren in Home) -> Figur setzen
    private int checkLeaderHunt(Figure figure, Decision decision) {
        // Position des nächsten (initialen) Ziels:
        int nextTargetPos = gameManager.getMap().isFigureInStreet(figure);
        if (nextTargetPos < 0)
            return 0;

        // Leader herausfinden:
        List<Player> leaders = new ArrayList<Player>();
        int maxScore = 0;
        for (Player curPlayer : gameManager.getPlayers()) {
            // Eigenen Spieler ignorieren:
            if (curPlayer == figure.getPlayer())
                continue;

            // Spieler-Score holen:
            int curPlayerScore = gameManager.getMap().getFigureCountInHome(curPlayer);
            System.out.print(curPlayer.getId() + " : " + curPlayerScore + " | ");
            // Neuen Score setzen und Liste clearen, falls höher:
            if (curPlayerScore > maxScore) {
                maxScore = curPlayerScore;
                leaders.clear();
            }

            // Wenn Score dem Max Score entspricht, Leader hinzufügen:
            if (curPlayerScore == maxScore)
                leaders.add(curPlayer);

        }
        System.out.println();
        System.out.print("größe" + leaders.size());

        // Nächste 12 Felder checken:
        for (int i = 0; i < 12; i++) {
            // Nächste Position:
            nextTargetPos = getStreetPositionFromSteps(nextTargetPos, 1);

            // Aktuelle Position über Ende hinaus:
            int playerEnd = figure.getPlayer().getEnd();
            int myDistance = this.getDistanceBetweenStreetPositions(gameManager.getMap().isFigureInStreet(figure), playerEnd);
            int nextTargetDistance = this.getDistanceBetweenStreetPositions(nextTargetPos, playerEnd);
            if (myDistance < nextTargetDistance)
                break;

            // Nächstes Ziel holen:
            Figure nextTarget = gameManager.getMap().getFigureAtStreetPosition(nextTargetPos);
            if (nextTarget != null) {
                // Nächstes Ziel = Leader => Bedingung erfüllt
                if (leaders.contains(nextTarget.getPlayer()))
                    return CHECK_LEADER_HUNT[behaviour];

            }
        }

        return 0;
    }

    /**
     * Prüft, welche Figur den längsten Weg zum Home hat / am weitesten im Home vorangekommen ist
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_FIRST_POSITION[speedBehaviour], falls die Figur an forderster Stelle stht sonst 0.
     */
    private int checkFirstPosition(Figure figure, Decision decision) {
        Figure minFigure = null;
        int distanceMin = 40;
        int minhomeposition = -1;
        //alle bewegbaren figure durchlaufen
        for (int i = 0; i < decision.getMovableFigures().length; i++) {
            Figure figure2 = decision.getMovableFigures()[i];

            int figure2position = gameManager.getMap().isFigureInStreet(figure2);

            //Figur auf der Starße?
            if (figure2position == -1)
                continue;

            int distance = getDistanceBetweenStreetPositions(figure2position, player.getEnd());

            //geringste Distanz zum Home suchen
            if (distance < distanceMin) {
                distanceMin = distance;
                minFigure = figure2;
            }
        }

        //alle bewegbaren figure durchlaufen
        for (int i = 0; i < decision.getMovableFigures().length; i++) {
            Figure figure2 = decision.getMovableFigures()[i];

            int figure2homeposition = gameManager.getMap().isFigureInHome(figure2);
            //Figur im Home?
            if (figure2homeposition == -1)
                continue;

            // wer ist am weitesten im Home vorangekommen?
            if (figure2homeposition > minhomeposition) {
                minhomeposition = figure2homeposition;
                minFigure = figure2;
            }

        }

        // eigene Figur als min setzen
        if (figure == minFigure)
            return CHECK_FIRST_POSITION[speedBehaviour];

        return 0;
    }

    /**
     * Prüft, am wenigsten im Home vorangekommen ist / welche Figur den längsten Weg zur Base hat
     *
     * @param figure, die Figur welche geprüft wird
     * @return CHECK_LAST_POSITION[speedBehaviour], falls die Figur an hinterster Stelle steht sonst 0.
     */
    // Welche Figur hat den geringsten Abstand zur Base
    private int checkLastPosition(Figure figure, Decision decision) {
        Figure minFigure = null;
        int distanceMin = 40;
        int maxHomePosition = 4;

        //alle bewegbaren figure durchlaufen
        for (int i = 0; i < decision.getMovableFigures().length; i++) {
            Figure figure2 = decision.getMovableFigures()[i];

            int figure2homeposition = gameManager.getMap().isFigureInHome(figure2);
            //Figur im Home?
            if (figure2homeposition == -1)
                continue;

            //wer ist am weitesten im Home vorangekommen?    
            if (figure2homeposition < maxHomePosition) {
                maxHomePosition = figure2homeposition;
                minFigure = figure2;
            }
        }

        //alle bewegbaren figure durchlaufen
        for (int i = 0; i < decision.getMovableFigures().length; i++) {
            Figure figure2 = decision.getMovableFigures()[i];

            int figure2position = gameManager.getMap().isFigureInStreet(figure2);

            //Figur auf der Starße?
            if (figure2position == -1)
                continue;

            int distance = getDistanceBetweenStreetPositions(player.getStart(), figure2position);

            //geringste Distanz zur Base suchen
            if (distance < distanceMin) {
                distanceMin = distance;
                minFigure = figure2;
            }
        }
        // eigene Figur als min setzen
        if (figure == minFigure)
            return CHECK_LAST_POSITION[speedBehaviour];

        return 0;
    }

    // Die Distance zwischen zwei Straßen Positionen
    private int getDistanceBetweenStreetPositions(int position1, int position2) {
        return (position2 - position1) < 0 ? position2 + (40 - position1) : position2 - position1;
    }

    // Die Distanz vorwärts von einer Position
    private int getStreetPositionFromSteps(int currentPos, int steps) {
        return (currentPos + steps) > 39 ? (currentPos + steps) - 40 : currentPos + steps;
    }

    // Die Distanz rückwärts von einer Position
    private int getStreetPositionFromBacksteps(int currentPos, int steps) {
        return (currentPos - steps) < 0 ? (currentPos - steps) + 40 : currentPos - steps;
    }
}
