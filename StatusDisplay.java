import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gibt ein Statusdisplay aus.
 */
public class StatusDisplay extends Actor {

    private GameManager gameManager;
    private Decision currentDecision;
    long lastAdded = System.currentTimeMillis();
    int count = 20;
    int player = 0;
    private static StatusDisplay instance;

    public static StatusDisplay getInstance() {
        return instance;
    }

    public StatusDisplay(GameManager gameManager) {
        this.gameManager = gameManager;

        instance = this;
    }

    /**
     * Status updaten.
     */
    public void updateStatus(Decision currentDecision) {
        this.currentDecision = currentDecision;
    }

    /**
     * Status-Anzeige zeichnen.
     */
    public void act() {
    }

    public void rollDice(int Fields) {

        count = 15;

        System.out.println("RollDice");

        int random = (Greenfoot.getRandomNumber(6) + 1);
        int rotate = 0;

        while (count < 1000) {

            long curTime = System.currentTimeMillis();

            GreenfootImage one = new GreenfootImage("images/" + random + ".png");
            one.scale(60, 60);
            this.setImage(one);
            one.rotate(rotate);
            rotate++;

            getWorld().repaint();

            if (curTime >= lastAdded + count) {

                random = (Greenfoot.getRandomNumber(6) + 1);
                rotate = 0;

                count = count + 200;
                lastAdded = curTime;
            }


        }

        GreenfootImage two = new GreenfootImage("images/" + Fields + ".png");
        two.scale(60, 60);
        this.setImage(two);
        getWorld().repaint();
    }
}
