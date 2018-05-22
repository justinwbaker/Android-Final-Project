package ca.easterndevelopers.finalproject.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ca.easterndevelopers.finalproject.GUI.GUI;
import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
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
    private Point previousTouchedPoint;
    private static Point dragAmt;

    private static Point canvasOffset;

    private Context context;

    public GameRenderer(Context context) {
        super(context);
        this.context = context;

        touchedPoint = new Point(-1, -1);
        previousTouchedPoint = new Point(-1, -1);
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
        touchedPoint = null;
    }

    public void render() {
        if (ourHolder.getSurface().isValid()) {
            //First we lock the area of memory we will be drawing to
            canvas = ourHolder.lockCanvas();
            canvas.save();
            canvas.translate(canvasOffset.x, canvasOffset.y);

            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, MainActivity.getResolution().x, MainActivity.getResolution().y, paint);

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
                if(previousTouchedPoint != null && Game.isLookingAtMap()) {
                    float dx = x - previousTouchedPoint.x;
                    float dy = y - previousTouchedPoint.y;
                    dragAmt = new Point((int)dx, (int)dy);
                    if(game.getPlayer().getCurrentLevel() != null) {
                        canvasOffset.x += dx;
                        canvasOffset.y += dy;
                    }

                    if (canvasOffset.x > 0)
                        canvasOffset.x = 0;
                    if (canvasOffset.y > 0)
                        canvasOffset.y = 0;
                    if (canvasOffset.x <= -Game.getPlayer().getCurrentLevel().getWidth() * MainActivity.getTileSize() + MainActivity.getResolution().x)
                        canvasOffset.x = (int)-(Game.getPlayer().getCurrentLevel().getWidth() * MainActivity.getTileSize()) + MainActivity.getResolution().x;
                    if (canvasOffset.y <= -Game.getPlayer().getCurrentLevel().getHeight() * MainActivity.getTileSize() + MainActivity.getResolution().y)
                        canvasOffset.y = (int)-(Game.getPlayer().getCurrentLevel().getHeight() * MainActivity.getTileSize())+ MainActivity.getResolution().y;
                }
                break;
            case MotionEvent.ACTION_UP:
                touchedPoint = new Point((int) x, (int) y);
                Game.getGameGUI().update();
                if(!Game.isLookingAtMap() && !GUI.isOnGUI) {
                    Game.getPlayer().updateUnits();
                }
                break;
        }
        previousTouchedPoint = new Point((int)x, (int)y);
        return true;
    }

    public static Point getTouchedPoint() {
        return touchedPoint;
    }

    public static Point getWorldTouchedPoint() {
        int x = touchedPoint.x - canvasOffset.x;
        int y = touchedPoint.y - canvasOffset.y;
        return new Point(x, y);
    }

    public static Point getDragAmt() {
        return dragAmt;
    }

    public static Point getCanvasOffset() {
        return canvasOffset;
    }
}
