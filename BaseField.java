import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gibt ein Basefeld auf dem Gameboard aus.
 */
public class BaseField extends Actor {
    private GameManager gameManager;
    private Player player;
    private int fieldId;

    // Bild, wenn das Feld leer ist
    private GreenfootImage image;

    // Bild, wenn auf dem Feld eine Figur steht
    // Player: 0 = Rot, 1 = Blau, 2 = Grün, 3 = Gelb
    private GreenfootImage imageWhenOccupied;

    public BaseField(GameManager gameManager, Player player, int fieldId) {
        this.gameManager = gameManager;
        this.player = player;
        this.fieldId = fieldId;

        loadImages();
    }

    /**
     * Feld zeichnen.
     */
    public void act() {
        // Muss auf diesem Feld eine Figur stehen, um die korrekte Anzahl an Figuren wiederzugeben?
        boolean fieldOccupied = (gameManager.getMap().getFigureCountInBase(player) > fieldId);

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
