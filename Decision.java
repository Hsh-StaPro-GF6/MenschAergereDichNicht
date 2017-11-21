/**
 * Stellt eine Entscheidung, die der Spieler für seinen Zug treffen muss, dar.
 */
public class Decision {
    private final Player player;
    private final int fields;
    private final boolean forcedImpact;
    private final boolean forcedLeave;
    private final boolean forcedMove;
    private final Figure[] movableFigures;

    private Figure selectedFigure = null;

    /**
     * Erstellt eine neue Instanz, die eine Entscheidung für einen Spielzug wiederspiegelt.
     *
     * @param player         Der Spieler, der diese Entscheidung treffen muss.
     * @param fields         Die gewürfelte Zahl.
     * @param forcedImpact   Gibt an, ob die Entscheidung durch Schlag-Zwang vorbestimmt ist.
     * @param forcedLeave    Gibt an, ob die Entscheidung durch Base-Verlass-Zwang vorbestimmt ist.
     * @param forcedMove     Gibt an, ob die Entscheidung durch Base-Verlass-Zwang vorbestimmt ist.
     * @param movableFigures Die Figuren, die für den nächsten Zug gewählt werden können.
     */
    public Decision(Player player, int fields, boolean forcedImpact, boolean forcedLeave, boolean forcedMove, Figure[] movableFigures) {
        this.player = player;
        this.fields = fields;
        this.forcedImpact = forcedImpact;
        this.forcedLeave = forcedLeave;
        this.forcedMove = forcedMove;
        this.movableFigures = movableFigures;
    }

    /**
     * Gibt den spieler zurück.
     *
     * @return Der Spieler.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gibt die gewürftelte Zahl zurück.
     *
     * @return Die gewürfelte Zahl.
     */
    public int getFields() {
        return fields;
    }

    /**
     * Gibt zurück, ob Schlag-Zwang besteht.
     *
     * @return True, falls Schlag-Zwang besteht, sonst False.
     */
    public boolean isForcedImpact() {
        return forcedImpact;
    }

    /**
     * Gibt zurück, Zwang besteht, die Base zu verlassen.
     *
     * @return True, falls Base-Veralss-Zwang besteht, sonst False.
     */
    public boolean isForcedLeave() {
        return forcedLeave;
    }

    /**
     * Gibt zurück, Zwang besteht, den Spawn zu räumen
     *
     * @return True, falls Spawn-Räum-Zwang besteht, sonst False.
     */
    public boolean isForcedMove() {
        return forcedMove;
    }

    /**
     * Ruft die Figuren ab, zwischen denen für den nächsten Zug gewählt werden kann.
     *
     * @return Die Liste der wählbaren Figuren.
     */
    public Figure[] getMovableFigures() {
        return movableFigures;
    }

    /**
     * Setzt die Figur, die für diese Entscheidung gewählt wird.
     *
     * @param figure Die gewählte Figur.
     */
    public void setSelectedFigure(Figure figure) {
        // Prüfen, ob Figur bewegt werden darf.
        boolean isMovable = false;

        for (Figure mvFig : movableFigures)
            if (mvFig == figure)
                isMovable = true;

        if (!isMovable) {
            System.out.println("FEHLER: Die ausgewählte Figur darf nicht bewegt werden!");
            return;
        }

        selectedFigure = figure;
    }

    /**
     * Ruft die Figur ab, die für diese Entscheidung gewählt wurde.
     *
     * @return Die gewählte Figur.
     */
    public Figure getSelectedFigure() {
        return selectedFigure;
    }
}
