package navigator.lakelandcc;

/**
 * Created by Alex on 12/23/2017.
 */

public class Point {
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public int sub(Point other){
        int tempx = Math.abs(x - other.x);
        int tempy = Math.abs(y - other.y);
        int score = 0;
        if (tempx > tempy){
            score = 14 * tempy + 10 * tempx;
        } else {
            score = 14 * tempx + 10 * tempy;
        }
        return score;
    }
}
