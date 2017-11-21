import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/**
 * Write a description of class menu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class menu extends World {

    private static final int SPACING = 60;

    private static final HashMap<String, Integer> player0 = new HashMap<>();
    private static final HashMap<String, Integer> player1 = new HashMap<>();
    private static final HashMap<String, Integer> player2 = new HashMap<>();
    private static final HashMap<String, Integer> player3 = new HashMap<>();


    private static final HashMap<Integer, HashMap<String, Integer>> player = new HashMap<>();


    /**
     * Constructor for objects of class menu.
     */
    public menu() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(12 * SPACING, 12 * SPACING, 1);

        player0.put("Kevin (KI)", 0);
        player0.put("Berta (KI)", 1);
        player0.put("Peter (KI)", 2);
        player0.put("Rooooobert (KI)", 3);
        player0.put("Balduin (KI)", 4);
        player0.put("Mensch", -1);
        player0.put("Geschlossen", -2);
        player.put(1, player0);

        player1.put("Donald (KI)", 0);
        player1.put("Peter (KI)", 1);
        player1.put("Sissi (KI)", 2);
        player1.put("Hodor (KI)", 3);
        player1.put("Albus Percival Wulfric Brian (KI)", 4);
        player1.put("Mensch", -1);
        player1.put("Geschlossen", -2);
        player.put(1, player1);

        player2.put("Gitte (KI)", 0);
        player2.put("Olaf (KI)", 1);
        player2.put("Franz (KI)", 2);
        player2.put("Albert (KI)", 3);
        player2.put("Ronni (KI)", 4);
        player2.put("Mensch", -1);
        player2.put("Geschlossen", -2);
        player.put(1, player2);

        player3.put("Horst (KI)", 0);
        player3.put("Gregly (KI)", 1);
        player3.put("Kunibert (KI)", 2);
        player3.put("Ernst-August (KI)", 3);
        player3.put("Jussi (KI)", 4);
        player3.put("Mensch", -1);
        player3.put("Geschlossen", -2);
        player.put(1, player3);

        setBackgroundImage();

        GreenfootImage backgroundImage = new GreenfootImage("images/Board.png");
        backgroundImage.drawImage(backgroundImage, SPACING, 5 * SPACING);

        showText("Mensch ärgere dich nicht!", 6 * SPACING, 1 * SPACING);

        showText("Spieler 1:", 3 * SPACING, 3 * SPACING);
        showText("Spieler 2:", 3 * SPACING, 4 * SPACING);
        showText("Spieler 3:", 3 * SPACING, 5 * SPACING);
        showText("Spieler 4:", 3 * SPACING, 6 * SPACING);

        showText("TEXT HERE", 6 * SPACING, 3 * SPACING);
    }

    private void setBackgroundImage() {
        GreenfootImage backgroundImage = new GreenfootImage("images/Board.png");

        backgroundImage.scale(getHeight(), getWidth());
        this.setBackground(backgroundImage);
    }
}
