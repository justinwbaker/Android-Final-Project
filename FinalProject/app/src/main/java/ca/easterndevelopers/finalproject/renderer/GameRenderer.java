package ca.easterndevelopers.finalproject.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
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

    private static Point touchedPoint;
    private Point preveousTouchedPoint;
    private static Point dragAmt;

    private Point canvasOffset;

    private Context context;

    public GameRenderer(Context context) {
        super(context);
        this.context = context;

        touchedPoint = new Point(-1, -1);
        preveousTouchedPoint = new Point(-1, -1);
        canvasOffset = new Point();

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
            //System.out.println("FPS: " + fps);
        }
    }

    public void update() {
        game.update(fps);
    }

    public void render() {
        if (ourHolder.getSurface().isValid()) {
            //First we lock the area of memory we will be drawing to
            canvas = ourHolder.lockCanvas();


                canvas.translate(canvasOffset.x, canvasOffset.y);

            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, GameActivity.getResolution().x, GameActivity.getResolution().y, paint);

            game.render(canvas, paint);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_MOVE:
                if(preveousTouchedPoint != null && Game.isLookingAtMap()) {
                    float dx = x - preveousTouchedPoint.x;
                    float dy = y - preveousTouchedPoint.y;
                    dragAmt = new Point((int)dx, (int)dy);
                    if(game.getPlayer().getCurrentLevel() != null) {
                        canvasOffset.x += dx;
                        canvasOffset.y += dy;
                    }

                    System.out.println(canvasOffset.x + " " + -Game.getPlayer().getCurrentLevel().getWidth() * GameActivity.getTileSize());

                    if (canvasOffset.x > 0)
                        canvasOffset.x = 0;
                    if (canvasOffset.y > 0)
                        canvasOffset.y = 0;
                    if (canvasOffset.x <= -Game.getPlayer().getCurrentLevel().getWidth() * GameActivity.getTileSize() + GameActivity.getResolution().x)
                        canvasOffset.x = (int)-(Game.getPlayer().getCurrentLevel().getWidth() * GameActivity.getTileSize()) + GameActivity.getResolution().x;
                    if (canvasOffset.y <= -Game.getPlayer().getCurrentLevel().getHeight() * GameActivity.getTileSize() + GameActivity.getResolution().y)
                        canvasOffset.y = (int)-(Game.getPlayer().getCurrentLevel().getHeight() * GameActivity.getTileSize())+ GameActivity.getResolution().y;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(!Game.isLookingAtMap()) {
                    touchedPoint = new Point((int) x, (int) y);
                    Game.getPlayer().updateUnits();
                }
                break;
        }
        preveousTouchedPoint = new Point((int)x, (int)y);
        return true;
    }

    public static Point getTouchedPoint() {
        return touchedPoint;
    }

    public static Point getDragAmt() {
        return dragAmt;
    }
}
