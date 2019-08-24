import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;

public class Ship extends JPanel implements ActionListener, KeyListener {
    int x = 100;
    int y = 100;

    int velX = 3;
    int velY = 3;

    private final Set<Integer> pressed = new TreeSet<Integer>();
    boolean keyPressed;

    Ship(){
        //Create Double Buffered GUI, Start time and set up listner.
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void actionPerformed(ActionEvent e){
        super.setDoubleBuffered(true);
        if(keyPressed){
            //moving in one direction
            if(pressed.size() == 1) {
                if(pressed.contains(KeyEvent.VK_DOWN)){
                    down();
                }else if (pressed.contains(KeyEvent.VK_UP)){
                    up();
                }else if (pressed.contains(KeyEvent.VK_LEFT)){
                    left();
                }else if(pressed.contains(KeyEvent.VK_RIGHT)){
                    right();
                }
            }
            if(pressed.size() >= 2){
                if(pressed.contains(KeyEvent.VK_UP) && pressed.contains(KeyEvent.VK_LEFT)){
                    up();
                    left();
                }else if(pressed.contains(KeyEvent.VK_UP) && pressed.contains(KeyEvent.VK_RIGHT)){
                    up();
                    right();
                }else if(pressed.contains(KeyEvent.VK_DOWN) && pressed.contains(KeyEvent.VK_RIGHT)){
                    down();
                    right();
                }else if(pressed.contains(KeyEvent.VK_DOWN) && pressed.contains(KeyEvent.VK_LEFT)){
                    down();
                    left();
                }
            }
        }
        repaint();
    }

    //Functions move the ship
    public void up(){ y -= velY; }

    public void down(){ y += velY; }

    public void left(){ x -= velX; }

    public void right(){ x += velX; }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Take key and get int value code from it.
        //Put key being pressed into a set of currently pressed keys
        int code = e.getKeyCode();
        pressed.add(code);
        System.out.println(pressed);
        //key is being pressed
        keyPressed = true;
        }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
        if(pressed.size() == 0){ keyPressed = false; }

    }
}
