import java.awt.*;
import java.util.Date;
import java.util.LinkedList;


public class Ship{

    private int height = 10;
    private int length = 10;
    private Point position;
    private Polygon shape;
    private int[] xCords = new int[4];
    private int[] yCords = new int[4];
    private int velX = 3;
    private int velY = 3;
    private long lastShot;
    private int fireRate = 250;

    public LinkedList<Bullet> bullets = new LinkedList<>();

    Ship(Point startPosition){
        position = startPosition;
        drawShip();
    }

    public void shoot(){
        Long now = new Date().getTime();
        if(now > lastShot + fireRate) {
            lastShot = new Date().getTime();
            bullets.add(new Bullet(new Point(position)));

        }
    }

    private void drawShip(){
        xCords[0] = position.x;
        xCords[1] = position.x + length;
        xCords[2] = position.x + length;
        xCords[3] = position.x;
        yCords[0] = position.y;
        yCords[1] = position.y;
        yCords[2] = position.y + height;
        yCords[3] = position.y + height;
        shape = new Polygon(xCords,yCords,4);
    }

    public Polygon getShape(){
        drawShip();
        return shape;
    }

    public void up(){ position.y -= velY; }

    public void down(){ position.y += velY; }

    public void left(){ position.x -= velX; }

    public void right(){ position.x += velX; }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int[] getxCords() {
        return xCords;
    }

    public int[] getyCords() {
        return yCords;
    }

    public Point getTopLeftCords(){
        return new Point(xCords[0], yCords[0]);
    }

    public Point getTopRightCords(){
        return new Point(xCords[1], yCords[1]);
    }

    public Point getBottomRightCords(){
        return new Point(xCords[2], yCords[2]);
    }

    public Point getBottomLeftCords(){
        return new Point(xCords[3], yCords[3]);
    }
}
