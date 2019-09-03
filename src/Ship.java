import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Ship extends JPanel{
    int velX = 3;
    int velY = 3;

    LinkedList<Bullet> bullets = new LinkedList<Bullet>();

    int height = 10;
    int length = 10;

    Polygon shape;

    int x;
    int y;

    int[] xCords = new int[4];
    int[] yCords = new int[4];

    Ship(Point startPosition){
        x = startPosition.x;
        y = startPosition.y;
        drawShip();
    }

    public void drawShip(){
        xCords[0] = x;
        xCords[1] = x + length;
        xCords[2] = x + length;
        xCords[3] = x;

        yCords[0] = y;
        yCords[1] = y;
        yCords[2] = y + height;
        yCords[3] = y + height;

        shape = new Polygon(xCords,yCords,4);
    }

    public Polygon getShip(){
        drawShip();
        return shape;
    }

    public void shoot(){
        bullets.add(new Bullet(new Point(x,y)));
    }



    public void up(){ y -= velY; }

    public void down(){ y += velY; }

    public void left(){ x -= velX; }

    public void right(){ x += velX; }

}
