import greenfoot.*;

public class RightArrow extends Actor {
    private NameBracket bracket;

    public RightArrow(NameBracket bracket) {
        this.bracket = bracket;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            bracket.changeSelection(true);
        }
    }
}
