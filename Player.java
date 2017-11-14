import java.util.ArrayList;
import java.util.Arrays;
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

        // Eine Zahl würfeln
        int fields = Greenfoot.getRandomNumber(6) + 1;

        // Steht eine Figur im Spawn und kann diesen verlassen? => Dann muss sies auch.
        List<Figure> movableFigures = new ArrayList<Figure>(figures.length);
        for (Figure figure : figures)
            if (map.isFigureInStreet(figure) == start && figure.canMoveForward(fields))
                movableFigures.add(figure);
        if (movableFigures.size() > 0)
            return new Decision(this, fields, false, false, true, movableFigures.toArray(new Figure[movableFigures.size()]));

        // Wenn der Spieler mit einer Figur die Base verlassen kann, muss er es auch.
        List<Figure> leavingFigures = new ArrayList<Figure>(figures.length);
        for (Figure figure : figures)
            if (figure.canLeaveBase(fields))
                leavingFigures.add(figure);
        if (leavingFigures.size() > 0)
            return new Decision(this, fields, false, true, false, leavingFigures.toArray(new Figure[leavingFigures.size()]));

        // Es besteht Schlag-Zwang, wenn eine Figur eine Figur schlagen kann.
        List<Figure> impactingFigures = new ArrayList<Figure>(figures.length);
        for (Figure figure : figures)
            if (figure.canKickFigure(fields))
                impactingFigures.add(figure);
        if (impactingFigures.size() > 0)
            return new Decision(this, fields, true, false, false, impactingFigures.toArray(new Figure[impactingFigures.size()]));

        // Es besteht kein Zwang, die Figur kann frei gewählt werden
        List<Figure> selectableFigures = new ArrayList<Figure>(figures.length);
        for (Figure figure : figures)
            if (figure.canMoveForward(fields) || figure.canLeaveBase(fields))
                selectableFigures.add(figure);

        return new Decision(this, fields, false, false, false, selectableFigures.toArray(new Figure[selectableFigures.size()]));
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
