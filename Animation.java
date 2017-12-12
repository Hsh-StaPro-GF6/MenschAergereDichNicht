import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation extends Actor
{
    private Field finalField;
    
    private int currentWaypoint = 0;
    private Field[] way;
    
    
    private GameBoard gameBoard;
    private GreenfootImage image;
    private int flields;
    public Animation(GameBoard gameBoard, Field startField, Field finalField, Field[] way){
        this.gameBoard = gameBoard;
        this.currentWaypoint = 0;
        this.way = way;
        this.finalField = finalField;
        
        //Animation für Feld setzen:
        way[way.length-1].setAnimationInProgress(true);
        image = startField.getImage();       
        this.setImage(image);
    }
    
    
    public void act() 
    {
        // Prüfen ob aktuellen Wegpunkt erreicht:
        if (this.getX() == way[currentWaypoint].getX() && this.getY() == way[currentWaypoint].getY()) {
            currentWaypoint++;
        }
        
        try {
            this.turnTowards(way[currentWaypoint].getX(), way[currentWaypoint].getY());
            this.move(10);
        } catch (ArrayIndexOutOfBoundsException ex) {
            
            this.getWorld().removeObject(this);
            way[way.length-1].setAnimationInProgress(false);
            
        }
    } 
    
   
}