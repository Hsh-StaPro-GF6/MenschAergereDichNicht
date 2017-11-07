import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gibt ein Streetfeld auf dem Gameboard aus.
 */
public class Field extends Actor {
    private GameManager gameManager;
    private int fieldId;

    public Field(GameManager gameManager, int fieldId) {
        this.gameManager = gameManager;
        this.fieldId = fieldId;
    }

    /**
     * Feld zeichnen.
     */
    public void act() {
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

        GreenfootImage image = new GreenfootImage(50, 50);

        // Ist das Feld belegt?
        boolean fieldOccupied = (gameManager.getMap().getFigureAtStreetPosition(fieldId) != null);

        // Feldstatus darstellen
        int figureOwner = fieldOccupied ? gameManager.getMap().getFigureAtStreetPosition(fieldId).getPlayer().getId() : 0;
        if (startFieldOfPlayer != -1)
            image.drawString("S " + startFieldOfPlayer + ": " + (fieldOccupied ? figureOwner : "-"), 5, 25);
        else
            image.drawString("F: " + (fieldOccupied ? figureOwner : "-"), 5, 25);

        this.setImage(image);
    }
}
