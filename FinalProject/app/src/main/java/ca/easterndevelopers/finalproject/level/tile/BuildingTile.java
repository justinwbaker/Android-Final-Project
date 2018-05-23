package ca.easterndevelopers.finalproject.level.tile;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.utils.Utils;

public class BuildingTile extends Tile {

    public BuildingTile(Point position, int buildingTile, Level level) {
        super(position);
        switch (buildingTile){
            case 0:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_door);
                break;
            case 1:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_bottom);
                break;
            case 2:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_bottom_left);
                break;
            case 3:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_bottom_right);
                break;
            case 4:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_left);
                break;
            case 5:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_middle);
                break;
            case 6:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_right);
                break;
            case 7:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_top);
                break;
            case 8:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_top_left);
                break;
            case 9:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_roof_top_right);
                break;
            case 10:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_window_top_00);
                break;
            case 11:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_window_top_01);
                break;
            case 12:
                this.image = Utils.loadTileBitmap(GameActivity.getContext(), R.drawable.building_window_top_02);
                break;
            case 13:
                this.image = Utils.loadTileBitmap(level.getContext(), R.drawable.building_window);
                break;
        }
    }

}
