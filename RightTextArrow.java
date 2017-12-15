import greenfoot.*;

public class RightTextArrow extends Actor {
    private TextField textField;

    public RightTextArrow(TextField textField) {
        this.textField=textField;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            textField.changeSelection(true);
        }
    }
}
