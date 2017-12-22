import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ein Textfeld welches, verschieden Texte anzeigt
 */
public class Name extends Actor
{
    /**
     * Ein Textfeld welches, verschieden Texte anzeigt
     */
    public Name(int field,GameMember gameMember) 
    {
        // welchselt zwischen verschiedenen Text feldern
        switch(field){
        // Menü: überschrift
        case 0:
          GreenfootImage bild = new GreenfootImage(50,50);
          bild.scale(600,300);
          this.setImage(bild);
          bild.setFont(bild.getFont().deriveFont(40f));
          bild.drawString("Mensch ärgere dich nicht!",35,50);
          break;
        // Menü: Spieler 1
        case 1:
          GreenfootImage bild1 = new GreenfootImage(50,50);
          bild1.scale(200,50);
          this.setImage(bild1);
          bild1.setFont(bild1.getFont().deriveFont(30f));
          bild1.drawString("Spieler 1:",30,35);
          break;
        // Menü: Spieler 2
        case 2:
          GreenfootImage bild2 = new GreenfootImage(50,50);
          bild2.scale(200,50);
          this.setImage(bild2);
          bild2.setFont(bild2.getFont().deriveFont(30f));
          bild2.drawString("Spieler 2:",30,35);
          break;
        // Menü: Spieler 3
        case 3:
          GreenfootImage bild3 = new GreenfootImage(50,50);
          bild3.scale(200,50);
          this.setImage(bild3);
          bild3.setFont(bild3.getFont().deriveFont(30f));
          bild3.drawString("Spieler 3:",30,35);
          break;
        // Menü: Spieler 4
        case 4:
          GreenfootImage bild4 = new GreenfootImage(50,50);
          bild4.scale(200,50);
          this.setImage(bild4);
          bild4.setFont(bild4.getFont().deriveFont(30f));
          bild4.drawString("Spieler 4:",30,35);
          break;
        case 5:
          GreenfootImage bild5 = new GreenfootImage(50,50);
          bild5.scale(200,50);
          this.setImage(bild5);
          bild5.setFont(bild5.getFont().deriveFont(30f));
          bild5.drawString(gameMember.getName(),30,35);
          break;
        }
    }    
}
