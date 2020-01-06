import java.awt.*;
import java.util.LinkedList;


public class Enviorment {
    private Ceiling ceiling;
    private Floor floor;
    LinkedList<Enemy> enemies = new LinkedList<Enemy>();
    LinkedList<Coin> coins = new LinkedList<Coin>();

    Enviorment(){
        ceiling = new Ceiling();
        floor = new Floor();
        spawnEnemyWaves();
    }

    public void spawnCoins(){
        coins.add(new Coin(new Point(980, 250)));
    }

    public void checkForCoinSpawn(){
        if(coins.size() == 0) spawnCoins();
    }

    public void spawnEnemyWaves(){
        for(int i = 0; i < 60; i+=12){
            enemies.add(new Enemy( new Point(1100, i + 200)));
        }
    }

    public void checkForNextWave(){
        if(enemies.size() == 0){
            spawnEnemyWaves();
        }
    }

    public Ceiling getCeiling() {
        return ceiling;
    }

    public Floor getFloor() {
        return floor;
    }
}
