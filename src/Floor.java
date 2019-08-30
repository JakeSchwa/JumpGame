import java.awt.*;
import java.util.Random;

public class Floor {
    public Polygon shape;
    Point topLeft;
    Point topRight;
    Point bottomLeft;
    Point bottomRight;

    int topBound;
    int bottomBound;
    int leftBound;
    int rightBound;

    int[] surfaceX = new int[21];
    int[] surfaceY = new int[21];

    int [ ] ceilingX = new int[23];
    int [ ] ceilingY = new int[23];
    Floor(){
        leftBound = 0;
        rightBound = 1000;
        topBound = 0;
        bottomBound = 200;
        topLeft = new Point(0, 0);
        topRight = new Point(1000, 0);
        bottomLeft = new Point(0,100 );
        bottomRight = new Point(1000, 100);
        generateSurface();

        for( int i = 0; i<ceilingX.length; i++){

            if(i == 0) {
                ceilingX[i] = 1000;
                ceilingY[i] = 500;
            }else if( i == 1 ){
                ceilingX[i] = 0;
                ceilingY[i] = 500;
            }else if(i>1){
                ceilingX[i] = surfaceX[i-2];
                ceilingY[i] = surfaceY[i-2];
            }
        }
        shape = new Polygon(ceilingX, ceilingY, 23);
        System.out.print(shape.xpoints);

    }

    private void generateSurface(){
        Random random = new Random();
        for(int i = 0; i<surfaceX.length; i++){
            surfaceY[i] = random.nextInt(100) + 350;
            surfaceX[i] = i * 50;
        }
    }
}



