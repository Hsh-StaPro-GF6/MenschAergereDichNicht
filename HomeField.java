import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gibt ein Homefeld auf dem Gameboard aus.
 */
public class HomeField extends Actor {
    private GameManager gameManager;
    private Player player;
    private int fieldId;

    public HomeField(GameManager gameManager, Player player, int fieldId) {
        this.gameManager = gameManager;
        this.player = player;
        this.fieldId = fieldId;
    }

    /**
     * Feld zeichnen.
     */
    public void act() {
        GreenfootImage image = new GreenfootImage(50, 50);

        // Ist das Feld belegt?
        boolean fieldOccupied = (gameManager.getMap().getFigureAtHomePosition(player, fieldId) != null);

        // Feldstatus darstellen
        image.drawString("H " + player.getId() + ": " + (fieldOccupied ? "O" : "-"), 5, 25);

        this.setImage(image);
    }
}


