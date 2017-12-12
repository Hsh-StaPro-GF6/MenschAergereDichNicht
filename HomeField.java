import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Gibt ein Homefeld auf dem Gameboard aus.
 */
public class HomeField extends Actor {
    private GameManager gameManager;
    private Player player;
    private int fieldId;

    // Bild, wenn das Feld leer ist
    private GreenfootImage image;

    // Bild, wenn auf dem Feld eine Figur steht
    // Player: 0 = Rot, 1 = Blau, 2 = Grün, 3 = Gelb
    private GreenfootImage imageWhenOccupied;

    public HomeField(GameManager gameManager, Player player, int fieldId) {
        this.gameManager = gameManager;
        this.player = player;
        this.fieldId = fieldId;

        loadImages();
    }

    /**
     * Feld zeichnen.
     */
    public void act() {
        // Ist das Feld belegt?
        boolean fieldOccupied = (gameManager.getMap().getFigureAtHomePosition(player, fieldId) != null);

        Figure figureAtHomePosition = gameManager.getMap().getFigureAtHomePosition(player, fieldId);

        // Ist das Feld mit einer bewegbaren Figur belegt && Ist ein Mensch an der Reihe
        boolean selectable = (gameManager.getCurrentDecision() != null && Arrays.asList(gameManager.getCurrentDecision().getMovableFigures()).contains(figureAtHomePosition) &&
                gameManager.getCurrentDecision().getPlayer().getMember() instanceof HumanMember);

        if (Greenfoot.mouseClicked(this) && selectable) {
            System.out.println("Klick auf klickbare Figur!");
            gameManager.getCurrentDecision().setSelectedFigure(figureAtHomePosition);
            Player lastPlayer = gameManager.getCurrentPlayer();
            boolean won = gameManager.exertDecision();
            if (won)
                getWorld().addObject(new PlayerWonDisplay(lastPlayer),getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }

        // Steht auf dem Feld eine Figur?
        if (fieldOccupied) {
            this.setImage(imageWhenOccupied);
            return;
        }

        // Feld ist leer
        this.setImage(image);
    }

    private void loadImages() {
        // Bilder je nach Spieler setzen
        switch (player.getId()) {
            case 0:
                image = new GreenfootImage("images/RED-Home.png");
                imageWhenOccupied = new GreenfootImage("images/red22.png");
                break;
            case 1:
                image = new GreenfootImage("images/BLUE-Home.png");
                imageWhenOccupied = new GreenfootImage("images/blue22.png");
                break;
            case 2:
                image = new GreenfootImage("images/GREEN-Home.png");
                imageWhenOccupied = new GreenfootImage("images/green22.png");
                break;
            case 3:
                image = new GreenfootImage("images/YELLOW-Home.png");
                imageWhenOccupied = new GreenfootImage("images/yellow22.png");
                break;
        }
    }
}


