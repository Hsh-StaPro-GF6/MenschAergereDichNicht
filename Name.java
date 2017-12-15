import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Name here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Name extends Actor
{
    /**
     * Act - do whatever the Name wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Name(int field) 
    {
        switch(field){
        case 0:
          GreenfootImage bild = new GreenfootImage(50,50);
          bild.scale(600,300);
          this.setImage(bild);
          bild.setFont(bild.getFont().deriveFont(40f));
          bild.drawString("Mensch ärgere dich nicht!",35,50);
          break;
        case 1:
          GreenfootImage bild1 = new GreenfootImage(50,50);
          bild1.scale(200,50);
          this.setImage(bild1);
          bild1.setFont(bild1.getFont().deriveFont(30f));
          bild1.drawString("Spieler 1:",30,35);
          break;
        case 2:
          GreenfootImage bild2 = new GreenfootImage(50,50);
          bild2.scale(200,50);
          this.setImage(bild2);
          bild2.setFont(bild2.getFont().deriveFont(30f));
          bild2.drawString("Spieler 2:",30,35);
          break;
        case 3:
          GreenfootImage bild3 = new GreenfootImage(50,50);
          bild3.scale(200,50);
          this.setImage(bild3);
          bild3.setFont(bild3.getFont().deriveFont(30f));
          bild3.drawString("Spieler 3:",30,35);
          break;
        case 4:
          GreenfootImage bild4 = new GreenfootImage(50,50);
          bild4.scale(200,50);
          this.setImage(bild4);
          bild4.setFont(bild4.getFont().deriveFont(30f));
          bild4.drawString("Spieler 4:",30,35);
        break;
        }
    }    
}
