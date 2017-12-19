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
    private int nmb;
    private int maxNmb;
    
    public TextField()
    {
       
        try {            
        FileReader fr = new FileReader("TextField.txt");
        BufferedReader br = new BufferedReader(fr);

        String begin=br.readLine();
        
        String begin2= begin.substring(1,3);
        
        int maxNmb= Integer.parseInt(begin2)+1;
        System.out.println(maxNmb);
        this.maxNmb = maxNmb;
                    } catch (IOException e) {
          e.printStackTrace();
       }
        nmb=1;
       updateImage();
       
    }  
    
    private void updateImage() {
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
                bild.scale(550,70);
                this.setImage(bild);
                bild.setFont(bild.getFont().deriveFont(15f));
                this.getImage().drawString(zeile1,10,20);
                this.setImage(bild);
                this.getImage().drawString(zeile2,10,40);
                this.setImage(bild);
                this.getImage().drawString(zeile3,10,60);
            
                break;
            }
        }
                    } catch (IOException e) {
          e.printStackTrace();
       }
    }
    
    public void changeSelection(boolean forward) {
        // Das ist Kunst :P
        nmb = forward ? (++nmb >= maxNmb ? 0 : nmb) : (--nmb < 0 ? maxNmb - 1 : nmb);
        
        updateImage();
    }
}
