import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gibt ein Basefeld auf dem Gameboard aus.
 */
public class BaseField extends Actor {
    private GameManager gameManager;
    private Player player;
    private int fieldId;

    public BaseField(GameManager gameManager, Player player, int fieldId) {
        this.gameManager = gameManager;
        this.player = player;
        this.fieldId = fieldId;
    }

    /**
     * Feld zeichnen.
     */
    public void act() {
        GreenfootImage image = new GreenfootImage(50, 50);

        // Muss auf diesem Feld eine Figur stehen, um die korrekte Anzahl an Figuren wiederzugeben?
        boolean fieldOccupied = (gameManager.getMap().getFigureCountInBase(player) > fieldId);

        // Feldstatus darstellen
        image.drawString("B " + player.getId() + ": " + (fieldOccupied ? "O" : "-"), 5, 25);

        this.setImage(image);
    }
}
