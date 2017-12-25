import greenfoot.*;

import java.util.*;

import javax.swing.JOptionPane;

public class Menu extends World {
    private static final int SPACING = 60;

    private static final GameMember[] memberTypes = new GameMember[]{
            new DeadMember(),
            new HumanMember(),
            new AiMember("Kevin (KI)", 4, 4, true),  // nutzt immer die kleinste Punktzahl
            new AiMember("Donald (KI)", 2, 0, false),
            new AiMember("Gitte (KI)", 1, 1, false),
            new AiMember("Horst (KI)", 2, 1, false),
            new AiMember("Sissi (KI)", 0, 4, false),
            new AiMember("Roobert (KI)", 2, 3, false),
            new AiMember("Hodor (KI)", 3, 3, false),
            new AiMember("Ernst-August (KI)", 4, 3, false),
            new AiMember("Ronni (KI)", 3, 4, false),
            new AiMember("Balduin (KI)", 4, 4, false),
            new AiMember("Albus Percival (KI)", 5, 4, false),
            new AiMember("Jussi (KI)", 4, 4, false) // overloard
    };

    private NameBracket[] brackets = new NameBracket[4];
    private StartButton startButton;
    private TextField textField;
    private String name0;
    private String name1;
    private String name2;
    private String name3;

    public Menu() {
        super(12 * SPACING, 12 * SPACING, 1);

        setBackgroundImage();

        //showText("Mensch ärgere dich nicht!", 6 * SPACING, 1 * SPACING);
        
        Name name0= new Name(0,memberTypes[0],"");
        addObject(name0,6 * SPACING, 3 * SPACING);
        
        Name name1= new Name(1,memberTypes[0],"");
        addObject(name1,3 * SPACING, 3 * SPACING);
        Name name2= new Name(2,memberTypes[0],"");
        addObject(name2,3 * SPACING, 4 * SPACING);
        Name name3= new Name(3,memberTypes[0],"");
        addObject(name3,3 * SPACING, 5 * SPACING);
        Name name4= new Name(4,memberTypes[0],"");
        addObject(name4,3 * SPACING, 6 * SPACING);

        

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
        addObject(new RightTextArrow(textField), 11 * SPACING, 8 * SPACING);
        addObject(new LeftTextArrow(textField), 1 * SPACING, 8 * SPACING);

        addObject(startButton = new StartButton(), 6 * SPACING, 10 * SPACING);
    }

    public void act() {
         if (Greenfoot.mouseClicked(startButton)) {
            if (brackets[0].getSelectedIndex() + brackets[1].getSelectedIndex() + brackets[2].getSelectedIndex() + brackets[3].getSelectedIndex() == 0)
                return;
            
            if(memberTypes[brackets[0].getSelectedIndex()].getName().equals("Mensch")) {
                name0 = JOptionPane.showInputDialog("Name von Spieler 1:");
            } else {
                name0 = memberTypes[brackets[0].getSelectedIndex()].getName();
            }
            
            if(memberTypes[brackets[1].getSelectedIndex()].getName().equals("Mensch")) {
                name1 = JOptionPane.showInputDialog("Name von Spieler 2:");
            } else {
                name1 = memberTypes[brackets[1].getSelectedIndex()].getName();
            }
            
            if(memberTypes[brackets[2].getSelectedIndex()].getName().equals("Mensch")) {
                name2 = JOptionPane.showInputDialog("Name von Spieler 3:");
            } else {
                name2 = memberTypes[brackets[2].getSelectedIndex()].getName();
            }
            
            if(memberTypes[brackets[3].getSelectedIndex()].getName().equals("Mensch")) {
                name3 = JOptionPane.showInputDialog("Name von Spieler 4:");
            } else {
                name3 = memberTypes[brackets[0].getSelectedIndex()].getName();
            }
            //String name0 = JOptionPane.showInputDialog("Please input a value");

            Greenfoot.setWorld(new GameBoard(
                    memberTypes[brackets[0].getSelectedIndex()],
                    memberTypes[brackets[1].getSelectedIndex()],
                    memberTypes[brackets[2].getSelectedIndex()],
                    memberTypes[brackets[3].getSelectedIndex()],
                    name0 ,name1, name2, name3));
            
        }
    }

    private void setBackgroundImage() {
        GreenfootImage backgroundImage = new GreenfootImage("images/Board.png");

        backgroundImage.scale(getHeight(), getWidth());
        this.setBackground(backgroundImage);
    }
}
