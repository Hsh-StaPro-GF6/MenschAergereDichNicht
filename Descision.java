/**
 * Stellt eine Entscheidung, die der Spieler für seinen Zug treffen muss, dar.
 */
public class Descision {
    private final Player player;
    private final int fields;
    private final boolean forcedImpact;
    private final Figure[] movableFigures;

    private Figure selectedFigure;

    public Descision(Player player, int fields, boolean forcedImpact, Figure[] movableFigures) {
        this.player = player;
        this.fields = fields;
        this.forcedImpact = forcedImpact;
        this.movableFigures = movableFigures;
    }

    public Player getPlayer() {
        return player;
    }

    public int getFields() {
        return fields;
    }

    public boolean isForcedImpact() {
        return forcedImpact;
    }

    public Figure[] getMovableFigures() {
        return movableFigures;
    }

    public void setSelectedFigure(Figure figure) {
        selectedFigure = figure;
    }

    public Figure getSelectedFigure(){
        return  selectedFigure;
    }
}
