package ca.easterndevelopers.finalproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;

import java.util.Random;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;

public class Utils {

    public static Bitmap loadBitmap(Context context, int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        return (BitmapFactory.decodeResource(context.getResources(), id, options));
    }

    public static Bitmap loadTileBitmap(Context context, int id) {
        Bitmap returnBitmap = loadBitmap(context, id);

        returnBitmap = getResizedBitmap(returnBitmap, (int) MainActivity.getTileSize(), (int)MainActivity.getTileSize(), true);

        return returnBitmap;
    }

    public Bitmap cropBitmap(Context context, int index, int x, int y, int w, int h) {
        return Bitmap.createBitmap(loadBitmap(context, index), x, y, w, h);
    }

    public static Bitmap cropBitmap(Bitmap image, int x, int y, int w, int h) {
        return Bitmap.createBitmap(image, x, y, w, h);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight, boolean isNecessaryToKeepOrig) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        if(!isNecessaryToKeepOrig){
            bm.recycle();
        }
        return resizedBitmap;
    }

    public static Point toTiledPosition(Point point) {
        Point tilePosition = new Point(point);
        tilePosition.x = Math.round(tilePosition.x / MainActivity.getTileSize());
        tilePosition.y = Math.round(tilePosition.y / MainActivity.getTileSize());
        return tilePosition;
    }

    public static Point toWorldPosition(Point point) {
        Point worldPosition = new Point(point);
        worldPosition.x *= MainActivity.getTileSize();
        worldPosition.y *= MainActivity.getTileSize();
        return worldPosition;
    }

    public static double getDistance(Point a, Point b) {
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        return Math.sqrt((dx*dx)+(dy*dy));
    }

    public static double getRadians(int degree){
        double radians = degree * Math.PI / 180;
        return radians;
    }

    public static int getRandom(){
        Random rand = new Random();
        int number = rand.nextInt(5) + 1;
        int negative = rand.nextInt(2) + 1;
        if(negative == 1){
            number = -number;
        }
        return number;
    }

}
