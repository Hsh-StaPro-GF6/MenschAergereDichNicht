import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Ausgabe der Anleitung, Regeln und der einzelnen Ki Spielern
 */
public class TextField extends Actor
{
    // Der Textblock der gerade bearbeitet wird     
    private int textBlockNumber;
    // Die maximale Anzahl der Textblöcke
    private int maxTextBlockNumber;
    /*
     * Liest das Dokument "TextFiled.txt" ein und führt die Text ausgabe durch
     */
    public TextField()
    {
   try {            
        File f = new File("./TextField.txt");

        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String begin=bufferedReader.readLine();        
        String begin2= begin.substring(2,4);
        
        int maxTextBlockNumber= Integer.parseInt(begin2);
        this.maxTextBlockNumber = maxTextBlockNumber+1;
                    } catch (IOException e) {
          e.printStackTrace();
       }
       
       updateImage();
       
    }  
    
    /*
     * Aktualiesiert das ausgegeben Bild, mit dem gerade ausgewählten Textblock
     */
    private void updateImage() {
 try {            
        File f = new File("./TextField.txt");

        FileReader fileReader = new FileReader(f);
        //FileReader fileReader = new FileReader(new File(this.getClass().getResource("/TextField.txt")));
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        // überprüft ob das Dokument zu ende ist
        while((line = bufferedReader.readLine()) != null) {
            // öffnet den vorher ausgewählten Block
            if (line.startsWith(String.valueOf(textBlockNumber))) {
                String zeile1 = bufferedReader.readLine();
                String zeile2 = bufferedReader.readLine();
                String zeile3 = bufferedReader.readLine();
                
                GreenfootImage bild = new GreenfootImage("images/Kastchen.png");
                bild.scale(550,70);
                this.setImage(bild);
                bild.setFont(bild.getFont().deriveFont(15f));
                this.getImage().drawString(zeile1,10,20);
                this.getImage().drawString(zeile2,10,40);                
                this.getImage().drawString(zeile3,10,60);            
                break;
            }
        }
                    } catch (IOException e) {
          
          e.printStackTrace();
          JOptionPane.showMessageDialog(null,e.getMessage());
          System.out.println(e.getMessage());
       }
    
    }
    
    /*
     * Springt zwischen den Textblöcken hin und her
     */
    public void changeSelection(boolean forward) {
        // Das ist Kunst :P
        textBlockNumber = forward ? (++textBlockNumber >= maxTextBlockNumber ? 0 : textBlockNumber) : (--textBlockNumber < 0 ? maxTextBlockNumber - 1 : textBlockNumber);
        
        updateImage();
    }
}
