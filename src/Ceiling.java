import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.json.JSONString;

import java.awt.*;
import java.util.Random;

public class Ceiling {
    Polygon shape;

    Point topLeft;
    Point topRight;
    Point bottomLeft;
    Point bottomRight;

    int [ ] ceilingX;
    int [ ] ceilingY;

    int numberOfPoints;





    int topBound;
    int bottomBound;
    int leftBound;
    int rightBound;

    int roughness = 20;

    int[] surfaceX = new int[roughness];
    int[] surfaceY = new int[roughness];




    Ceiling(){
        leftBound = 0;
        rightBound = 1000;
        topBound = 0;
        bottomBound = 500;

        topLeft = new Point(0, 0);
        topRight = new Point(1000, 0);
        bottomLeft = new Point(0,500 );
        bottomRight = new Point(1000, 500);

        int [ ] ceilingX;
        int [ ] ceilingY;

        int numberOfPoints = 4;


        JSONObject test = generateSurface();

        System.out.println(surfaceX.length);

    }

    private JSONObject generateSurface(){


        Random random = new Random();

        for(int i = 0; i<surfaceX.length; i++){
            surfaceY[i] = random.nextInt(bottomBound);
            surfaceX[i] = i;



        }

        JSONObject surfaceCords = new JSONObject();

        surfaceCords.put("x",surfaceX);
        surfaceCords.put("y",surfaceY);

        System.out.println(surfaceCords.toString());


        return surfaceCords;
    }



}
