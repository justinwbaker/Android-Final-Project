package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.gameobject.GameObject;
import ca.easterndevelopers.finalproject.utils.Utils;

public class Car extends GameObject {

    Bitmap carimage;

    public Car(Point position, int color_index) {
        super(position, new Point((int)MainActivity.getTileSize(), (int)MainActivity.getTileSize()));
        switch (color_index) {
            case 0:
                carimage = Utils.loadTileBitmap(GameActivity.getContext(), R.drawable.red_car_horizontal);
                break;
            case 1:
                carimage = Utils.loadTileBitmap(GameActivity.getContext(), R.drawable.green_car_horizontal);
                break;
            case 2:
                carimage = Utils.loadTileBitmap(GameActivity.getContext(), R.drawable.orange_car_horizontal);
                break;
            case 3:
                carimage = Utils.loadTileBitmap(GameActivity.getContext(), R.drawable.red_car);
                break;
            case 4:
                carimage = Utils.loadTileBitmap(GameActivity.getContext(), R.drawable.green_car_vertical);
                break;
            case 5:
                carimage = Utils.loadTileBitmap(GameActivity.getContext(), R.drawable.orange_car_vertical);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(double fps) {

    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.carimage, this.getPosition().x, this.getPosition().y, paint);
        if(Game.debug){
            paint.setColor(Color.argb(100, 255, 20, 20));
            canvas.drawRect(this.getHitbox(), paint);
        }
    }
}
