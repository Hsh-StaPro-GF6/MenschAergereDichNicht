import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gibt ein Basefeld auf dem Gameboard aus.
 */
public class BaseField extends Actor {
    private GameManager gameManager;
    private Player player;
    private int fieldId;
    
    //Image for players: 0 = RED, 1 = BLUE, 2 = GREEN, 3 = YELLOW 
    GreenfootImage image;
    GreenfootImage imageWhenOccupied;    
       
    

    public BaseField(GameManager gameManager, Player player, int fieldId) {
        this.gameManager = gameManager;
        this.player = player;
        this.fieldId = fieldId;
        setColors();
    }

    /**
     * Feld zeichnen.
     */
    public void act() {
        
        
        
        // Muss auf diesem Feld eine Figur stehen, um die korrekte Anzahl an Figuren wiederzugeben?
        boolean fieldOccupied = (gameManager.getMap().getFigureCountInBase(player) > fieldId);

        // Feldstatus darstellen
        
        if(fieldOccupied){
            this.setImage(imageWhenOccupied);
        }
        else{
            this.setImage(image);
        }
        
        
        
    }
    
    private void setColors(){
        //setting colors for every players bases 
        //1st line should work when a player will have its own choosed color
        //player.getColor().toString().equals("RED")   
        if(player.getId()==0){
            image = new GreenfootImage("images/RED-Home.png");
            imageWhenOccupied = new GreenfootImage("images/red22.png");            
        }
        //else if(player.getColor().toString().equals("BLUE")){
        else if(player.getId()==1){
            image = new GreenfootImage("images/BLUE-Home.png");
            imageWhenOccupied = new GreenfootImage("images/blue22.png");
        }
        //else if(player.getColor().toString().equals("GREEN")){
        else if(player.getId()==2){
            image = new GreenfootImage("images/GREEN-Home.png");
            imageWhenOccupied = new GreenfootImage("images/green22.png");
        }
        //else if(player.getColor().toString().equals("YELLOW")){
        else if(player.getId()==3){
            image = new GreenfootImage("images/YELLOW-Home.png");
            imageWhenOccupied = new GreenfootImage("images/yellow22.png");
        }
    }
    
}
