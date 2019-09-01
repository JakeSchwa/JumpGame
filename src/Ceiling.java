import java.awt.*;
import java.util.Random;

public class Ceiling {

    int numOfPixelsBetweenSurfacePoints = 20;

    int lengthOfCeiling = 6000;

    int NumberOfPolygonPoints = lengthOfCeiling / numOfPixelsBetweenSurfacePoints + 2;

    int numberOfSurfaces = lengthOfCeiling / numOfPixelsBetweenSurfacePoints;

    int [ ] ceilingX = new int[NumberOfPolygonPoints];
    int [ ] ceilingY = new int[NumberOfPolygonPoints];

    int[] surfaceX = new int[numberOfSurfaces];
    int[] surfaceY = new int[numberOfSurfaces];

    public Polygon shape;

    Ceiling(){
        generateCeilingSurface();
        generateCeilingPolygon();
    }

    private void generateCeilingSurface(){
        Random random = new Random();
        for(int i = 0; i<surfaceX.length; i++){
            surfaceY[i] = random.nextInt(100) + 50;
            surfaceX[i] = i * numOfPixelsBetweenSurfacePoints;
        }
    }

    private void generateCeilingPolygon(){
        for( int i = 0; i<NumberOfPolygonPoints; i++){
            if(i == 0) {
                ceilingX[i] = 6000;
                ceilingY[i] = 0;
            }else if( i == 1 ){
                ceilingX[i] = 0;
                ceilingY[i] = 0;
            }else if(i>1){
                ceilingX[i] = surfaceX[i-2];
                ceilingY[i] = surfaceY[i-2];
            }
        }
        updateShape();
    }

    public void moveSurface(){
        for(int i = 0; i<NumberOfPolygonPoints; i++ ){
            ceilingX[i]-=1;
        }
        updateShape();
    }
    private void updateShape(){
        shape = new Polygon(ceilingX, ceilingY, NumberOfPolygonPoints);
    }
}
