import java.awt.*;

public class Enemy extends Ship {

    boolean onMap;
    private int direction = 0;


    Enemy(Point startPosition){
        super(startPosition);
        onMap = false;
    }

    public Polygon getEnemy(){
        return getShape();
    }

    public void move(){
        if(!onMap){
            checkOnMap();
            left();
            left();
        }else{
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
    }

    public void changeDirection(int i){
        direction = i;
    }

    public void checkOnMap(){
        if(getxCords()[1] < 1000 && getxCords()[1] > 0){
            onMap = true;
        }
    }

}
