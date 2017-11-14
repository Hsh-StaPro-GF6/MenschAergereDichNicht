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
        //Bedingung:
        boolean isTrue = false;

        //Position des nächsten (initialen) Ziels:
        int nextTargetPos = gameManager.getMap().isFigureInStreet(figure) + 1;

        //Maximalen Score herausfinden:
        int maxScore = 0;
        for (Player curPlayer : gameManager.getPlayers()) {
            int curPlayerScore = gameManager.getMap().getFigureCountInBase(curPlayer);
            if (curPlayerScore > maxScore) {
                maxScore = curPlayerScore;
            }
        }

        //Leader herausfinden:
        boolean leaders[] = {false, false, false, false};
        for (Player curPlayer : gameManager.getPlayers()) {
            int curPlayerScore = gameManager.getMap().getFigureCountInBase(curPlayer);
            if (curPlayerScore == maxScore) {
                leaders[curPlayer.getId()] = true;
            }
        }

        //Selbst als Nicht-Leader setzen:
        leaders[figure.getPlayer().getId()] = false;

        //Nächste 12 Felder checken:
        for (int i = 0; i < 12; i++, nextTargetPos++) {
            //Position zwischen 0 und 39 halten:
            if (nextTargetPos >= 40) {
                nextTargetPos = 0;
            }

            //Aktuelle Position über Ende hinaus:
            if (nextTargetPos > figure.getPlayer().getEnd()) {
                break;
            }

            //Nächstes Ziel holen:
            Figure nextTarget = gameManager.getMap().getFigureAtStreetPosition(nextTargetPos);
            if (nextTarget != null) {
                //Nächstes Ziel = Leader => Bedingung erfüllt
                if (leaders[nextTarget.getPlayer().getId()]) {
                    isTrue = true;
                    break;
                }
            }
        }
        
        return isTrue ? new int[]{40, 40, 20, 10, 5}[behaviour] : 0;
    }

    // Die Distance zwischen zwei Straßen Positionen
    private int distanceBetweenStreetPositions(int position1, int position2) {
        return (position2 - position1) < 0 ? position2 + (40 - position1) : position2 - position1;
    }
}
