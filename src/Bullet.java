import org.json.JSONObject;
import java.awt.*;

public class Bullet {

    private Point position;
    private int width = 5;
    private int height = 2;
    private int velocity = 8;

    Bullet(Point position){
        this.position = position;
    }

    public void moveBullet() {
        this.position.x += velocity;
    }

    public Point getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}

