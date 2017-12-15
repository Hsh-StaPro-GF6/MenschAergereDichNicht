import greenfoot.*;

public class PlayerWonDisplay extends Actor {
    private int imageIndex = 0;

    public PlayerWonDisplay(Player wonPlayer) {

    }

    public void act() {
        if (++imageIndex >= 100) {
            getWorld().removeObject(this);
            return;
        }

        setImage(new GreenfootImage("images/PlayerWon_frames/" + "0000".substring((imageIndex + "").length()) + imageIndex + ".png"));
    }
}