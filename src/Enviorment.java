import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Enviorment {
    Ceiling ceiling;
    Floor floor;
    LinkedList<Enemy> enemies = new LinkedList<Enemy>();


    Enviorment(){
        ceiling = new Ceiling();
        floor = new Floor();
        spawnEnemyWaves();
    }

    public void spawnEnemyWaves(){
        for(int i = 0; i < 60; i+=12){
            enemies.add(new Enemy( new Point(800, i + 200)));
        }
    }

    public void checkForNextWave(){
        if(enemies.size() == 0){
            spawnEnemyWaves();
        }
    }



}
