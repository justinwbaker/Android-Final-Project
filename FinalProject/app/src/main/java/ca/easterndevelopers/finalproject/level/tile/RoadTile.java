package ca.easterndevelopers.finalproject.level.tile;

import android.graphics.BitmapFactory;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.utils.Utils;

public class RoadTile extends Tile {

    public RoadTile(Point position, Level level, int roadTile) {
        super(position);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        switch(roadTile) {
            case 0:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.vertical_road_line);
                break;
            case 1:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.vertical_left_side_road);
                break;
            case 2:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.vertical_right_side_road);
                break;
            case 3:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.t_junction_road_line_left);
                break;
            case 4:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.t_junction_road_line_right);
                break;
            case 5:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.horizontal_bottom_road);
                break;
            case 6:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.horizontal_top_road);
                break;
            case 7:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.horizontal_road_line);
                break;
            case 8:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.corner_bottom_left_road);
                break;
            case 9:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.corner_bottom_right_road);
                break;
            case 10:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.corner_top_left_road);
                break;
            case 11:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.corner_top_right_road);
                break;
            case 12:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.sidewalk);
                break;
        }
    }

}
