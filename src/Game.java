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
    int points = 0;

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
        if(ship.health <=0){
            super.removeAll();
            super.updateUI();

        }else {
            super.paintComponent(g);
            enviorment.getCeiling().moveSurface();
            enviorment.getFloor().moveSurface();
            g.fillPolygon(enviorment.getCeiling().getShape());
            g.fillPolygon(enviorment.getFloor().getShape());
            shipHitWall();
            g.fillPolygon(ship.getShape());
            bulletActivity(g);
            enemyCollision(g);
            coinCollision(g);
            paintEnemys(g);
            g.setColor(Color.yellow);
            pintCoins(g);
            CollisionCheck();
            coinCollision();
            CheckForShipEnemyCollision();
            enviorment.checkForNextWave();
            enviorment.checkForCoinSpawn();
            g.setColor(Color.green);
            g.drawString(Integer.toString(points), 10, 10);
            g.setColor(Color.red);
            g.drawString(Integer.toString((ship.health)), 10, 25);
            endGame();
        }
    }

    private void gameOver(Graphics g){
        g.drawString("Game Over",100,100);

    }


    private void bulletActivity(Graphics g){
        if(ship.bullets.size() > 0){
            for (Bullet bullet : ship.bullets) {
                bullet.moveBullet();
                paintBullet(g, bullet);
            }
        }
    }

    private void paintBullet(Graphics g, Bullet bullet) throws NullPointerException{
        g.fillOval((int) bullet.getPosition().getX(), (int) bullet.getPosition().getY(), bullet.getWidth(), bullet.getHeight());
    }

    private void shipHitWall(){
        if(enviorment.getCeiling().getShape().contains(ship.getTopLeftCords())){
            ship.down();
            ship.right();
            ship.down();
            ship.right();
        }
        if(enviorment.getCeiling().getShape().contains(ship.getTopRightCords())){
            ship.down();
            ship.left();
            ship.down();
            ship.left();
        }
        if(enviorment.getFloor().getShape().contains(ship.getBottomLeftCords())){
            ship.up();
            ship.right();
            ship.up();
            ship.right();
        }
        if(enviorment.getFloor().getShape().contains(ship.getBottomRightCords())){
            ship.up();
            ship.left();
            ship.up();
            ship.left();
        }
        if(ship.getTopRightCords().getX() >= 1001){
            ship.left();
        }
        if(ship.getTopLeftCords().getX() <= -2){
            ship.right();
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

    private void coinCollision(Graphics g){
        for(Coin coin : enviorment.coins){
            boolean topLeft = enviorment.getCeiling().getShape().contains(coin.getTopLeftCords());
            boolean topRight = enviorment.getCeiling().getShape().contains(coin.getTopRightCords());
            boolean bottomRight = enviorment.getFloor().getShape().contains(coin.getBottomLeftCords());
            boolean bottomLeft = enviorment.getFloor().getShape().contains(coin.getBottomRightCords());
            boolean rightSide = coin.getTopRightCords().getX() >= 1000;
            boolean leftside = coin.getTopLeftCords().getX() <= 0;

            if(topLeft && !topRight && !bottomLeft){
                coin.changeDirection(4);
            }else if(topRight && !topLeft && !bottomRight){
                coin.changeDirection(2);
            }else if(bottomRight && !bottomLeft && !topRight){
                coin.changeDirection(0);
            }else if(bottomLeft && !bottomRight && !topLeft){
                coin.changeDirection(6);
            }else if(topRight && topLeft){
                coin.changeDirection(3);
            }else if(topRight && bottomRight || rightSide){
                coin.changeDirection(1);
            }else if(bottomLeft && bottomRight){
                coin.changeDirection(7);
            }else if(bottomLeft && topLeft){
                coin.changeDirection(5);
            }else if(leftside){
                enviorment.coins.remove(coin);
            }
        }
    }



    private void paintEnemys(Graphics g){
        if(enviorment.enemies.size() > 0){
            for(Enemy enemy : enviorment.enemies){
                enemy.move();
                g.fillPolygon(enemy.getShape());
            }
        }
    }

    private void pintCoins(Graphics g){
        if(enviorment.coins.size() > 0){
            for(Coin coin : enviorment.coins){
                coin.move();
                g.fillPolygon(coin.getShape());
            }
        }
    }

    public void CollisionCheck(){
        boolean floorCollision = enviorment.getFloor().getShape().intersects(ship.getShape().getBounds());
        boolean ceilingCollision = enviorment.getCeiling().getShape().intersects(ship.getShape().getBounds());
        bulletCollision();
        if(floorCollision || ceilingCollision){
            System.out.println("collision");
            ship.health -= 5;
        }
    }

    public void endGame(){
        if(ship.health <= 0){

        }
    }

    public boolean offTheScreen(Bullet bullet){
        if (bullet.getPosition().getX() >= 1000 || bullet.getPosition().getX() <= 0){
            return true;
        }
        else return false;
    }

    public boolean shipAndBulletCollision(Ship ship, Bullet bullet){
        if(ship.getShape().intersects( bullet.getPosition().x, bullet.getPosition().y, bullet.getWidth(), bullet.getHeight() )){
            return true;
        }
        else return false;
    }

    public boolean shipCoinCollision(Coin coin){
        if(ship.getShape().getBounds2D().intersects(coin.getShape().getBounds2D())){
            return true;
        }
        else return false;
    }

    public void coinCollision(){
        for(int c = 0; c<enviorment.coins.size(); c++){
            Coin currentCoin = enviorment.coins.get(c);
            if(shipCoinCollision(currentCoin)){
                points += 100;
                enviorment.coins.remove(c);
                c--;
            }
        }
    }

    public boolean shipAndEnemeyCollision(Ship enemy){
        if(ship.getShape().getBounds2D().intersects(enemy.getShape().getBounds2D())){
            return true;
        }
        else return false;
    }

    public void CheckForShipEnemyCollision(){
        for(Ship enemy : enviorment.enemies){
            if(shipAndEnemeyCollision(enemy)){
                ship.health -= 5;
            }
        }
    }

    public void bulletCollision(){
        for(int b = 0; b <ship.bullets.size(); b++){
            Bullet currentBullet = ship.bullets.get(b);
            if(offTheScreen(currentBullet)) {
                ship.bullets.remove(currentBullet);
            }
            for(int e = 0; e< enviorment.enemies.size(); e++){
                Enemy currentEnemy = enviorment.enemies.get(e);
                if(shipAndBulletCollision(currentEnemy,currentBullet)){
                    enviorment.enemies.remove(currentEnemy);
                    ship.bullets.remove(b);
                    points += 10;
                    b--;
                    e--;
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
