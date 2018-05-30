package ca.easterndevelopers.finalproject.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MenuRenderer extends SurfaceView implements Runnable {

    private double newTime, oldTime, deltaTime, fps;
    private boolean running;
    private Paint paint;
    private SurfaceHolder ourHolder;
    private Context context;
    private Canvas canvas;

    public MenuRenderer(Context context) {
        super(context);
        this.context = context;
        paint = new Paint();
        ourHolder = getHolder();

        fps = 1;
    }

    @Override
    public void run() {
        newTime = System.nanoTime();
        while(running) {
            update();
            render();
            oldTime = System.nanoTime();
            deltaTime = oldTime-newTime;
            fps = 1000000000.0 / (deltaTime);
            newTime = oldTime;
            //System.out.println("FPS: " + fps);
        }
    }

    private void update() {

    }

    private void render() {
        if (ourHolder.getSurface().isValid()) {


            paint.setColor(Color.BLACK);
           // canvas.drawRect(0, 0, MainScreen.getResolution().x, MainScreen.getResolution().y, paint);



        }
    }
}
