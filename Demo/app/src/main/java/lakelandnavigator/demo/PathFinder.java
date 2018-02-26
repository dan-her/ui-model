package navigator.lakelandcc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alex on 12/23/2017.
 */

public class PathFinder {
    public static ArrayList<Node> find(Node start, Node end){
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();
        open.add(start);
        while (open.size() > 0){
            Node n = open.get(0);
            for(int i = 0; i < open.size(); i++){
                Node check = open.get(i);
                if (check.getfCost() <= n.getfCost() && check.hCost < n.hCost){
                    n = check;
                }
            }
            open.remove(n);
            closed.add(n);
            if (n == end){
                path = retrace(start, end);
                return path;
            }
            for (Node node : MainActivity.getAdjacent(n)){
                if (!node.canWalk || closed.contains(node)){
                    continue;
                }
                int cost = n.gCost + n.location.sub(node.location);
                if (cost < node.gCost || !open.contains(node)) {
                    node.hCost = node.location.sub(end.location);
                    node.parent = n;
                    node.gCost = cost;
                    if (!open.contains(node)) {
                        open.add(node);
                    }
                }
            }
        }
        return path;
    }
    public static ArrayList<Node> retrace(Node start, Node end){
        ArrayList<Node> path = new ArrayList();
        Node c = end;
        while (start != c) {
            path.add(c);
            c = c.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
