import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/**
 * Gibt ein Streetfeld auf dem Gameboard aus.
 */
public class Field extends Actor {
    private GameManager gameManager;
    private int fieldId;

    // Bild, wenn das Feld leer ist
    private GreenfootImage image;

    // Bild, wenn auf dem Feld eine Figur steht
    // Player: 0 = Rot, 1 = Blau, 2 = Grün, 3 = Gelb
    private GreenfootImage[] imageWhenOccupied = new GreenfootImage[4];

    // Bild, wenn auf dem Feld eine Figur steht und diese auswählbar ist
    // Player: 0 = Rot, 1 = Blau, 2 = Grün, 3 = Gelb
    private GreenfootImage[] imageWhenOccupiedSelectable = new GreenfootImage[4];

    // Bild, wenn das Feld das Startfeld eines Spielers ist
    // Player: 0 = Rot, 1 = Blau, 2 = Grün, 3 = Gelb
    private GreenfootImage[] startImage = new GreenfootImage[4];
    
    // Animation für dieses Feld aktiv
    private boolean animationInProgress;
    private boolean test;

    public Field(GameManager gameManager, int fieldId) {
        this.gameManager = gameManager;
        this.fieldId = fieldId;
        this.animationInProgress = false;

        loadImages();
    }

    /**
     * Feld zeichnen.
     */
    public void act() {

        // Feld auf dem eine Figur steht
        Figure figureAtStreetPosition = gameManager.getMap().getFigureAtStreetPosition(fieldId);

        // Prüfen, ob dies das Startfeld eines Spielers ist
        int startFieldOfPlayer = -1;
        switch (fieldId) {
            // Spawnfeld Spieler 0
            case 0: {
                startFieldOfPlayer = 0;
                break;
            }
            // Spawnfeld Spieler 1
            case 10: {
                startFieldOfPlayer = 1;
                break;
            }
            // Spawnfeld Spieler 2
            case 20: {
                startFieldOfPlayer = 2;
                break;
            }
            // Spawnfeld Spieler 3
            case 30: {
                startFieldOfPlayer = 3;
                break;
            }
        }

        // Ist das Feld belegt?
        boolean fieldOccupied = (figureAtStreetPosition != null);

        // Feldstatus darstellen
        int figureOwner = fieldOccupied ? figureAtStreetPosition.getPlayer().getId() : -1;

        // Ist das Feld mit einer bewegbaren Figur belegt && Ist ein Mensch an der Reihe
        boolean selectable = (gameManager.getCurrentDecision() != null && Arrays.asList(gameManager.getCurrentDecision().getMovableFigures()).contains(figureAtStreetPosition) &&
                gameManager.getCurrentDecision().getPlayer().getMember() instanceof HumanMember);

        if (Greenfoot.mouseClicked(this) && selectable) {
            System.out.println("Klick auf klickbare Figur!");
            gameManager.getCurrentDecision().setSelectedFigure(figureAtStreetPosition);
            Player lastPlayer = gameManager.getCurrentPlayer();
            boolean won = gameManager.exertDecision();
            if (won)
                getWorld().addObject(new PlayerWonDisplay(lastPlayer),getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }

        // Steht auf dem Feld eine Figur?
        if (fieldOccupied && !animationInProgress) {
            this.setImage(selectable ? imageWhenOccupiedSelectable[figureOwner] : imageWhenOccupied[figureOwner]);

            if (test) {
                test = false;
                gameManager.getGameBoard().setAnimationInProgress(false);
            }

            return;
        }

        // Start-Feld eines Spielers?
        if (startFieldOfPlayer != -1) {
            this.setImage(startImage[startFieldOfPlayer]);
            return;
        }

        // Leeres Feld zeichnen
        this.setImage(image);
    }

    private void loadImages() {
        // Bilder für Feldstatus laden
        image = new GreenfootImage("images/Feld.png");
        imageWhenOccupied[0] = new GreenfootImage("images/red22.png");
        imageWhenOccupied[1] = new GreenfootImage("images/blue22.png");
        imageWhenOccupied[2] = new GreenfootImage("images/green22.png");
        imageWhenOccupied[3] = new GreenfootImage("images/yellow22.png");
        imageWhenOccupiedSelectable[0] = new GreenfootImage("images/red22_selectable.png");
        imageWhenOccupiedSelectable[1] = new GreenfootImage("images/blue22_selectable.png");
        imageWhenOccupiedSelectable[2] = new GreenfootImage("images/green22_selectable.png");
        imageWhenOccupiedSelectable[3] = new GreenfootImage("images/yellow22_selectable.png");
        startImage[0] = new GreenfootImage("images/RED-Start.png");
        startImage[1] = new GreenfootImage("images/BLUE-Start.png");
        startImage[2] = new GreenfootImage("images/GREEN-Start.png");
        startImage[3] = new GreenfootImage("images/YELLOW-Start.png");
    }
    
    public void setAnimationInProgress(boolean animationInProgress) {
        if (animationInProgress == false)
            test = true;
        this.animationInProgress = animationInProgress;
    }
}
