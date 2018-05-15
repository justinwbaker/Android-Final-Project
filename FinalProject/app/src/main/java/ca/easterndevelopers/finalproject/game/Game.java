package ca.easterndevelopers.finalproject.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Button;

public class Game {

    Context context;

    float x = 0;

    public Game(Context context) {
        this.context = context;

    }


    public void update(double fps) {
        x+=50/fps;
    }

    public void render(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawRect(x, 100, x+100, 200, paint);
    }

}
