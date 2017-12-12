import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

public class NameBracket extends Actor {
    private final GameMember[] memberTypes;
    private int currentIndex;

    public NameBracket(GameMember[] memberTypes, int currentIndex) {
        this.memberTypes = memberTypes;
        this.currentIndex = currentIndex;

        updateImage();
    }

    public int getSelectedIndex() {
        return currentIndex;
    }

    public void changeSelection(boolean forward) {
        // Das ist Kunst :P
        currentIndex = forward ? (++currentIndex >= memberTypes.length ? 0 : currentIndex) : (--currentIndex < 0 ? memberTypes.length - 1 : currentIndex);

        updateImage();
    }

    private void updateImage() {
        GreenfootImage imageWithName = new GreenfootImage("images/Kastchen.png");
        imageWithName.setFont(imageWithName.getFont().deriveFont(20f));
        imageWithName.drawString(memberTypes[currentIndex].getName(), 10, 30);
        this.setImage(imageWithName);
    }
}
