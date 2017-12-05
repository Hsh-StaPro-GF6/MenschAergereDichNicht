import greenfoot.*;

public class PlayerWonDisplay extends Actor {
    private GifImage gif = new GifImage("images/PlayerWon.gif");
    private int countdown = 250;

    public PlayerWonDisplay(Player wonPlayer) {

    }

    public void act() {
        setImage(gif.getCurrentImage());

        if (countdown-- <= 0)
            getWorld().removeObject(this);
    }
}