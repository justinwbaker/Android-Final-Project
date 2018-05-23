package ca.easterndevelopers.finalproject.GUI;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GUI {

    public static boolean isOnGUI = false;

    public abstract void update(double fps);

    public abstract void render(Canvas canvas, Paint paint);

}
