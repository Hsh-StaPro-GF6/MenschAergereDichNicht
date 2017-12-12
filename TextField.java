import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * Write a description of class TextField here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextField extends Actor
{
    public TextField()
    {
       updateImage(1);
    }  
    
    private void updateImage(int nmb) {
       try {            
        FileReader fr = new FileReader("TextField.txt");
        BufferedReader br = new BufferedReader(fr);

        String line;
        while((line = br.readLine()) != null) {
            if (line.startsWith(String.valueOf(nmb))) {
                String zeile1 = br.readLine();
                String zeile2 = br.readLine();
                String zeile3 = br.readLine();
              
                GreenfootImage bild = new GreenfootImage("images/Kastchen.png");
                bild.scale(600,70);
                this.setImage(bild);
                bild.setFont(bild.getFont().deriveFont(20f)); // Where 12 is the desired size.
                this.getImage().drawString(zeile1,10,20);
                this.setImage(bild);
                this.getImage().drawString(zeile2,10,40);
                this.setImage(bild);
                this.getImage().drawString(zeile3,10,60);
                break;
            }
        }

        
        /*String zeile1 = br.readLine();
        String zeile2 = br.readLine();
        String zeile3 = br.readLine();
        
        GreenfootImage imageWithName = new GreenfootImage("images/Kastchen.png");
        imageWithName.setFont(imageWithName.getFont().deriveFont(20f));
        imageWithName.drawString(memberTypes[currentIndex].getName(), 10, 30);
        this.setImage(imageWithName);
              
        GreenfootImage bild = new GreenfootImage(600,70);
                    this.setImage(bild);
                    bild.setFont(bild.getFont().deriveFont(20f)); // Where 12 is the desired size.
                    this.getImage().drawString(zeile1,0,20);
                     this.setImage(bild);
                    this.getImage().drawString(zeile2,0,40);
                     this.setImage(bild);
                    this.getImage().drawString(zeile3,0,60);*/
                    } catch (IOException e) {
          e.printStackTrace();
       }
    }
}
