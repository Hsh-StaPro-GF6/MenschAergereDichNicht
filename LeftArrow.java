import greenfoot.*;

public class LeftArrow extends Actor {
    private NameBracket bracket;

    public LeftArrow(NameBracket bracket) {
        this.bracket = bracket;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            bracket.changeSelection(false);
        }
    }
}
