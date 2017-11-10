import java.util.ArrayList;
import java.util.List;

import greenfoot.*;

/**
 * Stellt einen am spiel teilnehmenden Spieler dar.
 */
public class Player {
    private final Map map;
    private final int playerId;
    private final int start;
    private final int end;
    private final Figure[] figures;

    /**
     * Erstellt eine neue Spieler-Instanz.
     *
     * @param map      Die Instanz der Map.
     * @param playerId Eine eindeutige Spieler-Nummer zwischen von 0-3.
     */
    public Player(Map map, int playerId, int start, int end) {
        this.map = map;
        this.playerId = playerId;
        this.start = start;
        this.end = end;

        this.figures = new Figure[4];
        for (int i = 0; i < figures.length; i++)
            figures[i] = new Figure(this.map, this);
    }

    /**
     * Gibt den Startfeld-Index zurück.
     *
     * @return Startfeld-Index
     */
    public int getStart() {
        return start;
    }

    /**
     * Gibt den Endfeld-Index zurück.
     *
     * @return Endfeld-Index.
     */
    public int getEnd() {
        return end;
    }

    /**
     * Ruft die Spieler-Nummer ab.
     *
     * @return Die Spieler-Nummer.
     */
    public int getId() {
        return playerId;
    }

    /**
     * Generiert eine Spieler-Farbe und gibt diese zurück.
     *
     * @return Die Farbe für diesen Spieler.
     */
    public Color getColor() {
        switch (this.getId()) {
            case 0:
                return Color.BLUE;
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            default:
                return null;
        }
    }

    /**
     * Ruft die Figuren dieses Spielers ab.
     *
     * @return Ein Array aller Figuren dieses Spielers.
     */
    public Figure[] getFigures() {
        return figures;
    }

    /**
     * Ruft ab, ob dieser Spieler alle Figuren im Home hat und somit fertig ist.
     *
     * @return True, wenn wer fertig ist, sonst False.
     */
    public boolean isFinished() {
        Figure[] figures = this.getFigures();
        for (Figure figure : figures) {
            if (this.map.isFigureInHome(figure) == -1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Lässt diesen Spieler würfeln und gibt eine Entscheidung mit allen möglichen Auswahlmöglichkeiten zurück.
     *
     * @return Ein neues Entscheidungs-Objekt.
     */
    public Decision rollDice() {
        System.out.println("Player.rollDice");

        int fields = Greenfoot.getRandomNumber(6) + 1;

        // TODO: Was wenn z.B. Schlagzwang für mehrere Figuren besteht?
        // TODO: Die Zwänge sollten für alle Figuren gleichwertig geprüft werden, unabhängig von der Reihenfolge der Fig. im Array.
        for (Figure figure : figures) {
            // Es besteht Schlag-Zwang, wenn eine Figur eine Figur schlagen kann.
            if (figure.canKickFigure(fields)) {
                Figure[] movable = new Figure[1];
                movable[0] = figure;
                return new Decision(this, fields, true, false, false, movable);
            }

            // Wenn der Spieler mit einer Figur die Base verlassen kann, muss er es auch.
            if (figure.canLeaveBase(fields)) {
                Figure[] movable = new Figure[1];
                movable[0] = figure;
                return new Decision(this, fields, false, true, false, movable);
            }

            // Steht eine Figur im Spawn und kann diesen verlassen? => Dann muss sies auch.
            if (map.isFigureInStreet(figure) == start && figure.canMoveForward(fields)) {
                Figure[] movable = new Figure[1];
                movable[0] = figure;
                return new Decision(this, fields, false, false, true, movable);
            }
        }

        List<Figure> movableFiguresList = new ArrayList<Figure>(figures.length);
        for (Figure figure : figures) {
            if (figure.canMoveForward(fields) || figure.canLeaveBase(fields)) {
                movableFiguresList.add(figure);
            }
        }

        Figure[] movableFigures = new Figure[movableFiguresList.size()];
        for (int i = 0; i < movableFiguresList.size(); i++) {
            movableFigures[i] = movableFiguresList.get(i);
        }

        return new Decision(this, fields, false, false,false, movableFigures);
    }

    /**
     * Führt anhand der getroffenen Entscheidung den nächsten Spielzug aus.
     *
     * @param decision Die getroffene Entscheidung.
     * @return True, falls der Spieler durch diesen Spielzug gewonnen hat, sonst False.
     */
    public boolean processMove(Decision decision) {
        if (decision.getSelectedFigure() == null) {
            System.out.println("FEHLER: Es wurde keine Figur ausgewählt!");
            return false;
        }

        int fields = decision.getFields();
        Figure selectedFigure = decision.getSelectedFigure();
        if (selectedFigure.canMoveForward(fields) || selectedFigure.canLeaveBase(fields)) {
            selectedFigure.processMove(fields);
        }

        return this.isFinished();
    }
}