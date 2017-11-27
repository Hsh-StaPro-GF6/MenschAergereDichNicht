import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RightArrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RightArrow extends UI
{
    public String name = "Arrow";
    NameBracket bracket;
    RightArrow(NameBracket bracket){
        this.bracket = bracket;
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)){
            bracket.setName(1);
        }
    }    
    
   
    
}
