import greenfoot.*;

import java.awt.Toolkit;

/**
 * Das grafische Spielfeld.
 */
public class GameBoard extends World {
    private static final int SPACING = 60;
    private static final int TOOLBAR = 24;

    
    private final GameManager gameManager;
    private Decision decision;
    private Ai ai;
    private boolean DiceRolled = false;
    private boolean animationInProgress;
    private boolean skipSleep = false;
    private boolean hackActive = false;
    private int initState = 0;

    private final StatusDisplay statusDisplay;
    private final ResetButton resetButton;
    private final GameMember member0;
    private final GameMember member1;
    private final GameMember member2;
    private final GameMember member3;
    
    private final Field [] fields = new Field[40];
    
    private static final int xOffSet=(Toolkit.getDefaultToolkit().getScreenSize().width-12*SPACING)/2;
    private static final int yOffSet=((Toolkit.getDefaultToolkit().getScreenSize().height)-12*SPACING-TOOLBAR)/2;

    /**
     * Erstellt ein neues Spielfeld.
     */
    public GameBoard(GameMember member0, GameMember member1, GameMember member2, GameMember member3, String name0, String name1, String name2, String name3) {
        super(Toolkit.getDefaultToolkit().getScreenSize().width,
        Toolkit.getDefaultToolkit().getScreenSize().height-TOOLBAR, 1);

        this.member0 = member0;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;

        this.animationInProgress = false;
       
        gameManager = new GameManager(member0, member1, member2, member3, this);

        // Hintergrund setzen
        setBackgroundImage();

        // Street-Felder anlegen
        addObject(fields[0] = new Field(gameManager, 0), SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[1] = new Field(gameManager, 1), 2 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[2] = new Field(gameManager, 2), 3 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[3] = new Field(gameManager, 3), 4 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[4] = new Field(gameManager, 4), 5 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[5] = new Field(gameManager, 5), 5 * SPACING + xOffSet, 4 * SPACING + yOffSet);
        addObject(fields[6] = new Field(gameManager, 6), 5 * SPACING + xOffSet, 3 * SPACING + yOffSet);
        addObject(fields[7] = new Field(gameManager, 7), 5 * SPACING + xOffSet, 2 * SPACING + yOffSet);
        addObject(fields[8] = new Field(gameManager, 8), 5 * SPACING + xOffSet, SPACING + yOffSet);
        addObject(fields[9] = new Field(gameManager, 9), 6 * SPACING + xOffSet, SPACING + yOffSet);

        addObject(fields[10] = new Field(gameManager, 10), 7 * SPACING + xOffSet, SPACING + yOffSet);
        addObject(fields[11] = new Field(gameManager, 11), 7 * SPACING + xOffSet, 2 * SPACING + yOffSet);
        addObject(fields[12] = new Field(gameManager, 12), 7 * SPACING + xOffSet, 3 * SPACING + yOffSet);
        addObject(fields[13] = new Field(gameManager, 13), 7 * SPACING + xOffSet, 4 * SPACING + yOffSet);
        addObject(fields[14] = new Field(gameManager, 14), 7 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[15] = new Field(gameManager, 15), 8 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[16] = new Field(gameManager, 16), 9 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[17] = new Field(gameManager, 17), 10 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[18] = new Field(gameManager, 18), 11 * SPACING + xOffSet, 5 * SPACING + yOffSet);
        addObject(fields[19] = new Field(gameManager, 19), 11 * SPACING + xOffSet, 6 * SPACING + yOffSet);

        addObject(fields[20] = new Field(gameManager, 20), 11 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[21] = new Field(gameManager, 21), 10 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[22] = new Field(gameManager, 22), 9 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[23] = new Field(gameManager, 23), 8 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[24] = new Field(gameManager, 24), 7 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[25] = new Field(gameManager, 25), 7 * SPACING + xOffSet, 8 * SPACING + yOffSet);
        addObject(fields[26] = new Field(gameManager, 26), 7 * SPACING + xOffSet, 9 * SPACING + yOffSet);
        addObject(fields[27] = new Field(gameManager, 27), 7 * SPACING + xOffSet, 10 * SPACING + yOffSet);
        addObject(fields[28] = new Field(gameManager, 28), 7 * SPACING + xOffSet, 11 * SPACING + yOffSet);
        addObject(fields[29] = new Field(gameManager, 29), 6 * SPACING + xOffSet, 11 * SPACING + yOffSet);

        addObject(fields[30] = new Field(gameManager, 30), 5 * SPACING + xOffSet, 11 * SPACING + yOffSet);
        addObject(fields[31] = new Field(gameManager, 31), 5 * SPACING + xOffSet, 10 * SPACING + yOffSet);
        addObject(fields[32] = new Field(gameManager, 32), 5 * SPACING + xOffSet, 9 * SPACING + yOffSet);
        addObject(fields[33] = new Field(gameManager, 33), 5 * SPACING + xOffSet, 8 * SPACING + yOffSet);
        addObject(fields[34] = new Field(gameManager, 34), 5 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[35] = new Field(gameManager, 35), 4 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[36] = new Field(gameManager, 36), 3 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[37] = new Field(gameManager, 37), 2 * SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[38] = new Field(gameManager, 38), SPACING + xOffSet, 7 * SPACING + yOffSet);
        addObject(fields[39] = new Field(gameManager, 39), SPACING + xOffSet, 6 * SPACING + yOffSet);

        // Base-Felder anlegen
        if(!member0.getName().equals("Keiner")) {
                addObject(new Name(6,member0,name0), SPACING + 30 + xOffSet, 20 + yOffSet);
            }
        addObject(new BaseField(gameManager, gameManager.getPlayers()[0], 0), SPACING + xOffSet, SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[0], 1), SPACING + xOffSet, 2 * SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[0], 2), 2 * SPACING + xOffSet, SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[0], 3), 2 * SPACING + xOffSet, 2 * SPACING + yOffSet);

        if(!member1.getName().equals("Keiner")) {
                addObject(new Name(6,member1,name1), 10*SPACING + 30 + xOffSet, 20 + yOffSet);
            }
        addObject(new BaseField(gameManager, gameManager.getPlayers()[1], 0), 10 * SPACING + xOffSet, SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[1], 1), 10 * SPACING + xOffSet, 2 * SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[1], 2), 11 * SPACING + xOffSet, SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[1], 3), 11 * SPACING + xOffSet, 2 * SPACING + yOffSet);

        if(!member2.getName().equals("Keiner")) {
                addObject(new Name(6,member2,name2), 10* SPACING + 30 + xOffSet, 9* SPACING + 20 + yOffSet);
            }
        addObject(new BaseField(gameManager, gameManager.getPlayers()[2], 0), 10 * SPACING + xOffSet, 10 * SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[2], 1), 10 * SPACING + xOffSet, 11 * SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[2], 2), 11 * SPACING + xOffSet, 10 * SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[2], 3), 11 * SPACING + xOffSet, 11 * SPACING + yOffSet);

       if(!member3.getName().equals("Keiner")) {
                addObject(new Name(6,member3,name3), SPACING + 30 + xOffSet,9* SPACING + 20 + yOffSet);
            }
        addObject(new BaseField(gameManager, gameManager.getPlayers()[3], 0), SPACING + xOffSet, 10 * SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[3], 1), SPACING + xOffSet, 11 * SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[3], 2), 2 * SPACING + xOffSet, 10 * SPACING + yOffSet);
        addObject(new BaseField(gameManager, gameManager.getPlayers()[3], 3), 2 * SPACING + xOffSet, 11 * SPACING + yOffSet);

        // Home-Felder anlegen
        addObject(new HomeField(gameManager, gameManager.getPlayers()[0], 0), 2 * SPACING + xOffSet, 6 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[0], 1), 3 * SPACING + xOffSet, 6 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[0], 2), 4 * SPACING + xOffSet, 6 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[0], 3), 5 * SPACING + xOffSet, 6 * SPACING + yOffSet);

        addObject(new HomeField(gameManager, gameManager.getPlayers()[1], 0), 6 * SPACING + xOffSet, 2 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[1], 1), 6 * SPACING + xOffSet, 3 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[1], 2), 6 * SPACING + xOffSet, 4 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[1], 3), 6 * SPACING + xOffSet, 5 * SPACING + yOffSet);

        addObject(new HomeField(gameManager, gameManager.getPlayers()[2], 0), 10 * SPACING + xOffSet, 6 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[2], 1), 9 * SPACING + xOffSet, 6 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[2], 2), 8 * SPACING + xOffSet, 6 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[2], 3), 7 * SPACING + xOffSet, 6 * SPACING + yOffSet);

        addObject(new HomeField(gameManager, gameManager.getPlayers()[3], 0), 6 * SPACING + xOffSet, 10 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[3], 1), 6 * SPACING + xOffSet, 9 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[3], 2), 6 * SPACING + xOffSet, 8 * SPACING + yOffSet);
        addObject(new HomeField(gameManager, gameManager.getPlayers()[3], 3), 6 * SPACING + xOffSet, 7 * SPACING + yOffSet);

        // Status-Anzeige anlegen
        addObject(statusDisplay = new StatusDisplay(gameManager), 6 * SPACING + xOffSet, 6 * SPACING + yOffSet);
        addObject(resetButton = new ResetButton(), 6 * SPACING + xOffSet, 6 * SPACING + yOffSet);
        gameManager.kiStarter();
    }

    // Spielfeld zeichnen
    public void act() {
        if (Greenfoot.mouseClicked(resetButton)) {
            Greenfoot.setWorld(new Menu());
        }
        //Delay um Welt aufbauen zu lassen
        if (initState < 20) {
            initState++;
            return;
        }

        //Taste um sleep zu skippen
        if (Greenfoot.isKeyDown("0")) {
            skipSleep = true;
        } else {
            skipSleep = false;
        }

        //Hack aktivieren
        if (Greenfoot.isKeyDown("A") && Greenfoot.isKeyDown("9") && Greenfoot.isKeyDown("P")) {
            hackActive = true;
        } else {
            hackActive = false;
        }
        
        //Instant win für den current player
        if (Greenfoot.isKeyDown("control") && Greenfoot.isKeyDown("1") && Greenfoot.isKeyDown("#")) {
            addObject(new PlayerWonDisplay(gameManager.getCurrentPlayer()), getWidth() / 2, getHeight() / 2);
            for(int i = 0; i < 4; i++){
                gameManager.getMap().moveFigureToBase(gameManager.getCurrentPlayer().getFigures()[i]);
                //gameManager.getMap().moveFigureToHomePosition(gameManager.getCurrentPlayer().getFigures()[i],i);
             }  
             
            for(int i = 0; i < 4; i++){
                gameManager.getMap().moveFigureToHomePosition(gameManager.getCurrentPlayer().getFigures()[i],i);
             }   
        }

        //Setze Würfel an aktuelle Spielerbase
           if(gameManager.getCurrentPlayer() == gameManager.getPlayers()[0]) statusDisplay.setLocation(3 * SPACING  + xOffSet, 1 * SPACING + 30 + yOffSet);
            if(gameManager.getCurrentPlayer() == gameManager.getPlayers()[1]) statusDisplay.setLocation(9 * SPACING + xOffSet, 1 * SPACING + 30 + yOffSet);
            if(gameManager.getCurrentPlayer() == gameManager.getPlayers()[2]) statusDisplay.setLocation(9 * SPACING + xOffSet, 10 * SPACING + 30 + yOffSet);
            if(gameManager.getCurrentPlayer() == gameManager.getPlayers()[3]) statusDisplay.setLocation(3 * SPACING + xOffSet, 10 * SPACING + 30 + yOffSet);


        //Entweder (Würfel rollt nicht und wird angeklickt) oder (KI ist dran und keine Animation in gange)
        if (((!DiceRolled && !gameManager.status() && Greenfoot.mouseClicked(statusDisplay)) || gameManager.isNextKi()) && !animationInProgress) {

            //setzt KI trigger zurück
            gameManager.resetNextKi();

            //Sperrt solange der Würfel rollt
            DiceRolled = true;

            //Würfelt und startet dessen Animation
            decision = gameManager.rollDice();
            statusDisplay.updateStatus(decision);

            //Ai Process
            if (decision != null && decision.getPlayer().getMember() instanceof AiMember) {

                //Überspringt Delay wenn 0 gedrückt
                if (!skipSleep) {
                    long curTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() <= curTime + 2000) {}
                }

                //AI verarbeitet Entscheidung
                if (decision.getMovableFigures().length > 0)
                    gameManager.getAi().processDecision(decision);

                //Führt Entscheidung aus
                Player lastPlayer = gameManager.getCurrentPlayer();
                boolean won = gameManager.exertDecision();
                if (won)
                    addObject(new PlayerWonDisplay(lastPlayer), getWidth() / 2, getHeight() / 2);

                //Überspringt Delay wenn 0 gedrückt
                if (!skipSleep) {
                    long curTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() <= curTime + 1000) {}
                }

            } else {
                //Falls kein Zug möglich ist wird zum nächsten Spieler gesprungen
                if (decision != null && decision.getMovableFigures().length == 0) {
                    Player lastPlayer = gameManager.getCurrentPlayer();
                    boolean won = gameManager.exertDecision();
                    if (won)
                        addObject(new PlayerWonDisplay(lastPlayer), getWidth() / 2, getHeight() / 2);
                }
            }

            //Sperre wird aufgehoben
            DiceRolled = false;
        }
    }

    private void setBackgroundImage() {
        GreenfootImage backgroundImage = new GreenfootImage("images/Board.png");

        backgroundImage.scale(Toolkit.getDefaultToolkit().getScreenSize().width,
        Toolkit.getDefaultToolkit().getScreenSize().height);
        this.setBackground(backgroundImage);
    }
    
    public Field[] getFieldsArray(){
        return fields;
    }

    public boolean getSkipSleep() {
        return skipSleep;
    }

    public boolean getHackActive() {
        return hackActive;
    }

    public void setAnimationInProgress(boolean animationInProgress) {
            this.animationInProgress = animationInProgress;
    }
}
