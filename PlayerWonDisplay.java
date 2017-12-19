import greenfoot.*;

public class PlayerWonDisplay extends Actor {
    private int imageIndex = 0;

    public PlayerWonDisplay(Player wonPlayer) {

    }

    public void act() {
        while (true) {
            if (++imageIndex >= 100) {
                getWorld().removeObject(this);
                break;
            }

            setImage(new GreenfootImage("images/PlayerWon_frames/" + "0000".substring((imageIndex + "").length()) + imageIndex + ".png"));
            getWorld().repaint();
        }
    }
}