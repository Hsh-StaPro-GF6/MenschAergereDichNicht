import java.util.ArrayList;
import java.util.List;

/**
 * Alle Ki Handlungen.
 */
public class Ai {
    private static final int[] CHECK_CLEAR_FOREIGN_SPAWN = {0, 10, 20, 30, 40};
    private static final int[] CHECK_AVOID_FOREIGN_SPAWN = {0, 10, 20, 30, 40};
    private static final int[] CHECK_SPAWN_CAMPING = {40, 25, 20, 0, 0};
    private static final int[] CHECK_HOMEBOY = {0, 30, 20, 40, 40};
    private static final int[] CHECK_IMPACT_PREVENTION = {0, 5, 20, 30, 40};
    private static final int[] CHECK_IMPACT_CHANCE = {40, 30, 20, 10, 0};
    private static final int[] CHECK_ENSURE_SPACING = {10, 10, 20, 30, 30};
    private static final int[] CHECK_PREVENT_SPACING = {10, 10, 20, 30, 30};
    private static final int[] CHECK_FUTURE_IMPACT_PREVENTION = {0, 5, 20, 30, 40};
    private static final int[] CHECK_FUTURE_IMPACT_CHANCE = {40, 30, 20, 10, 0};
    private static final int[] CHECK_LEADER_HUNT = {40, 40, 20, 10, 5};
    private static final int[] CHECK_HOME_POSITION = {40, 30, 20, 10, 0};

    private final GameManager gameManager;
    private final Player player;
    private final int behaviour;
    private final int speedBehaviour;

    /**
     * Instanziert eine neue KI.
     *
     * @param gameManager Der GameManager des aktuellen Spiels (Wird benötigt um Informationen über das aktuelle Spiel abzurufen).
     * @param player      Der Spieler, für den die KI handelt.
     * @param behaviour   Der Schwierigkeitsgrad der KI.
     */
    public Ai(GameManager gameManager, Player player, int behaviour, int speedBehaviour) {
        this.gameManager = gameManager;
        this.player = player;
        this.behaviour = behaviour;
        this.speedBehaviour = speedBehaviour;
    }

    public void processDecision(Decision decision) {

    }

    // Die Figur sollte fremde Spawnpunkte möglichst verlassen.
    private int checkClearForeignSpawn(Figure figure, Decision decision) {
        int position = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (position == -1)
            return 0;

        if (position == player.getStart())
            return 0;

        if (position == 0 || position == 10 || position == 20 || position == 30)
            return CHECK_CLEAR_FOREIGN_SPAWN[behaviour];

        return 0;
    }

    // Die Figur sollte fremde Spawnpunkte möglichst nicht betreten.
    private int checkAvoidForeignSpawn(Figure figure, Decision decision) {
        int position = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (position == -1)
            return 0;

        int nextPosition = getStreetPositionFromSteps(position, decision.getFields());

        if (nextPosition == player.getStart())
            return CHECK_AVOID_FOREIGN_SPAWN[behaviour];

        if (nextPosition == 0 || nextPosition == 10 || nextPosition == 20 || nextPosition == 30)
            return 0;

        return CHECK_AVOID_FOREIGN_SPAWN[behaviour];
    }

    // Checkt ob sich die Figur ein Feld vom Spawnfeld entfernt befindet, also dieses "belagert"
    private int checkSpawnCamping(Figure figure) {
        Player[] players = gameManager.getPlayers();

        int position = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (position == -1)
            return 0;

        switch (position) {
            // Wenn die Figur 1 feld vor dem Spawn steht und ist das nicht unser
            // Spawn und die base von player dem  des Spawnfeld gehort nicht leer ist return 0 (warte)
            // ^^ Wer das versteht kriegt nen Keks :P
            case 9:
                if (position != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[1]) > 0)
                    return 0;
                break;

            case 19:
                if (position != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[2]) > 0)
                    return 0;
                break;

            case 29:
                if (position != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[3]) > 0)
                    return 0;
                break;

            case 39:
                if (position != player.getEnd() && gameManager.getMap().getFigureCountInBase(players[0]) > 0)
                    return 0;
                break;
        }

        // Sonst nicht warten
        return CHECK_SPAWN_CAMPING[behaviour];
    }

    // Prüft ob die Figur ins Home kann
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

    private int checkHomePosition(Figure figure) {
        if (figure.isInHome() != -1)
            return CHECK_HOME_POSITION[behaviour];
        return 0;
    }

    //check 5 Felder hinter Figur: wenn gegner -> Flucht
    private int checkImpactPrevention(Figure figure) {
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        // Überprüft die letzten 5 Felder auf Spieler
        Figure possibleImpact;
        for (int i = 1; i < 6; i++) {
            possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionFromBacksteps(pos, i));
            if (possibleImpact != null)
                return CHECK_IMPACT_PREVENTION[behaviour];
        }

        // Keine Gefahr!
        return 0;
    }

    //check 5 Felder vor Figur: wenn gegner a. nicht schlagen können -> warten
    private int checkImpactChance(Figure figure) {
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        // Überprüft die nächsten 5 Felder auf Spieler
        Figure possibleImpact;
        for (int i = 1; i < 6; i++) {
            possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionFromSteps(pos, i));
            if (possibleImpact != null)
                return CHECK_IMPACT_CHANCE[behaviour];
        }

        // Nichts da, was man schlagen könnte
        return 0;
    }

    //check 5 Felder nach Würfeln hinter Figur: wenn gegner -> Flucht
    private int checkFutureImpactPrevention(Figure figure, Decision decision) {
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        pos = +decision.getFields();

        // Überprüft die letzten 5 Felder auf Spieler
        Figure possibleImpact;
        for (int i = 1; i < 6; i++) {
            possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionFromBacksteps(pos, i));
            if (possibleImpact != null)
                return CHECK_FUTURE_IMPACT_PREVENTION[behaviour];
        }

        // Keine Gefahr!
        return 0;
    }

    //check 5 Felder nach Würfeln vor Figur: wenn gegner -> warten
    private int checkFutureImpactChance(Figure figure, Decision decision) {
        int pos = figure.isInStreet();

        // Überhaupt auf der Straße?
        if (pos == -1)
            return 0;

        pos = +decision.getFields();

        // Überprüft die nächsten 5 Felder auf Spieler
        Figure possibleImpact;
        for (int i = 1; i < 6; i++) {
            possibleImpact = gameManager.getMap().getFigureAtStreetPosition(getStreetPositionFromSteps(pos, i));
            if (possibleImpact != null)
                return CHECK_FUTURE_IMPACT_CHANCE[behaviour];
        }

        // Nichts da, was man schlagen könnte
        return 0;
    }

    // Überprüft ob eigene Figuren auf einem Haufen stehen
    private int checkEnsureSpacing(Figure figure) {
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
                return 0;

            int distance = getDistanceBetweenStreetPositions(ownPosition, figure2Position);

            // Distanz gleich 1?
            if (distance == 1)
                return CHECK_ENSURE_SPACING[behaviour];

        }

        return 0;
    }

    // Überprüft ob eigene Figuren nach Zug auf haufen stehen
    private int checkPreventSpacing(Figure figure, Decision decision) {
        int ownPosition = getStreetPositionFromSteps(gameManager.getMap().isFigureInStreet(figure), decision.getFields());

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
                return 0;

            int distance = getDistanceBetweenStreetPositions(ownPosition, figure2Position);

            // Distanz gleich 1?
            if (distance == 1)
                return 0;

        }

        return CHECK_PREVENT_SPACING[behaviour];
    }

    // 12 Felder hinter Figur von führendem Spieler (meiste Figuren in Home) -> Figur setzen
    private int checkLeaderHunt(Figure figure) {
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

            // Neuen Score setzen und Liste clearen, falls höher:
            if (curPlayerScore > maxScore) {
                maxScore = curPlayerScore;
                leaders.clear();
            }

            // Wenn Score dem Max Score entspricht, Leader hinzufügen:
            if (curPlayerScore == maxScore)
                leaders.add(curPlayer);

        }

        // Nächste 12 Felder checken:
        for (int i = 0; i < 12; i++) {
            // Nächste Position:
            nextTargetPos = getStreetPositionFromSteps(gameManager.getMap().isFigureInStreet(figure), 1);

            // Aktuelle Position über Ende hinaus:
            if (nextTargetPos > figure.getPlayer().getEnd())
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

    // Welche Figur hat den geringsten Abstand zum Home
    private int checkFirstPosition(Figure figure) {
        Figure minFigure = null;
        int distanceMin = 40;
        //alle Figuren durchlaufen
        for (Figure figure2 : player.getFigures()) {
            int figure2position = gameManager.getMap().isFigureInStreet(figure2);

            if (figure2position == -1)
                continue;

            int distance = getDistanceBetweenStreetPositions(figure2position, player.getEnd());
            //geringste Distanz zum Home suchen
            if (distance < distanceMin) {
                distanceMin = distance;
                minFigure = figure2;
            }
        }
        // eigene Figur als min setzen
        if (figure == minFigure)
            return new int[]{100, 50, 0, 10, 0}[speedBehaviour];

        return 0;

    }

    // Welche Figur hat den geringsten Abstand zur Base
    private int checkLastPosition(Figure figure) {
        Figure minFigure = null;
        int distanceMin = 40;
        //alle Figuren durchlaufen
        for (Figure figure2 : player.getFigures()) {
            int figure2position = gameManager.getMap().isFigureInStreet(figure2);

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
            return new int[]{0, 0, 0, 50, 100}[speedBehaviour];

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
