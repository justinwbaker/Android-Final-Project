package ca.easterndevelopers.finalproject.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.game.Game;

public class GameRenderer extends SurfaceView implements Runnable{

    private Thread thread;

    private boolean running;

    private double newTime, oldTime, deltaTime, fps;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    private Game game;

    private Context context;

    public GameRenderer(Context context) {
        super(context);
        this.context = context;
        paint = new Paint();
        ourHolder = getHolder();

        fps = 1;

        game = new Game(context);
    }

    public void pause() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
    }

    public void resume() {
        running = true;
        thread = new Thread(this);
        thread.start();
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
            System.out.println("FPS: " + fps);
        }
    }

    public void update() {
        game.update(fps);
    }

    public void render() {
        if (ourHolder.getSurface().isValid()) {
            //First we lock the area of memory we will be drawing to
            canvas = ourHolder.lockCanvas();

            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, GameActivity.getResolution().x, GameActivity.getResolution().y, paint);

            game.render(canvas, paint);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }
}
