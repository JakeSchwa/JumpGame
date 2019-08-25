import javax.swing.*;
import java.awt.*;


public class Ship extends JPanel{
    Point position;
    int velX = 3;
    int velY = 3;

    Ship(){
        position = new Point(100, 100);
    }

    //Functions move the ship
    public void up(){ position.y -= velY; }

    public void down(){ position.y += velY; }

    public void left(){ position.x -= velX; }

    public void right(){ position.x += velX; }

}
