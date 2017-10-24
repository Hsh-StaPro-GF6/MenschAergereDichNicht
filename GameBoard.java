import greenfoot.*;

/**
 * The main game board.
 */
public class GameBoard extends World {

    GameManager GM = new GameManager();
    Decision decision;


    /**
     * Constructs a new game board.
     * Should only by called once in the game.
     */
    public GameBoard() {
        super(800, 800, 1);
    }

    public void act() {
        if (Greenfoot.isKeyDown("0")) {

            System.out.println("");
            System.out.println("------------------------");
            System.out.println("Aktueller Spieler: " + GM.getCurrentPlayer());


            decision = GM.rollDice();

            System.out.println(decision.getMovableFigures().length + " Gewürfelt: " + decision.getFields());

            for (int i = 0; i < decision.getMovableFigures().length; i++) {
                System.out.print(" " + decision.getMovableFigures()[i] + " ");
            }
            if (decision.getMovableFigures().length > 0) {
                decision.setSelectedFigure(decision.getMovableFigures()[0]);
                GM.exertDecision(decision);
            }

            System.out.println();

            for (int p = 0; p < 4; p++) {
                System.out.print(" " + GM.getPlayers()[p].getId() + ":");
                for (int i = 0; i < 4; i++) {
                    if (GM.getPlayers()[p].getFigures()[i].isInBase())
                        System.out.print(" B ");
                    else
                        System.out.print(" - ");
                }
                System.out.print("|");
            }

            System.out.println();

            for (int i = 0; i < 40; i++) {
                Figure figure = GM.getMap().getFigureAtStreetPosition(i);
                System.out.print(figure == null ? " - " : " " + figure.getPlayer().getId() + " ");
            }

            System.out.println();

            for (int p = 0; p < 4; p++) {
                System.out.print(" " + GM.getPlayers()[p].getId() + ":");
                for (int i = 0; i < 4; i++) {
                    Figure figure = GM.getMap().getFigureAtHomePosition(GM.getPlayers()[p], i);
                    System.out.print(figure == null ? " - " : " " + figure.getPlayer().getId() + " ");
                }
                System.out.print("|");
            }

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
