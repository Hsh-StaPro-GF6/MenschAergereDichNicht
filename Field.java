import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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

    // Bild, wenn das Feld das Startfeld eines Spielers ist
    // Player: 0 = Rot, 1 = Blau, 2 = Grün, 3 = Gelb
    private GreenfootImage[] startImage = new GreenfootImage[4];

    public Field(GameManager gameManager, int fieldId) {
        this.gameManager = gameManager;
        this.fieldId = fieldId;

        loadImages();
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

        // Ist das Feld belegt?
        boolean fieldOccupied = (gameManager.getMap().getFigureAtStreetPosition(fieldId) != null);

        // Feldstatus darstellen
        int figureOwner = fieldOccupied ? gameManager.getMap().getFigureAtStreetPosition(fieldId).getPlayer().getId() : -1;

        // Steht auf dem Feld eine Figur?
        if (fieldOccupied) {
            this.setImage(imageWhenOccupied[figureOwner]);
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
        startImage[0] = new GreenfootImage("images/RED-Start.png");
        startImage[1] = new GreenfootImage("images/BLUE-Start.png");
        startImage[2] = new GreenfootImage("images/GREEN-Start.png");
        startImage[3] = new GreenfootImage("images/YELLOW-Start.png");
    }
}
