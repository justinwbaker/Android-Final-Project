package ca.easterndevelopers.finalproject.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.level.gameobject.Bin;
import ca.easterndevelopers.finalproject.level.gameobject.Car;
import ca.easterndevelopers.finalproject.level.gameobject.Collider;
import ca.easterndevelopers.finalproject.level.gameobject.Sniper;
import ca.easterndevelopers.finalproject.level.gameobject.Soldier;
import ca.easterndevelopers.finalproject.level.gameobject.Tank;
import ca.easterndevelopers.finalproject.level.tile.BuildingTile;
import ca.easterndevelopers.finalproject.level.tile.GrassTile;
import ca.easterndevelopers.finalproject.level.tile.RoadTile;
import ca.easterndevelopers.finalproject.utils.Utils;

public class LevelManager {

    public static ArrayList<Level> levels = new ArrayList<Level>();

    public static  ArrayList<int[]> levelIndexes = new ArrayList<int[]>();

    /* Tiles:



                         grass: ff00ff00

        Road:

              verticalRoadLine: fff7e26b
            horizontalRoadLine: fff5dc49

          verticalLeftSideRoad: ff0f0f0f
         verticalRightSideRoad: ff000000

          horizontalBottomRoad: ff3f3f3f
             horizontalTopRoad: ff2f2f2f

              t_junction_right: fff9e887
               t_junction_left: fff8e57a

       road_corner_bottom_left: ff909090
      road_corner_bottom_right: ff9a9a9a
          road_corner_top_left: ff9b9b9b
         road_corner_top_right: ff9c9c9c

                      sidewalk: ff876a73

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

                     roof_left: ff5a5a5a
                      roof_mid: ff6a6a6a
                    roof_right: ff7a7a7a

     */

    public static Level loadLevel(Context context, int bitmapIndex, int goBitmapIndex) {
        int indexes[] = new int[2];
        Bitmap levelBitmap = Utils.loadBitmap(context, bitmapIndex);
        Bitmap goBitmap = Utils.loadBitmap(context, goBitmapIndex);
        Level level = new Level(levelBitmap.getWidth(), levelBitmap.getHeight(), context);
        for(int i = 0; i < levelBitmap.getHeight(); i++) {
            for(int j = 0; j < levelBitmap.getWidth(); j++) {
                switch (levelBitmap.getPixel(j, i)) {
                    //roads
                    case 0xfff7e26b:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 0));
                        break;

                    case 0xff0f0f0f:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 1));
                        break;
                    case 0xff000000:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 2));
                        break;
                    case 0xfff9e887:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 3));
                        break;
                    case 0xfff8e57a:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 4));
                        break;
                    case 0xff3f3f3f:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 5));
                        break;
                    case 0xff2f2f2f:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 6));
                        break;
                    case 0xfff5dc49:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 7));
                        break;

                    case 0xff909090:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 8));
                        break;
                    case 0xff9a9a9a:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 9));
                        break;
                    case 0xff9b9b9b:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 10));
                        break;
                    case 0xff9c9c9c:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 11));
                        break;
                    case 0xff876a73:
                        level.setTile(j, i, new RoadTile(new Point(j, i), level, 12));
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
                    case 0xff5a5a5a:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),4, level));
                        break;
                    case 0xff6a6a6a:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),5, level));
                        break;
                    case 0xff7a7a7a:
                        level.setTile(j, i, new BuildingTile(new Point(j, i),6, level));
                        break;
                    // fences

                    default:
                        level.setTile(j, i, new GrassTile(new Point(j, i), level));
                        break;
                }
            }
        }

        /*game objects

        player start: ffbe2633
        collider: ffb2dcef
        red bin : ffff0000
        green bin : ff00ff00
        car red : ff981e27
        car orange : ff44891a
        Tank : ffe0976f
        Sniper : ffe0976f
        enemy soldier: ffe06f8b
        player start area: ffbe2633

        enemy soldier: ffe06f8b
        enemy sniper: ffc3e06f
        enemy tank: ffe0976f

         */

        for(int i = 0; i < goBitmap.getHeight(); i++) {
            for(int j = 0; j < goBitmap.getWidth(); j++) {
                switch (goBitmap.getPixel(j, i)) {
                    case 0xffbe2633:
                        // do player start area stuff....
                        level.addStartingArea(j, i);
                        break;
                    case 0xffe06f8b:
                        level.getEnemy().addUnit(new Soldier(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), true));
                        break;
                    case 0xffe0976f:
                        level.getEnemy().addUnit(new Tank(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), true));
                        break;
                    case 0xffc3e06f:
                        level.getEnemy().addUnit(new Sniper(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), true));
                        break;
                    case 0xffff0000:
                        level.addGameObject(new Bin(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), false));
                        break;
                    case 0xff00ff00:
                        level.addGameObject(new Bin(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), true));
                        break;
                    case 0xffb2dcef:
                        level.addGameObject(new Collider(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), new Point((int) MainActivity.getTileSize(), (int)MainActivity.getTileSize())));
                        break;
                    case 0xff981e27:
                        level.addGameObject(new Car(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), 3));
                        break;
                    case 0xff44891a:
                        level.addGameObject(new Car(new Point((int)(MainActivity.getTileSize()*j),(int)(MainActivity.getTileSize()*i)), 4));
                        break;
                }
            }
        }
        indexes[0] = bitmapIndex;
        indexes[1] = goBitmapIndex;
        levelIndexes.add(indexes);
        levels.add(level);
        return level;
    }

    public static Level getLevel(int id){
        return levels.get(id);
    }

    public static void resetLevels(Context context) {
        for(int i = 0; i < levels.size(); i++) {
            resetLevel(context, i);
        }
    }

    public static void resetLevel(Context context, int index) {
        int[] indexes = levelIndexes.get(index);
        levels.set(index, loadLevel(context, indexes[0], indexes[1]));
    }

    public static void resetLevel(Context context, Level level) {
        int indexes[] = new int[2];
        for(int i = 0; i < levels.size(); i++) {
            if(levels.get(i) == level) {
                indexes[0] = levelIndexes.get(i)[0];
                indexes[1] = levelIndexes.get(i)[1];
                levels.set(i, loadLevel(context, indexes[0], indexes[1]));
            }
        }
    }

}
