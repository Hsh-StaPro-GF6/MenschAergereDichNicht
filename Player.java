import java.util.ArrayList;
import java.util.List;

import greenfoot.Color;
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
            if (!this.map.isFigureInHome(figure)) {
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
        int fields = Greenfoot.getRandomNumber(6);

        for (Figure figure : figures) {
            if (figure.canKickFigure(fields)) {
                Figure[] moveable = new Figure[1];
                moveable[0] = figure;
                return new Decision(this, fields, true, moveable);
            }
        }

        List<Figure> moveableFiguresList = new ArrayList<Figure>(figures.length);
        for (Figure figure : figures) {
            if (figure.canMoveForward(fields) || figure.canLeaveBase(fields)) {
                moveableFiguresList.add(figure);
            }
        }

        Figure[] moveableFigures = new Figure[moveableFiguresList.size()];
        for (int i = 0; i < moveableFiguresList.size(); i++) {
            moveableFigures[i] = moveableFiguresList.get(i);
        }

        return new Decision(this, fields, false, moveableFigures);
    }

    /**
     * Führt anhand der getroffenen Entscheidung den nächsten Spielzug aus.
     *
     * @param decision Die getroffene Entscheidung.
     * @return True, falls der Spieler durch diesen Spielzug gewonnen hat, sonst False.
     */
    public boolean processMove(Decision decision) {
        assert (decision.getSelectedFigure() != null);

        int fields = decision.getFields();
        Figure selectedFigure = decision.getSelectedFigure();
        if (selectedFigure.canMoveForward(fields)) {
            selectedFigure.processMove(fields);
        }

        return this.isFinished();
    }
}
