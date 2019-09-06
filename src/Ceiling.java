import java.awt.*;
import java.util.Random;

public class Ceiling {

    private int numOfPixelsBetweenSurfacePoints = 20;
    private int lengthOfCeiling = 6000;
    private int NumberOfPolygonPoints = lengthOfCeiling / numOfPixelsBetweenSurfacePoints + 2;
    private int numberOfSurfaces = lengthOfCeiling / numOfPixelsBetweenSurfacePoints;
    private int [] ceilingX = new int[NumberOfPolygonPoints];
    private int [] ceilingY = new int[NumberOfPolygonPoints];
    private int[] surfaceX = new int[numberOfSurfaces];
    private int[] surfaceY = new int[numberOfSurfaces];
    private Polygon shape;

     Ceiling(){
        generateCeilingSurface();
        generateCeilingPolygon();
    }
    private void generateCeilingSurface(){
        Random random = new Random();
        for(int i = 0; i<surfaceX.length; i++){
            int abnormal = random.nextInt(2);
            if(abnormal == 0){
                surfaceY[i] = random.nextInt(100) + 100;
            }else if(abnormal == 1){
                surfaceY[i] = random.nextInt(100) + 50;
            }
            surfaceX[i] = i * numOfPixelsBetweenSurfacePoints;
        }
    }

    private void generateCeilingPolygon(){
        for( int i = 0; i<NumberOfPolygonPoints; i++){
            if( i == 0) {
                ceilingX[i] = 6000;
                ceilingY[i] = 0;
            }else if( i == 1 ){
                ceilingX[i] = 0;
                ceilingY[i] = 0;
            }else if( i>1 ){
                ceilingX[i] = surfaceX[i-2];
                ceilingY[i] = surfaceY[i-2];
            }
        }
        updateShape();
    }

    private void updateShape(){
        shape = new Polygon(ceilingX, ceilingY, NumberOfPolygonPoints);
    }

    public void moveSurface(){
        for(int i = 0; i<NumberOfPolygonPoints; i++ ){
            ceilingX[i]-=1;
        }
        updateShape();
    }

    public Polygon getShape(){
         return shape;
    }

}