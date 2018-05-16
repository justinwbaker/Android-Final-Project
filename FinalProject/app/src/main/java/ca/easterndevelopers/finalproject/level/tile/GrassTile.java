package ca.easterndevelopers.finalproject.level.tile;

import android.graphics.BitmapFactory;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.utils.Utils;

public class GrassTile extends Tile {

    public GrassTile(Point position, Level level) {
        super(position);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.grass);
    }


}
