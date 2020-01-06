import java.awt.*;
import java.util.Random;

public class Coin{
    private int height = 10;
    private int length = 10;
    private Point position;
    private Polygon shape;
    private int[] xCords = new int[4];
    private int[] yCords = new int[4];
    private int velX = 2;
    private int velY = 2;
    private int direction;

    Coin(Point spawnPoint){
        position = spawnPoint;
        Random random = new Random();
        if(random.nextBoolean()){
            direction = 0;
        } else direction = 2;
    }

    private void drawCoin(){
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
        drawCoin();
        return shape;
    }

    public void up(){ position.y -= velY; }

    public void down(){ position.y += velY; }

    public void left(){ position.x -= velX; }

    public void right(){ position.x += velX; }

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

    public void move(){
        if(direction == 0){
            up();
            left();
        }else if(direction == 1){
            left();
        }else if(direction == 2){
            left();
            down();
        }else if(direction == 3){
            down();
        }else if(direction == 4){
            down();
            right();
        }else if(direction == 5){
            right();
        }else if(direction == 6){
            right();
            up();
        }else if(direction == 7){
            up();
        }
    }

    public void changeDirection(int i){
        direction = i;
    }

}
