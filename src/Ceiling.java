import netscape.javascript.JSObject;

import java.awt.*;
import java.util.Random;

public class Ceiling {
    Polygon shape;

    Point topLeft;
    Point topRight;
    Point bottomLeft;
    Point bottomRight;

    int numberOfPoints;
    int[] surface;

    int rate;
    int topBound;
    int bottomBound;
    int leftBound;
    int rightBound;


    Ceiling(){
        rightBound = 1000;
        leftBound = 0;
        topLeft = new Point(0, 0);
        topRight = new Point(1000, 0);
        bottomLeft = new Point(0,500 );
        bottomRight = new Point(1000, 500);
        int [ ] x = {0,1000,1000,0};
        int [ ] y = {0,0,50,50};
        int numberOfPoints = 4;
        shape = new Polygon(x,y,numberOfPoints);
    }

    private JSObject generateSurface(){
        int[] x;
        int[] y;
        int bumps = 20;
        Random random = new Random();

        for(int i = leftBound; i<=rightBound; i+=bumps){
            x[i] = random.nextInt()
        }

        return null;
    }



}
