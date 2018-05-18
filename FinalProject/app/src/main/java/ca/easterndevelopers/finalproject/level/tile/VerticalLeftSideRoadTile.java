package ca.easterndevelopers.finalproject.level.tile;

import android.graphics.BitmapFactory;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.utils.Utils;

public class VerticalLeftSideRoadTile extends Tile {

    public VerticalLeftSideRoadTile(Point position, Level level) {
        super(position);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.vertical_left_side_road);
    }
}
