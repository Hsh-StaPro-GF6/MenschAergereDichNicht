import greenfoot.*;

public class LeftTextArrow extends Actor {
    private TextField textField;

    public LeftTextArrow(TextField textField) {
        this.textField=textField;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            textField.changeSelection(false);
        }
    }
}
