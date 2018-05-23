package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.utils.Utils;

public class Bin extends GameObject {

    Bitmap binImage;

    public Bin(Point position, boolean greenBin) {
        super(position, new Point(16, 16));
        if(greenBin) {
            this.binImage = Utils.loadBitmap(GameActivity.getContext(), R.drawable.green_bin);
        }else {
            this.binImage = Utils.loadBitmap(GameActivity.getContext(), R.drawable.green_bin);
        }
    }

    @Override
    public void update(double fps) {

    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.binImage, this.getPosition().x, this.getPosition().y, paint);
    }
}
