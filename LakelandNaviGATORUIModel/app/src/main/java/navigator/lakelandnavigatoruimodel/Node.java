package navigator.lakelandnavigatoruimodel;

import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Alex on 12/23/2017.
 */

public class Node {
    public static ArrayList<Node> rooms = new ArrayList<>();
    public String name;
    public Boolean canWalk;
    public int hCost;
    public int gCost;
    public Point location;
    public Node parent;
    public Node(Boolean canWalk, Point location){
        this.canWalk = canWalk;
        this.location = location;
    }
    //impertinent
    public Node(Boolean canWalk, Point location, int color){
        this.canWalk = canWalk;
        this.location = location;
        this.name = nameFromColor(color);
        rooms.add(this);
    }
    //impertinent
    public static String nameFromColor(int color){
        int r,g,b;
        r = Color.red(color);
        g = Color.green(color);
        b = Color.blue(color);
        return String.format("%c-%s%s", (char)r+64, 128-g, b < 10 ? "0" + b : b);
    }
    //impertinent
    public int getfCost(){
        return hCost+gCost;
    }
    public String getName(){
        if (name != null){
            return name;
        } else {
            return "Not a room";
        }
    }
    //possibly pertinent
    public Boolean equals(Node n){
        return (location == n.location);
    }
    public static Node getNode(String s){
        for(Node n : rooms){
            if (n.name.equals(s)){
                return n;
            }
        }
        return rooms.get(0);
    }
    public String print(){
        return String.format("%s %s %s", location.x, location.y, canWalk);
    }
}
