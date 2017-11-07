import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gibt ein Statusdisplay aus.
 */
public class StatusDisplay extends Actor {
    private GameManager gameManager;
    private Decision currentDecision;

    public StatusDisplay(GameManager gameManager) {
        this.gameManager = gameManager;
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
        GreenfootImage image = new GreenfootImage(50, 50);

        if (currentDecision == null) {
            image.drawString("Bitte würfeln!", 5, 15);
        } else {
            image.drawString("AS: " + currentDecision.getPlayer().getId(), 5, 15);
            image.drawString("fi: " + currentDecision.getFields(), 5, 35);
        }

        this.setImage(image);
    }
}
