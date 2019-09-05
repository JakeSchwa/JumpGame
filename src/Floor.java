import java.awt.*;
import java.util.Random;

public class Floor {

    private int numOfPixelsBetweenSurfacePoints = 20;
    private int lengthOfCeiling = 6000;
    private int NumberOfPolygonPoints = lengthOfCeiling / numOfPixelsBetweenSurfacePoints + 2;
    private int numberOfSurfacePoints = lengthOfCeiling / numOfPixelsBetweenSurfacePoints;
    private int [ ] polygonX = new int[NumberOfPolygonPoints];
    private int [ ] polygonY = new int[NumberOfPolygonPoints];
    private int[] surfaceX = new int[numberOfSurfacePoints];
    private int[] surfaceY = new int[numberOfSurfacePoints];
    private Polygon shape;

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

    public Polygon getShape() {
        return shape;
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



