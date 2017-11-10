import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gibt ein Streetfeld auf dem Gameboard aus.
 */
public class Field extends Actor {
    private GameManager gameManager;
    private int fieldId;
    
    //Image: PLayer: 0 = RED, 1 = BLUE, 2 = GREEN, 3 = YELLOW 
    GreenfootImage image ; // free field
    GreenfootImage [] imageWhenOccupied = new GreenfootImage [4]; // when player stands on
    GreenfootImage [] startImage = new GreenfootImage [4]; // start fields

    public Field(GameManager gameManager, int fieldId) {
        this.gameManager = gameManager;
        this.fieldId = fieldId;
        setColors();
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
        int figureOwner = fieldOccupied ? gameManager.getMap().getFigureAtStreetPosition(fieldId).getPlayer().getId() : 0;
        
        //if field not occupied and not a start field of some player then set it to normal field    
        if(startFieldOfPlayer == -1 && !fieldOccupied) {
            this.setImage(image);
        }
        //if field not occupied but is a start field of player it accordingly with different colour image 
        else if (!fieldOccupied && startFieldOfPlayer != -1){
            switch(startFieldOfPlayer){
                case 0: 
                    this.setImage(startImage[0]);
                    break;
                case 1: 
                    this.setImage(startImage[1]);
                    break;
                case 2: 
                    this.setImage(startImage[2]);
                    break;
                case 3: 
                    this.setImage(startImage[3]);
                    break;
                
            }
            
        }
        //if field occupied by a figure set image to show that figure accordingly to a player
        else {
            switch(figureOwner){
                case 0:
                    this.setImage(imageWhenOccupied[0]);
                    break;
                case 1:
                    this.setImage(imageWhenOccupied[1]);
                    break;
                case 2:
                    this.setImage(imageWhenOccupied[2]);
                    break;
                case 3:
                    this.setImage(imageWhenOccupied[3]);
                    break;
                    
            }
            
        }
            
            

        
    }
    
    private void setColors(){
        //setting all possible images to use them later
        image = new GreenfootImage("images/Feld.png");
        imageWhenOccupied [0] = new GreenfootImage("images/red22.png");
        imageWhenOccupied [1] = new GreenfootImage("images/blue22.png");
        imageWhenOccupied [2] = new GreenfootImage("images/green22.png");
        imageWhenOccupied [3] = new GreenfootImage("images/yellow22.png");
        startImage [0] = new GreenfootImage("images/RED-Start.png");
        startImage [1] = new GreenfootImage("images/BLUE-Start.png");
        startImage [2] = new GreenfootImage("images/GREEN-Start.png");
        startImage [3] = new GreenfootImage("images/YELLOW-Start.png");
    }
}
