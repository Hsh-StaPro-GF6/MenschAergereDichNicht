import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LeftArrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeftArrow extends UI
{
    public String name = "Arrow";
    NameBracket bracket;
    LeftArrow(NameBracket bracket){
        this.bracket = bracket;
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)){
            bracket.setName(-1);
        }
    }    
       
   
}
