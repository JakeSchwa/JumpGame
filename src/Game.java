import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;

public class Game  extends JPanel implements ActionListener, KeyListener {

    Timer time = new Timer(15, this);
    Enviorment enviorment;
    Ship ship;
    private final Set<Integer> movementKeysCurrentlyPressed = new TreeSet<Integer>();
    private final Set<Integer> attackKeysCurrentlyPressed = new TreeSet<Integer>();

    Game(){
        time.start();
        this.ship = new Ship(new Point(200,200));
        this.enviorment = new Enviorment();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        super.setDoubleBuffered(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        enviorment.getCeiling().moveSurface();
        enviorment.getFloor().moveSurface();
        g.fillPolygon(ship.getShape());
        paintBullets(g);
        enemyCollision(g);
        paintEnemys(g);
        g.fillPolygon(enviorment.getCeiling().getShape());
        g.fillPolygon(enviorment.getFloor().getShape());
        CollisionCheck();
        enviorment.checkForNextWave();

    }

    private void paintBullets(Graphics g){
        if(ship.bullets.size() > 0){
            for (Bullet bullet : ship.bullets) {
                JSONObject JSONbullet = bullet.getBullet();
                if(JSONbullet.getInt("x") >= 1000 || JSONbullet.getInt("x") <= 0){
                    ship.bullets.remove(bullet);
                }
                g.fillOval(JSONbullet.getInt("x"),JSONbullet.getInt("y"),JSONbullet.getInt("width"),JSONbullet.getInt("height") );
            }
        }
    }

    private void enemyCollision(Graphics g){
        for(Enemy enemy : enviorment.enemies){
            boolean topLeft = enviorment.getCeiling().getShape().contains(enemy.getTopLeftCords());
            boolean topRight = enviorment.getCeiling().getShape().contains(enemy.getTopRightCords());
            boolean bottomRight = enviorment.getFloor().getShape().contains(enemy.getBottomLeftCords());
            boolean bottomLeft = enviorment.getFloor().getShape().contains(enemy.getBottomRightCords());
            boolean rightSide = enemy.getTopRightCords().getX() >= 1000 && enemy.onMap;
            boolean leftside = enemy.getTopLeftCords().getX() <= 0;
            if(topLeft && !topRight && !bottomLeft){
                enemy.changeDirection(4);
            }else if(topRight && !topLeft && !bottomRight){
                enemy.changeDirection(2);
            }else if(bottomRight && !bottomLeft && !topRight){
                enemy.changeDirection(0);
            }else if(bottomLeft && !bottomRight && !topLeft){
                enemy.changeDirection(6);
            }else if(topRight && topLeft){
                enemy.changeDirection(3);
            }else if(topRight && bottomRight || rightSide){
                enemy.changeDirection(1);
            }else if(bottomLeft && bottomRight){
                enemy.changeDirection(7);
            }else if((bottomLeft && topLeft) || leftside){
                enemy.changeDirection(5);
            }
        }
    }

    private void paintEnemys(Graphics g){
        if(enviorment.enemies.size() > 0){
            for(Enemy enemy : enviorment.enemies){
                enemy.move();
                g.fillPolygon(enemy.getEnemy());
            }
        }
    }

    public void CollisionCheck(){
        boolean floorCollision = enviorment.getFloor().getShape().intersects(ship.getShape().getBounds());
        boolean ceilingCollision = enviorment.getCeiling().getShape().intersects(ship.getShape().getBounds());
        bulletCollision();
        if(floorCollision || ceilingCollision){
            System.out.println("collision");
        }
    }

    public void bulletCollision(){
        for(Bullet bullet : ship.bullets){
            for(Enemy enemy : enviorment.enemies){
                if(enemy.getShape().intersects( bullet.getPosition().x, bullet.getPosition().y, bullet.getWidth(), bullet.getHeight() )){
                    enviorment.enemies.remove(enemy);
                    ship.bullets.remove(bullet);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(attackKeysCurrentlyPressed.contains(KeyEvent.VK_SPACE)){
            ship.shoot();
        }

        if(movementKeysCurrentlyPressed.contains(KeyEvent.VK_S) && movementKeysCurrentlyPressed.size() == 1){
            ship.down();
        }else if (movementKeysCurrentlyPressed.contains(KeyEvent.VK_W) && movementKeysCurrentlyPressed.size() == 1){
            ship.up();
        }else if (movementKeysCurrentlyPressed.contains(KeyEvent.VK_A) && movementKeysCurrentlyPressed.size() == 1){
            ship.left();
        }else if(movementKeysCurrentlyPressed.contains(KeyEvent.VK_D) && movementKeysCurrentlyPressed.size() == 1) {
            ship.right();
        }else if(movementKeysCurrentlyPressed.contains(KeyEvent.VK_W) && movementKeysCurrentlyPressed.contains(KeyEvent.VK_A)){
            ship.up();
            ship.left();
        }else if(movementKeysCurrentlyPressed.contains(KeyEvent.VK_W) && movementKeysCurrentlyPressed.contains(KeyEvent.VK_D)){
            ship.up();
            ship.right();
        }else if(movementKeysCurrentlyPressed.contains(KeyEvent.VK_S) && movementKeysCurrentlyPressed.contains(KeyEvent.VK_D)){
            ship.down();
            ship.right();
        }else if(movementKeysCurrentlyPressed.contains(KeyEvent.VK_S) && movementKeysCurrentlyPressed.contains(KeyEvent.VK_A)) {
            ship.down();
            ship.left();
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            attackKeysCurrentlyPressed.add(code);
        }else if(code == KeyEvent.VK_W || code == KeyEvent.VK_S || code == KeyEvent.VK_A || code == KeyEvent.VK_D){
            movementKeysCurrentlyPressed.add(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            attackKeysCurrentlyPressed.remove(code);
        }else if(code == KeyEvent.VK_W || code == KeyEvent.VK_S || code == KeyEvent.VK_A || code == KeyEvent.VK_D){
            movementKeysCurrentlyPressed.remove(code);
        }
    }

}
