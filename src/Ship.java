import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;

public class Ship extends JPanel{
    int x = 100;
    int y = 100;

    int velX = 3;
    int velY = 3;

    Ship(){
    }


    //Functions move the ship
    public void up(){ y -= velY; }

    public void down(){ y += velY; }

    public void left(){ x -= velX; }

    public void right(){ x += velX; }



}
