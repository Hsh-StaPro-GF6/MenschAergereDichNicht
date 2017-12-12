import greenfoot.*;

/**
 * Das grafische Spielfeld.
 */
public class GameBoard extends World {
    private static final int SPACING = 60;

    private final GameManager gameManager;
    private Decision decision;
    private Ai ai;
    private boolean DiceRolled = false;

    private final StatusDisplay statusDisplay;

    private final GameMember member0;
    private final GameMember member1;
    private final GameMember member2;
    private final GameMember member3;

    /**
     * Erstellt ein neues Spielfeld.
     */
    public GameBoard(GameMember member0, GameMember member1, GameMember member2, GameMember member3) {
        super(12 * SPACING, 12 * SPACING, 1);

        this.member0 = member0;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;

        gameManager = new GameManager(member0, member1, member2, member3);

        // Hintergrund setzen
        setBackgroundImage();

        // Street-Felder anlegen
        addObject(new Field(gameManager, 0), SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 1), 2 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 2), 3 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 3), 4 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 4), 5 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 5), 5 * SPACING, 4 * SPACING);
        addObject(new Field(gameManager, 6), 5 * SPACING, 3 * SPACING);
        addObject(new Field(gameManager, 7), 5 * SPACING, 2 * SPACING);
        addObject(new Field(gameManager, 8), 5 * SPACING, SPACING);
        addObject(new Field(gameManager, 9), 6 * SPACING, SPACING);

        addObject(new Field(gameManager, 10), 7 * SPACING, SPACING);
        addObject(new Field(gameManager, 11), 7 * SPACING, 2 * SPACING);
        addObject(new Field(gameManager, 12), 7 * SPACING, 3 * SPACING);
        addObject(new Field(gameManager, 13), 7 * SPACING, 4 * SPACING);
        addObject(new Field(gameManager, 14), 7 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 15), 8 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 16), 9 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 17), 10 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 18), 11 * SPACING, 5 * SPACING);
        addObject(new Field(gameManager, 19), 11 * SPACING, 6 * SPACING);

        addObject(new Field(gameManager, 20), 11 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 21), 10 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 22), 9 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 23), 8 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 24), 7 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 25), 7 * SPACING, 8 * SPACING);
        addObject(new Field(gameManager, 26), 7 * SPACING, 9 * SPACING);
        addObject(new Field(gameManager, 27), 7 * SPACING, 10 * SPACING);
        addObject(new Field(gameManager, 28), 7 * SPACING, 11 * SPACING);
        addObject(new Field(gameManager, 29), 6 * SPACING, 11 * SPACING);

        addObject(new Field(gameManager, 30), 5 * SPACING, 11 * SPACING);
        addObject(new Field(gameManager, 31), 5 * SPACING, 10 * SPACING);
        addObject(new Field(gameManager, 32), 5 * SPACING, 9 * SPACING);
        addObject(new Field(gameManager, 33), 5 * SPACING, 8 * SPACING);
        addObject(new Field(gameManager, 34), 5 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 35), 4 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 36), 3 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 37), 2 * SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 38), SPACING, 7 * SPACING);
        addObject(new Field(gameManager, 39), SPACING, 6 * SPACING);

        // Base-Felder anlegen
        addObject(new BaseField(gameManager, gameManager.getPlayers()[0], 0), SPACING, SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[0], 1), SPACING, 2 * SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[0], 2), 2 * SPACING, SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[0], 3), 2 * SPACING, 2 * SPACING);

        addObject(new BaseField(gameManager, gameManager.getPlayers()[1], 0), 10 * SPACING, SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[1], 1), 10 * SPACING, 2 * SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[1], 2), 11 * SPACING, SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[1], 3), 11 * SPACING, 2 * SPACING);

        addObject(new BaseField(gameManager, gameManager.getPlayers()[2], 0), 10 * SPACING, 10 * SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[2], 1), 10 * SPACING, 11 * SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[2], 2), 11 * SPACING, 10 * SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[2], 3), 11 * SPACING, 11 * SPACING);

        addObject(new BaseField(gameManager, gameManager.getPlayers()[3], 0), SPACING, 10 * SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[3], 1), SPACING, 11 * SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[3], 2), 2 * SPACING, 10 * SPACING);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[3], 3), 2 * SPACING, 11 * SPACING);

        // Home-Felder anlegen
        addObject(new HomeField(gameManager, gameManager.getPlayers()[0], 0), 2 * SPACING, 6 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[0], 1), 3 * SPACING, 6 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[0], 2), 4 * SPACING, 6 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[0], 3), 5 * SPACING, 6 * SPACING);

        addObject(new HomeField(gameManager, gameManager.getPlayers()[1], 0), 6 * SPACING, 2 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[1], 1), 6 * SPACING, 3 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[1], 2), 6 * SPACING, 4 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[1], 3), 6 * SPACING, 5 * SPACING);

        addObject(new HomeField(gameManager, gameManager.getPlayers()[2], 0), 10 * SPACING, 6 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[2], 1), 9 * SPACING, 6 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[2], 2), 8 * SPACING, 6 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[2], 3), 7 * SPACING, 6 * SPACING);

        addObject(new HomeField(gameManager, gameManager.getPlayers()[3], 0), 6 * SPACING, 10 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[3], 1), 6 * SPACING, 9 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[3], 2), 6 * SPACING, 8 * SPACING);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[3], 3), 6 * SPACING, 7 * SPACING);

        // Status-Anzeige anlegen
        addObject(statusDisplay = new StatusDisplay(gameManager), 6 * SPACING, 6 * SPACING);
    }

    // Spielfeld zeichnen
    public void act() {

        if ((!DiceRolled && !gameManager.status() && Greenfoot.mouseClicked(statusDisplay)) || gameManager.isNextKi()) {

            DiceRolled = true;

            // Beginn der nächsten Runde

            System.out.println();
            System.out.println("------------------------");
            System.out.println("Spieler " + gameManager.getCurrentPlayer().getId() + " ist an der Reihe!");

            decision = gameManager.rollDice();
            statusDisplay.updateStatus(decision);

            System.out.println(" Gewürfelt: " + decision.getFields() + " | Bewegbare Figuren: " + decision.getMovableFigures().length);



            /*
            for (int i = 0; i < decision.getMovableFigures().length; i++) {
                System.out.print(" " + decision.getMovableFigures()[i] + " ");
            }
            System.out.println();
            */

            //Ai Process
            if (decision != null && decision.getPlayer().getMember() instanceof AiMember) {

                long curTime = System.currentTimeMillis();

                while (System.currentTimeMillis() <= curTime + 2000) {}

                if (decision.getMovableFigures().length > 0)
                    gameManager.getAi().processDecision(decision);

                System.out.println();

                Player lastPlayer = gameManager.getCurrentPlayer();
                boolean won = gameManager.exertDecision();
                if (won)
                    addObject(new PlayerWonDisplay(lastPlayer), getWidth() / 2, getHeight() / 2);

                curTime = System.currentTimeMillis();

                while (System.currentTimeMillis() <= curTime + 1000) {}

            } else {
                if (decision != null && decision.getMovableFigures().length == 0) {
                    Player lastPlayer = gameManager.getCurrentPlayer();
                    boolean won = gameManager.exertDecision();
                    if (won)
                        addObject(new PlayerWonDisplay(lastPlayer), getWidth() / 2, getHeight() / 2);
                }
            }
            DiceRolled = false;
        }
    }

    private void setBackgroundImage() {
        GreenfootImage backgroundImage = new GreenfootImage("images/Board.png");

        backgroundImage.scale(getHeight(), getWidth());
        this.setBackground(backgroundImage);
    }
}
