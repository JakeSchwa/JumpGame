import java.awt.*;
import java.util.Random;

public class Floor {

    int numOfPixelsBetweenSurfacePoints = 20;

    int lengthOfCeiling = 6000;

    int NumberOfPolygonPoints = lengthOfCeiling / numOfPixelsBetweenSurfacePoints + 2;

    int numberOfSurfacePoints = lengthOfCeiling / numOfPixelsBetweenSurfacePoints;

    int [ ] polygonX = new int[NumberOfPolygonPoints];
    int [ ] polygonY = new int[NumberOfPolygonPoints];

    int[] surfaceX = new int[numberOfSurfacePoints];
    int[] surfaceY = new int[numberOfSurfacePoints];

    public Polygon shape;

    Floor(){
        generateCeilingSurface();
        generateCeilingPolygon();
    }

    private void generateCeilingSurface(){
        Random random = new Random();
        for(int i = 0; i<surfaceX.length; i++){
            surfaceY[i] = random.nextInt(100) + 300;
            surfaceX[i] = i * numOfPixelsBetweenSurfacePoints;
        }
    }

    private void generateCeilingPolygon(){
        for( int i = 0; i<NumberOfPolygonPoints; i++){
            if(i == 0) {
                polygonX[i] = 6000;
                polygonY[i] = 500;
            }else if( i == 1 ){
                polygonX[i] = 0;
                polygonY[i] = 500;
            }else if(i>1){
                polygonX[i] = surfaceX[i-2];
                polygonY[i] = surfaceY[i-2];
            }
        }
        updatePolygon();
    }

    public void moveSurface(){
        for(int i = 0; i<NumberOfPolygonPoints; i++ ){
            polygonX[i]-=1;
        }
        updatePolygon();
    }
    private void updatePolygon(){
        shape = new Polygon(polygonX, polygonY, NumberOfPolygonPoints);
    }
}



