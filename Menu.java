import greenfoot.*;

import java.util.*;

public class Menu extends World {
    private static final int SPACING = 60;

    private static final GameMember[] memberTypes = new GameMember[]{
            new DeadMember(),
            new HumanMember(),
            new AiMember("Kevin (KI)", 0, 0),  // TODO: kleinste Punktzahl benutzen
            new AiMember("Donald (KI)", 2, 0),
            new AiMember("Gitte (KI)", 1, 1),
            new AiMember("Horst (KI)", 2, 1),
            new AiMember("Sissi (KI)", 0, 4),
            new AiMember("Roobert (KI)", 2, 3),
            new AiMember("Hodor (KI)", 3, 3),
            new AiMember("Ernst-August (KI)", 4, 3),
            new AiMember("Ronni (KI)", 3, 4),
            new AiMember("Balduin (KI)", 4, 4),
            new AiMember("Albus Percival Wulfric Brian (KI)", 5, 4),
            new AiMember("Jussi (KI)", 4, 4) // overloard
    };

    private NameBracket[] brackets = new NameBracket[4];
    private StartButton startButton;

    public Menu() {
        super(12 * SPACING, 12 * SPACING, 1);

        setBackgroundImage();

        showText("Mensch ärgere dich nicht!", 6 * SPACING, 1 * SPACING);

        showText("Spieler 1:", 3 * SPACING, 3 * SPACING);
        showText("Spieler 2:", 3 * SPACING, 4 * SPACING);
        showText("Spieler 3:", 3 * SPACING, 5 * SPACING);
        showText("Spieler 4:", 3 * SPACING, 6 * SPACING);

        // bracket for player 0
        brackets[0] = new NameBracket(memberTypes, 0);
        addObject(brackets[0], 7 * SPACING, 3 * SPACING);
        addObject(new LeftArrow(brackets[0]), 5 * SPACING, 3 * SPACING);
        addObject(new RightArrow(brackets[0]), 9 * SPACING, 3 * SPACING);
        // bracket for player 1
        brackets[1] = new NameBracket(memberTypes, 0);
        addObject(brackets[1], 7 * SPACING, 4 * SPACING);
        addObject(new LeftArrow(brackets[1]), 5 * SPACING, 4 * SPACING);
        addObject(new RightArrow(brackets[1]), 9 * SPACING, 4 * SPACING);
        // bracket for player 2
        brackets[2] = new NameBracket(memberTypes, 0);
        addObject(brackets[2], 7 * SPACING, 5 * SPACING);
        addObject(new LeftArrow(brackets[2]), 5 * SPACING, 5 * SPACING);
        addObject(new RightArrow(brackets[2]), 9 * SPACING, 5 * SPACING);
        // bracket for player 3
        brackets[3] = new NameBracket(memberTypes, 0);
        addObject(brackets[3], 7 * SPACING, 6 * SPACING);
        addObject(new LeftArrow(brackets[3]), 5 * SPACING, 6 * SPACING);
        addObject(new RightArrow(brackets[3]), 9 * SPACING, 6 * SPACING);
        
        TextField textField= new TextField();
                addObject(textField, 6 * SPACING, 8* SPACING);
        /*addObject(new LeftArrow(brackets[3]), 5 * SPACING, 6 * SPACING);
        addObject(new RightArrow(brackets[3]), 9 * SPACING, 6 * SPACING);*/

        addObject(startButton = new StartButton(), 6 * SPACING, 10 * SPACING);
    }

    public void act() {
        if (Greenfoot.mouseClicked(startButton)) {
            if (brackets[0].getSelectedIndex() + brackets[1].getSelectedIndex() + brackets[2].getSelectedIndex() + brackets[3].getSelectedIndex() == 0)
                return;

            Greenfoot.setWorld(new GameBoard(
                    memberTypes[brackets[0].getSelectedIndex()],
                    memberTypes[brackets[1].getSelectedIndex()],
                    memberTypes[brackets[2].getSelectedIndex()],
                    memberTypes[brackets[3].getSelectedIndex()]
            ));
        }
    }

    private void setBackgroundImage() {
        GreenfootImage backgroundImage = new GreenfootImage("images/Board.png");

        backgroundImage.scale(getHeight(), getWidth());
        this.setBackground(backgroundImage);
    }
}
