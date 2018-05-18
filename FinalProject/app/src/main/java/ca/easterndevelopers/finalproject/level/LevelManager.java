package ca.easterndevelopers.finalproject.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.level.tile.GrassTile;
import ca.easterndevelopers.finalproject.utils.Utils;

public class LevelManager {

    public static ArrayList<Level> levels = new ArrayList<Level>();

    /* Tiles:

        grass = ff00ff00
        verticalRoadLine = fff7e26b
        verticalLeftSideRoad = ff0f0f0f
        verticalRightSideRoad = ff000000

     */

    public static void loadLevel(Context context, int bitmapIndex) {
        Bitmap levelBitmap = Utils.loadBitmap(context, bitmapIndex);
        Level level = new Level(levelBitmap.getWidth(), levelBitmap.getHeight(), context);
        for(int i = 0; i < levelBitmap.getHeight(); i++) {
            for(int j = 0; j < levelBitmap.getWidth(); j++) {
                System.out.println(levelBitmap.getPixel(j, i));
                switch (levelBitmap.getPixel(j, i)) {
                    case 0xff00ff00:
                        level.setTile(j, i, new GrassTile(new Point(j, i), level));
                        break;
                }
            }
        }
    }

    public static Level getLevel(int id){
        return levels.get(id);
    }

}
