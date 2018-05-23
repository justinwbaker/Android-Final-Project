package ca.easterndevelopers.finalproject.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.level.gameobject.Soldier;
import ca.easterndevelopers.finalproject.level.tile.BuildingTile;
import ca.easterndevelopers.finalproject.level.tile.GrassTile;
import ca.easterndevelopers.finalproject.level.tile.VerticalLeftSideRoadTile;
import ca.easterndevelopers.finalproject.level.tile.VerticalRightSideRoadTile;
import ca.easterndevelopers.finalproject.level.tile.VerticalRoadLineTile;
import ca.easterndevelopers.finalproject.utils.Utils;

public class LevelManager {

    public static ArrayList<Level> levels = new ArrayList<Level>();

    /* Tiles:

             player start area: ffbe2633
                 enemy soldier: ffe06f8b

                         grass: ff00ff00
              verticalRoadLine: fff7e26b
          verticalLeftSideRoad: ff0f0f0f
         verticalRightSideRoad: ff000000

        building:
                          door: ff31a2f2
                 window_bottom: ff5e5e5e
                 window_top_00: ff6e6e6e
                 window_top_01: ff7e7e7e
                 window_top_02: ff8e8e8e

              roof_bottom_left: ff5f5f5f
             roof_bottom_right: ff6f6f6f

                 roof_top_left: ff7f7f7f
                roof_top_right: ff8f8f8f

                      roof_top: ff9f9f9f
                   roof_middle: ff4f4f4f
     */

    public static void loadLevel(Context context, int bitmapIndex) {
        Bitmap levelBitmap = Utils.loadBitmap(context, bitmapIndex);
        Level level = new Level(levelBitmap.getWidth(), levelBitmap.getHeight(), context);
        for(int i = 0; i < levelBitmap.getHeight(); i++) {
            for(int j = 0; j < levelBitmap.getWidth(); j++) {
                switch (levelBitmap.getPixel(j, i)) {
                    case 0xff00ff00:
                        level.setTile(j, i, new GrassTile(new Point(j, i), level));
                        break;
                    case 0xffbe2633:
                        level.setTile(j, i, new GrassTile(new Point(j, i), level));
                        // do player start area stuff....
                        break;
                    case 0xffe06f8b:
                        level.setTile(j, i, new GrassTile(new Point(j, i), level));
                        // add enemy solder
                        level.getEnemy().addUnit(new Soldier(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), true));
                        break;
                    //roads
                    case 0xfff7e26b:
                        level.setTile(j, i, new VerticalRoadLineTile(new Point(j, i), level));
                        break;

                    case 0xff0f0f0f:
                        level.setTile(j, i, new VerticalLeftSideRoadTile(new Point(j, i), level));
                        break;
                    case 0xff000000:
                        level.setTile(j, i, new VerticalRightSideRoadTile(new Point(j, i), level));
                        break;
                    // bulding
                    case 0xff31a2f2:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),0, level));
                        break;
                    case 0xff5e5e5e:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),13, level));
                        break;
                    case 0xff6e6e6e:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),10, level));
                        break;
                    case 0xff7e7e7e:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),11, level));
                        break;
                    case 0xff8e8e8e:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),12, level));
                        break;
                    case 0xff5f5f5f:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),2, level));
                        break;
                    case 0xff6f6f6f:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),3, level));
                        break;
                    case 0xff7f7f7f:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),8, level));
                        break;
                    case 0xff8f8f8f:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),9, level));
                        break;
                    case 0xff9f9f9f:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),7, level));
                        break;
                    case 0xff4f4f4f:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),1, level));
                        break;
                    // fences
                }
            }
        }
        levels.add(level);
    }

    public static Level getLevel(int id){
        return levels.get(id);
    }

}
