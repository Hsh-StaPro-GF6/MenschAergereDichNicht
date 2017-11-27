import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;
/**
 * Write a description of class NameBracket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NameBracket extends UI
{
    HashMap<String, Integer> player;
    
    GreenfootImage image = new GreenfootImage("images/Kastchen.png");
    GreenfootImage imageWithName = this.getImage();
    //
    int index=-1;
    NameBracket(HashMap<String, Integer>  player){
        this.player = player;
        this.setName(0);
    }
    
    public void act() 
    {
        
    }   
    // value +1 / -1 um die namen vor-/ruckwarts tauschen
    public void setName(int value){
        this.index = index+value;
        if(index == -3) index = 4;
        if(index == 5) index = -2;
        imageWithName = new GreenfootImage("images/Kastchen.png");;
        
        
         //how to get from value in HashMap example - similar to Hashtable example
        HashMap map = new HashMap();
        map.put("one", 1);
        map.put("two", 2);
      
        //find key from value in HashMap Java - one to one mapping
        Integer intValue=index;
        String strKey = null;
        for(HashMap.Entry entry: player.entrySet()){
            if(intValue.equals(entry.getValue())){
                strKey = (String) entry.getKey();
                break; //breaking because its one to one map
            }
        }

        //Read more: http://javarevisited.blogspot.com/2013/02/how-to-get-key-from-value-in-hashtable.html#ixzz4zGMewvRg

                
        imageWithName.drawString(strKey,10,16);
        this.setImage(imageWithName);
    }
    
}
